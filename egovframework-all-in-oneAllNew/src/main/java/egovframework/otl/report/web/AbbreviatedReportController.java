package egovframework.otl.report.web;

import egovframework.com.cmm.LoginVO;
import egovframework.com.cmm.service.EgovFileMngService;
import egovframework.com.cmm.service.EgovFileMngUtil;
import egovframework.com.cmm.service.FileVO;
import egovframework.com.cmm.util.EgovUserDetailsHelper;
import egovframework.com.cop.bbs.service.BoardVO;
import egovframework.com.sec.rgm.service.EgovAuthorGroupService;
import egovframework.otl.report.service.AbbreviatedReportSearchVO;
import egovframework.otl.report.service.AbbreviatedReportService;
import egovframework.otl.report.service.AbbreviatedReportVO;
import egovframework.rte.fdl.property.EgovPropertyService;
import egovframework.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;

@Controller
public class AbbreviatedReportController {

    @Resource(name = "AbbreviatedReportService")
    private AbbreviatedReportService abbreviatedReportService;

    @Resource(name = "egovAuthorGroupService")
    private EgovAuthorGroupService egovAuthorGroupService;

    @Resource(name = "EgovFileMngService")
    private EgovFileMngService fileMngService;

    @Resource(name = "EgovFileMngUtil")
    private EgovFileMngUtil fileUtil;

    @Resource(name = "propertiesService")
    protected EgovPropertyService propertyService;

    /*
     * 보고서 화면 매핑
     *
     * */
    // 사용자가 속한 팀이 작성한 보고서 리스트 화면 매핑
    @RequestMapping(value = "/report/abbreviatedReportList.do")
    public String abbreviatedReportList(@ModelAttribute("searchForm") AbbreviatedReportSearchVO searchVO, ModelMap model) throws Exception {

        HashMap parameterMap = new HashMap<>();
        LoginVO user = (LoginVO) EgovUserDetailsHelper.getAuthenticatedUser();

        searchVO.setPageUnit(propertyService.getInt("pageUnit"));
        searchVO.setPageSize(propertyService.getInt("pageSize"));

        PaginationInfo paginationInfo = new PaginationInfo();
        paginationInfo.setCurrentPageNo(searchVO.getPageIndex());
        paginationInfo.setRecordCountPerPage(searchVO.getPageUnit());
        paginationInfo.setPageSize(searchVO.getPageSize());

        parameterMap.put("esntlId", user.getUniqId());
        parameterMap.put("recordCountPerPage", paginationInfo.getRecordCountPerPage());
        parameterMap.put("firstIndex", paginationInfo.getFirstRecordIndex());

        /*검색용 파라미터*/
        parameterMap.put("reportType", searchVO.getReportType());
        parameterMap.put("reportTitle", searchVO.getReportTitle());
        parameterMap.put("reportStatus", searchVO.getReportStatus());
        if(searchVO.getTeamId() != "" && searchVO.getTeamId() != null) {
            parameterMap.put("teamId", searchVO.getTeamId());
        }

        List<AbbreviatedReportVO> reportList = abbreviatedReportService.abbreviatedReportList(parameterMap);
        HashMap role = egovAuthorGroupService.selectUserRole(parameterMap);
        int totalRecordCount = abbreviatedReportService.abbreviatedReportCount(parameterMap);
        paginationInfo.setTotalRecordCount(totalRecordCount);

        model.addAttribute("reportList", reportList);
        if(role.get("author_code").equals("ROLE_ADMIN")) {
            model.addAttribute("teamIdList", abbreviatedReportService.searchTeamIdList(reportList.get(0).getTrainId()));
        }
        model.addAttribute("role", role);
        model.addAttribute("paginationInfo", paginationInfo);
        // 사용자가 속한 팀이 작성한 보고서 객체 리스트를 전달
        return "egovframework/otl/report/AbbreviatedReportList";
    }

    // 보고서 Detail 화면 매핑
    @RequestMapping(value = "/report/abbreviatedReportDetail.do")
    public String abbreviatedReportDetail(@ModelAttribute("reportVO") AbbreviatedReportVO vo, ModelMap model) throws Exception {
        HashMap parameterMap = new HashMap<>();
        LoginVO user = (LoginVO) EgovUserDetailsHelper.getAuthenticatedUser();
        parameterMap.put("esntlId", user.getUniqId());

        AbbreviatedReportVO reportVO = abbreviatedReportService.abbreviatedReportDetail(vo);
        model.addAttribute("role", egovAuthorGroupService.selectUserRole(parameterMap));
        model.addAttribute("reportVO", reportVO);
        return "egovframework/otl/report/AbbreviatedReportDetail";
    }

    // 보고서 작성 화면 매핑
    @RequestMapping(value = "/report/abbreviatedReportRegist.do")
    public String abbreviatedReportRegist(ModelMap model) throws Exception {
        return "egovframework/otl/report/AbbreviatedReportRegist";
    }

    // 보고서 수정 화면 매핑
    @RequestMapping(value = "/report/abbreviatedReportUpdateView.do")
    public String abbreviatedReportUpdateView(@ModelAttribute("reportVO") AbbreviatedReportVO vo, ModelMap model) throws Exception {
        AbbreviatedReportVO reportVO = abbreviatedReportService.abbreviatedReportDetail(vo);
        model.addAttribute("reportVO", reportVO);
        return "egovframework/otl/report/AbbreviatedReportUpdate";
    }

