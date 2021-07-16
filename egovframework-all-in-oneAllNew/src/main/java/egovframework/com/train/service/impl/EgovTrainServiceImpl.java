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

}
