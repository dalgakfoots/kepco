package egovframework.otl.report.service;

import java.util.HashMap;
import java.util.List;

public interface AbbreviatedReportService {
    public List<AbbreviatedReportVO> abbreviatedReportList(HashMap parameterMap) throws Exception; // 보고서 리스트 조회

    public AbbreviatedReportVO abbreviatedReportDetail(AbbreviatedReportVO vo) throws Exception; // 보고서 Detail 조회

    public void abbreviatedReportInsert(AbbreviatedReportVO vo) throws Exception; // 보고서 insert

    /*기타 기능*/
    public AbbreviatedReportVO searchUsersTrainingAndTeamId(String userId) throws Exception;

    int abbreviatedReportCount(HashMap parameterMap) throws Exception;

    void abbreviatedReportSubmit(AbbreviatedReportVO vo) throws Exception;

    void abbreviatedReportUpdate(AbbreviatedReportVO vo) throws Exception;

    void abbreviatedReportDelete(AbbreviatedReportVO vo) throws Exception;

    void abbreviatedReportReject(AbbreviatedReportVO vo) throws Exception;

    void abbreviatedReportGiveScore(AbbreviatedReportVO vo, int score) throws Exception;

    void abbreviatedReportUndoGiveScore(AbbreviatedReportVO vo) throws Exception;

    List<HashMap> searchTeamIdList(String trainId) throws Exception;
}
