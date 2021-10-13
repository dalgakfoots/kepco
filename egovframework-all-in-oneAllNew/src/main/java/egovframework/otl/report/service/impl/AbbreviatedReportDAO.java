package egovframework.otl.report.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import egovframework.com.cmm.service.impl.EgovComAbstractDAO;
import egovframework.otl.report.service.AbbreviatedReportVO;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("AbbreviatedReportDAO")
public class AbbreviatedReportDAO extends EgovComAbstractDAO {

    public List<AbbreviatedReportVO> abbreviatedReportList(HashMap param){
        return selectList("AbbreviatedReportDAO.abbreviatedReportList",param);
    }

    public AbbreviatedReportVO abbreviatedReportDetail(AbbreviatedReportVO vo) {
        return selectOne("AbbreviatedReportDAO.abbreviatedReportDetail",vo);
    }

    public void abbreviatedReportInsert(AbbreviatedReportVO vo) {
        try {
            insert("AbbreviatedReportDAO.abbreviatedReportInsert", vo);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public AbbreviatedReportVO searchUsersTrainingAndTeamId(String userId) {
        return selectOne("AbbreviatedReportDAO.searchUsersTrainingAndTeamId",userId);
    }

    public int abbreviatedReportCount(HashMap parameterMap) {
        return (Integer) selectOne("AbbreviatedReportDAO.abbreviatedReportCount", parameterMap);
    }

    public void abbreviatedReportSubmit(AbbreviatedReportVO vo) {
        update("AbbreviatedReportDAO.abbreviatedReportSubmit", vo);
    }

    public void abbreviatedReportUpdate(AbbreviatedReportVO vo) {
        update("AbbreviatedReportDAO.abbreviatedReportUpdate", vo);
    }

    public void abbreviatedReportDelete(AbbreviatedReportVO vo) {
        delete("AbbreviatedReportDAO.abbreviatedReportDelete", vo);
    }

    public void abbreviatedReportChangeStatus(AbbreviatedReportVO vo) {
        update("AbbreviatedReportDAO.abbreviatedReportChangeStatus", vo);
    }

    public void abbreviatedReportGiveScore(HashMap param) {
        insert("AbbreviatedReportDAO.abbreviatedReportGiveScore", param);
    }

    public void abbreviatedReportUndoGiveScore(AbbreviatedReportVO vo) {
        delete("AbbreviatedReportDAO.abbreviatedReportUndoGiveScore", vo);
    }

    public List<HashMap> searchTeamIdList(String trainId) {
        return selectList("AbbreviatedReportDAO.searchTeamIdList",trainId);
    }
}
