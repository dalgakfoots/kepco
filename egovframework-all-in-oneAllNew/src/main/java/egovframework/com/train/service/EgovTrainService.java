package egovframework.com.train.service;

import java.util.HashMap;
import java.util.List;

public interface EgovTrainService {

	List<HashMap> selectUserVmLists(HashMap param) throws Exception;

	HashMap selectUserVmGroupId(HashMap param) throws Exception;

	List<HashMap> selectUserExamList(HashMap param) throws Exception;

	HashMap selectQuestionDetail(HashMap param) throws Exception;

	void insertUserAnswer(HashMap param) throws Exception;

}
