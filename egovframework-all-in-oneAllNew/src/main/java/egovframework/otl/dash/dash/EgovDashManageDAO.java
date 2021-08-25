package egovframework.otl.dash.dash;

import egovframework.com.cmm.service.impl.EgovComAbstractDAO;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 개요
 * - 지식전문가에 대한 DAO 클래스를 정의한다.
 *
 * 상세내용
 * - 지식전문가에 대한 등록, 수정, 삭제, 조회 기능을 제공한다.
 * - 지식전문가의 조회기능은 목록조회, 상세조회로 구분된다.
 * @author 박종선
 * @version 1.0
 * @created 12-8-2010 오후 3:44:52
 */

@Repository("EgovDashManageDAO")
public class EgovDashManageDAO extends EgovComAbstractDAO {

	/**
	 * 등록된 지식전문가 정보를 조회 한다.
	 * @param KnoSpecialistVO- 지식전문가 VO
	 * @return String - 지식전문가 목록
	 *
	 * @param KnoSpecialistVO
	 */
	public List<Map> selectTrainingTeams(String trainingId) throws Exception {
		return  selectList("EgovDashManageDAO.selectTrainingTeams", trainingId);

	}
	public String selectTrainingName(String trainingId) throws Exception {
		return selectOne("EgovDashManageDAO.selectTrainingName", trainingId);
	}
	
	public HashMap selectDashTable(String trainingId, String teamId) throws Exception {
		HashMap model = new HashMap();
		model.put("trainingId", trainingId);
		model.put("teamId", teamId);
		return selectOne("EgovDashManageDAO.selectDashTable", model);
	}
	
	public List<Map> selectDashTableList(String trainingId) throws Exception {
		return selectList("EgovDashManageDAO.selectDashTableList", trainingId);
	}


	public void insertDashScore(HashMap model) throws Exception {
		insert("EgovDashManageDAO.insertDashScore", model);
	}
	
	public int selectTeamTotalScoreByDateTime(String trainingId, String teamId, String date) throws Exception {
		Map param = new HashMap();
		param.put("trainingId", trainingId);
		param.put("teamId", teamId);
		param.put("selectTime", date);
		return selectOne("EgovDashManageDAO.selectTeamTotalScoreByDateTime", param);
	}
	
	public List<Map> selectScoreLogList (String trainingId, String teamId) throws Exception {
		Map param = new HashMap();
		param.put("trainingId", trainingId);
		param.put("teamId", teamId);
		return selectList("EgovDashManageDAO.selectScoreLogList", param);
	}
	
	public List<Map> selectScoreLogListForDeduction (String trainingId, String trainingType) throws Exception {
		Map param = new HashMap();
		param.put("trainingId", trainingId);
		param.put("trainingType", trainingType);
		return selectList("EgovDashManageDAO.selectScoreLogListForDeduction", param);
	}
	
	public List<Map> selectSortedTeamIdByRank(String trainingId) {
		return selectList("EgovDashManageDAO.selectSortedTeamIdByRank", trainingId);
		
	}
	
	public List<Map> selectSortedTeamIdByRankList(String trainingId) {
		return selectList("EgovDashManageDAO.selectSortedTeamIdByRankList", trainingId);
		
	}
	
	public String selectTeamIdByUserId(String userId) {
		return selectOne("EgovDashManageDAO.selectTeamIdByUserId", userId);
	}
	
	public void insertEgovDeductionScore(String trainingId, String teamId, String score) {
		Map param = new HashMap();
		param.put("trainingId", trainingId);
		param.put("teamId", teamId);
		param.put("score", score);
		param.put("questionId", "vm_reboot");
		param.put("trainingType", "vm복구");
		insert("EgovDashManageDAO.insertDashScore", param);
	}
	
	public Map selectTrainingTimeByTrainingId(String trainingId) {
		return selectOne("EgovDashManageDAO.selectTrainingTimeByTrainingId", trainingId);
	}
}