    /*
     * 보고서 CRUD 관련 매핑
     *
     * */

    // 보고서 Insert 매핑
    @RequestMapping(value = "/report/abbreviatedReportInsert.do")
    public String abbreviatedReportInsert(final MultipartHttpServletRequest multiRequest, @ModelAttribute("reportVO") AbbreviatedReportVO vo, ModelMap modelMap) throws Exception {
        LoginVO user = (LoginVO) EgovUserDetailsHelper.getAuthenticatedUser();
        AbbreviatedReportVO temp = abbreviatedReportService.searchUsersTrainingAndTeamId(user.getUniqId());

        vo.setTrainId(temp.getTrainId());
        vo.setTeamId(temp.getTeamId());
        vo.setCreateUserId(user.getUniqId());

        // 첨부파일 서버 업로드
        List<FileVO> result = null;
        String atchFileId = "";
        final List<MultipartFile> files = multiRequest.getFiles("file_1");
        if (!files.isEmpty()) {
            result = fileUtil.parseFileInf(files, "REPORT_", 0, "", "");
            atchFileId = fileMngService.insertFileInfs(result);
        }

        vo.setAttachFileId(atchFileId);
        abbreviatedReportService.abbreviatedReportInsert(vo);
        return "forward:/report/abbreviatedReportList.do";
    }

    // 보고서 update 매핑
    @RequestMapping(value = "/report/abbreviatedReportUpdate.do")
    public String abbreviatedReportUpdate(final MultipartHttpServletRequest multiRequest, @ModelAttribute("reportVO") AbbreviatedReportVO vo, ModelMap modelMap) throws Exception {
        final List<MultipartFile> files = multiRequest.getFiles("file_1");
        String atchFileId = vo.getAttachFileId();

        if (!files.isEmpty()) {
            if (atchFileId == null || "".equals(atchFileId)) {
                List<FileVO> result = fileUtil.parseFileInf(files, "REPORT_", 0, atchFileId, "");
                atchFileId = fileMngService.insertFileInfs(result);
                vo.setAttachFileId(atchFileId);
            } else {
                FileVO fvo = new FileVO();
                fvo.setAtchFileId(atchFileId);
                int cnt = fileMngService.getMaxFileSN(fvo);
                List<FileVO> _result = fileUtil.parseFileInf(files, "REPORT_", cnt, atchFileId, "");
                fileMngService.updateFileInfs(_result);
            }
        }

        abbreviatedReportService.abbreviatedReportUpdate(vo);
        return "forward:/report/abbreviatedReportList.do";
    }

    @RequestMapping(value = "/report/abbreviatedReportDelete.do")
    public String abbreviatedReportDelete(@ModelAttribute("reportVO") AbbreviatedReportVO vo, ModelMap modelMap) throws Exception {
        AbbreviatedReportVO reportVO = abbreviatedReportService.abbreviatedReportDetail(vo);
        abbreviatedReportService.abbreviatedReportDelete(reportVO);
        return "forward:/report/abbreviatedReportList.do";
    }

    // 사용자의 보고서 제출
    @RequestMapping(value = "/report/abbreviatedReportSubmit.do")
    public String abbreviatedReportSubmit(@ModelAttribute("reportVO") AbbreviatedReportVO vo, ModelMap modelMap) throws Exception {
        // 해당 reportId의 reportStatus를 200 ("관리자에게 제출")로 변경
        abbreviatedReportService.abbreviatedReportSubmit(vo);
        return "forward:/report/abbreviatedReportList.do";
    }

    // 관리자의 사용자 보고서 재검토 요청
    @RequestMapping(value = "/report/abbreviatedReportReject.do")
    public String abbreviatedReportReject(@ModelAttribute("rejectReport") AbbreviatedReportVO vo) throws Exception {
        abbreviatedReportService.abbreviatedReportReject(vo);
        // TODO 메시지전송 기능 개발 완료 시, 메시지전송 기능 화면으로 return 해줘야 함.
        return "forward:/report/abbreviatedReportList.do";
    }

    // 관리자의 사용자 보고서 검토 이후 점수 부여 요청
    @RequestMapping(value = "/report/abbreviatedReportGiveScore.do")
    public String abbreviatedReportGiveScore(@RequestParam(value = "score", required = false, defaultValue = "0") int score, @ModelAttribute("giveScoreToReport") AbbreviatedReportVO vo) throws Exception {
        abbreviatedReportService.abbreviatedReportGiveScore(vo, score);
        return "forward:/report/abbreviatedReportList.do";
    }

    // 관리자으의 사용자 보고서 점수 부여 취소 요청
    @RequestMapping(value="/report/abbreviatedReportUndoGiveScore.do")
    public String abbreviatedReportUndoGiveScore(@ModelAttribute("undoGiveScore") AbbreviatedReportVO vo) throws Exception {
        abbreviatedReportService.abbreviatedReportUndoGiveScore(vo);
        return "forward:/report/abbreviatedReportList.do";
    }
}
