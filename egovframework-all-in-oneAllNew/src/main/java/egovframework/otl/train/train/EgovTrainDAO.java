package egovframework.otl.train.train;

import egovframework.com.cmm.service.impl.EgovComAbstractDAO;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;

@Repository("EgovTrainDAO")
public class EgovTrainDAO extends EgovComAbstractDAO {

	public List<HashMap> selectUserVMLists(HashMap param) {
		return selectList("egovTrainDAO.selectUserVMLists", param);
	}

	public HashMap selectUserVmGroupId(HashMap param) {
		return selectOne("egovTrainDAO.selectUserVmGroupId", param);
	}

	public List<HashMap> selectUserExamList(HashMap param) {
		return selectList("egovTrainDAO.selectUserExamList",param);
	}

	public HashMap selectQuestionDetail(HashMap param) {
		return selectOne("egovTrainDAO.selectQuestionDetail",param);
	}

	public void insertUserAnswer(HashMap param) {
		insert("egovTrainDAO.insertUserAnswer", param);
	}

	public String selectSubmitCnt(HashMap submitCntMap) {
		return selectOne("egovTrainDAO.selectSubmitCnt",submitCntMap);
	}

	public String selectUserAnswer(HashMap submitCntMap) {
		return selectOne("egovTrainDAO.selectUserAnswer", submitCntMap);
	}

	public String selectQuestionFinishYn(HashMap finishYnMap) {
		return selectOne("egovTrainDAO.selectQuestionFinishYn", finishYnMap);
	}

	public HashMap selectUserGroupQuestionScore(HashMap param) {
		return selectOne("egovTrainDAO.selectUserGroupQuestionScore",param);
	}

	public void insertUserGroupQuestionScore(HashMap resultMap) {
		insert("egovTrainDAO.insertUserGroupQuestionScore", resultMap);
	}

	public void insertQuestionFinishYn(HashMap resultMap) {
		insert("egovTrainDAO.insertQuestionFinishYn",resultMap);
	}

	public String selectQuestionFinishYnByUserId(HashMap submitCntMap) {
		return selectOne("egovTrainDAO.selectQuestionFinishYnByUserId", submitCntMap);
	}

	public HashMap selectWatAdditionalScore(HashMap param) {
		return selectOne("egovTrainDAO.selectWatAdditionalScore",param);
	}

	public List<HashMap> selectUserWatExamList(HashMap param) {
		return selectList("egovTrainDAO.selectUserWatExamList",param);
	}

	public HashMap selectTrainingTimeSetting(String trainingId) {
		return selectOne("egovTrainDAO.selectTrainingTimeSetting",trainingId);
	}

	public List<HashMap> selectTrainingIdList() {
		return selectList("egovTrainDAO.selectTrainingIdList");
	}

	public void setTrainingTimeSetting(EgovTrainTimeSettingVO frm) {
		insert("egovTrainDAO.setTrainingTimeSetting",frm);
	}

	public String selectCurrentExamAvailable(HashMap trainType) {
		return selectOne("egovTrainDAO.selectCurrentExamAvailable", trainType);
	}

	public void updateWatExamOpenTime(HashMap param) {
		update("egovTrainDAO.updateWatExamOpenTime", param);
	}

	public String selectRealAnswer(HashMap param) {
		return selectOne("egovTrainDAO.selectRealAnswer",param);
	}

	public List selectSrgRealAnswer(HashMap param) {
		return selectList("egovTrainDAO.selectSrgRealAnswer", param);
	}

	

}
