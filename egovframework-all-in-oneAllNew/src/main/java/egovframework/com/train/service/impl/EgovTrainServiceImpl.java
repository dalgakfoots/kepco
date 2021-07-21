package egovframework.com.train.service.impl;

import java.util.HashMap;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import egovframework.com.train.service.EgovTrainService;

@Service("EgovTrainService")
public class EgovTrainServiceImpl implements EgovTrainService {
	
	@Resource(name="EgovTrainDAO")
	EgovTrainDAO egovTrainDAO;
	
	@Override
	public List<HashMap> selectUserVmLists(HashMap param) throws Exception {
		return egovTrainDAO.selectUserVMLists(param);
	}

	@Override
	public HashMap selectUserVmGroupId(HashMap param) throws Exception {
		return egovTrainDAO.selectUserVmGroupId(param);
	}

	@Override
	public List<HashMap> selectUserExamList(HashMap param) throws Exception {
		return egovTrainDAO.selectUserExamList(param);
	}

	@Override
	public HashMap selectQuestionDetail(HashMap param) throws Exception {
		return egovTrainDAO.selectQuestionDetail(param);
	}

	@Override
	public void insertUserAnswer(HashMap param) throws Exception {
		egovTrainDAO.insertUserAnswer(param);
	}

	@Override
	public String selectSubmitCnt(HashMap submitCntMap) throws Exception {
		return egovTrainDAO.selectSubmitCnt(submitCntMap);
	}

	@Override
	public String selectUserAnswer(HashMap submitCntMap) throws Exception {
		return egovTrainDAO.selectUserAnswer(submitCntMap);
	}

	@Override
	public String selectQuestionFinishYn(HashMap finishYnMap) throws Exception {
		return egovTrainDAO.selectQuestionFinishYn(finishYnMap);
	}

	@Override
	public HashMap selectUserGroupQuestionScore(HashMap param) throws Exception {
		return egovTrainDAO.selectUserGroupQuestionScore(param);
	}

	@Override
	public void insertUserGroupQuestionScore(HashMap resultMap) throws Exception {
		egovTrainDAO.insertUserGroupQuestionScore(resultMap);
	}

	@Override
	public void insertQuestionFinishYn(HashMap resultMap) throws Exception {
		egovTrainDAO.insertQuestionFinishYn(resultMap);
	}

	@Override
	public String selectQuestionFinishYnByUserId(HashMap submitCntMap) throws Exception {
		return egovTrainDAO.selectQuestionFinishYnByUserId(submitCntMap);
	}

	@Override
	public HashMap selectWatAdditionalScore(HashMap param) throws Exception {
		return egovTrainDAO.selectWatAdditionalScore(param);
	}

	@Override
	public List<HashMap> selectUserWatExamList(HashMap param) throws Exception {
		return egovTrainDAO.selectUserWatExamList(param);
	}

}
