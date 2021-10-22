package egovframework.otl.report.service.impl;

import egovframework.com.cmm.service.EgovFileMngService;
import egovframework.com.cmm.service.FileVO;
import egovframework.com.sec.rgm.service.EgovAuthorGroupService;
import egovframework.otl.report.service.AbbreviatedReportService;
import egovframework.otl.report.service.AbbreviatedReportVO;
import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;
import egovframework.rte.fdl.idgnr.EgovIdGnrService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;

@Service("AbbreviatedReportService")
public class AbbreviatedReportServiceImpl extends EgovAbstractServiceImpl implements AbbreviatedReportService {


    @Resource(name = "abbreviatedReportIdService")
    private EgovIdGnrService abbreviatedReportIdService; // 보고서 ID 채번
    @Resource(name="egovAuthorGroupService")
    private EgovAuthorGroupService egovAuthorGroupService;
    @Resource(name="AbbreviatedReportDAO")
    private AbbreviatedReportDAO abbreviatedReportDAO;
    @Resource(name = "EgovFileMngService")
    private EgovFileMngService fileService;

    @Override
    public List<AbbreviatedReportVO> abbreviatedReportList(HashMap parameterMap) throws Exception{
        HashMap<String , String> userRole = egovAuthorGroupService.selectUserRole(parameterMap); //[mber_nm , author_code , mber_id , esntl_id]
        parameterMap.put("role",userRole.get("author_code"));
        AbbreviatedReportVO temp = abbreviatedReportDAO.searchUsersTrainingAndTeamId(userRole.get("esntl_id"));
        try {
            if(!parameterMap.containsKey("teamId")) {
                parameterMap.put("teamId", temp.getTeamId());
            }
        }catch (Exception e){
            if(e.getClass() == NullPointerException.class){
                parameterMap.put("teamId","");
            }else{
                e.printStackTrace();
                throw e;
            }
        }
        List<AbbreviatedReportVO> abbreviatedReportList = abbreviatedReportDAO.abbreviatedReportList(parameterMap);

        return abbreviatedReportList;
    }

    @Override
    public AbbreviatedReportVO abbreviatedReportDetail(AbbreviatedReportVO vo) throws Exception {
        AbbreviatedReportVO abbreviatedReportVO = abbreviatedReportDAO.abbreviatedReportDetail(vo);
        return abbreviatedReportVO;
    }

    @Override
    public void abbreviatedReportInsert(AbbreviatedReportVO vo) throws Exception {
        vo.setReportId(abbreviatedReportIdService.getNextStringId());
        vo.setReportStatus("200"); // 200 : 제출 후 관리자 검토 중
        abbreviatedReportDAO.abbreviatedReportInsert(vo);
    }

    @Override
    public AbbreviatedReportVO searchUsersTrainingAndTeamId(String userId) throws Exception {
        return abbreviatedReportDAO.searchUsersTrainingAndTeamId(userId);
    }

    @Override
    public int abbreviatedReportCount(HashMap parameterMap) throws Exception {
        return abbreviatedReportDAO.abbreviatedReportCount(parameterMap);
    }

    @Override
    public void abbreviatedReportSubmit(AbbreviatedReportVO vo) throws Exception {
        abbreviatedReportDAO.abbreviatedReportSubmit(vo);
    }

    @Override
    public void abbreviatedReportUpdate(AbbreviatedReportVO vo) throws Exception {
        abbreviatedReportDAO.abbreviatedReportUpdate(vo);
    }

    @Override
    public void abbreviatedReportDelete(AbbreviatedReportVO vo) throws Exception {
        System.out.println("vo.getAttachFileId() = " + vo.getAttachFileId());
        FileVO fvo = new FileVO();
        fvo.setAtchFileId(vo.getAttachFileId());
        if (!"".equals(fvo.getAtchFileId()) || fvo.getAtchFileId() != null) {
            fileService.deleteAllFileInf(fvo);
        }

        abbreviatedReportDAO.abbreviatedReportDelete(vo);
    }

    @Override
    public void abbreviatedReportReject(AbbreviatedReportVO vo) throws Exception {
        vo.setReportStatus("300");
        abbreviatedReportDAO.abbreviatedReportChangeStatus(vo);
    }

    @Override
    public void abbreviatedReportGiveScore(AbbreviatedReportVO vo, int score) throws Exception {
        // KEPCO_TRAINING_TEAM_SCORES 테이블에 데이터 입력
        // {TRAINING_ID , TEAM_ID , QUESTION_ID , SCORE_ID, TRAINING_TYPE, SCORE} <- NOT NULL
        HashMap param = new HashMap();
        param.put("trainingId", vo.getTrainId());
        param.put("teamId", vo.getTeamId());
        param.put("questionId", vo.getReportId());

        param.put("scoreId", 0);

        /* TODO trainingType은 대시보드(리더보드)에서 집계할 시 분류를 위한 키로 사용됨. 변경 가능성 높음.*/
        param.put("trainingType", selectTrainingType(vo.getReportType()));
        param.put("score", score);

        param.put("subType",selectSubType(vo.getReportType()));
        abbreviatedReportDAO.abbreviatedReportGiveScore(param);

        // 보고서의 상태값 400 : 관리자가 검토 이후 점수 부여 완료 로 변경
        vo.setReportStatus("400");
        abbreviatedReportDAO.abbreviatedReportChangeStatus(vo);
    }

    @Override
    public void abbreviatedReportUndoGiveScore(AbbreviatedReportVO vo) throws Exception {
        // KEPCO_TRAINING_TEAM_SCORES 테이블에서 데이터 삭제
        // key : question_id == vo.getReportId()
        abbreviatedReportDAO.abbreviatedReportUndoGiveScore(vo);

        // 보고서의 상태값 200 : 제출 후 관리자 검토 중 으로 변경
        vo.setReportStatus("200");
        abbreviatedReportDAO.abbreviatedReportChangeStatus(vo);
    }

    @Override
    public List<HashMap> searchTeamIdList(String trainId) throws Exception {
        return abbreviatedReportDAO.searchTeamIdList(trainId);
    }

    /**
     * 보고서 타입에 따라 kepco_training_team_scores.training_type에 들어갈 값을 정한다.
     * @param reportType
     * @return String result
     */
    private String selectTrainingType(String reportType) {
        String trainingType = "err";
        if (reportType.equals("APT1")) {
            trainingType = "apt01";
        } else if (reportType.equals("APT2")) {
            trainingType = "apt02";
        } else if (reportType.equals("RANSOM")) {
            trainingType = "ransom";
        } else if (reportType.equals("WEB")) {
            trainingType = "wh";
        } else if (reportType.equals("DDOS")) {
            trainingType = "ddos";
        }

        return trainingType;
    }

    /**
     * 보고서 타입에 따라 kepco_training_team_scores.sub_type에 들어갈 값을 정한다.
     * @param reportType
     * @return
     */
    private String selectSubType(String reportType) {
        String subType = "err";

        if (reportType.equals("APT1")) {
            subType = "APT1_REPORT";
        } else if (reportType.equals("APT2")) {
            subType = "APT2_REPORT";
        } else if (reportType.equals("RANSOM")) {
            subType = "RANSOM_REPORT";
        } else if (reportType.equals("WEB")) {
            subType = "WH_REPORT";
        } else if (reportType.equals("DDOS")) {
            subType = "DDOS_REPORT";
        }

        return subType;
    }

}
