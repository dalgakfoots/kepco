package egovframework.com.train.service;

import java.util.HashMap;
import java.util.List;

public interface EgovTrainService {

	List<HashMap> selectUserVmLists(HashMap param) throws Exception;

	HashMap selectUserVmGroupId(HashMap param) throws Exception;

	List<HashMap> selectUserExamList(HashMap param) throws Exception;

	HashMap selectQuestionDetail(HashMap param) throws Exception;

	String selectSubmitCnt(HashMap submitCntMap) throws Exception;
	
	void insertUserAnswer(HashMap param) throws Exception;

	String selectUserAnswer(HashMap submitCntMap) throws Exception;

	String selectQuestionFinishYn(HashMap finishYnMap) throws Exception;

	HashMap selectUserGroupQuestionScore(HashMap param) throws Exception;

	void insertUserGroupQuestionScore(HashMap resultMap) throws Exception;

	void insertQuestionFinishYn(HashMap resultMap) throws Exception;

	String selectQuestionFinishYnByUserId(HashMap submitCntMap) throws Exception;

	HashMap selectWatAdditionalScore(HashMap param) throws Exception;

	List<HashMap> selectUserWatExamList(HashMap param) throws Exception;



}
