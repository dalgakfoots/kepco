package egovframework.com.dash.service;

import java.text.ParseException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.codehaus.jettison.json.JSONException;
import org.springframework.ui.ModelMap;
import org.springframework.web.servlet.ModelAndView;

import com.fasterxml.jackson.core.JsonProcessingException;

public interface EgovDashManageService {

	List<Map> selectTrainingTeams(String trainingId) throws Exception;
	String selectTrainingName(String trainingId) throws Exception;
	
	
	List<?> selectDashTable(String trainingId) throws Exception;
	ModelAndView selectDashGragh(String trainingId, ModelAndView modelAndView) throws Exception;
	
	List<Map> selectScoreLogList(String trainingId, String teamId) throws Exception;
	List<Map> selectScoreLogListForDeduction(String trainingId) throws Exception;
	Map selectCurrentScore(String trainingId, String teamId) throws Exception;
	
	String selectTeamIdByUserId(String userId) throws Exception;
	void insertDashScore( String trainingId, HashMap model) throws Exception;
	void insertEgovDeductionScore(String trainingIs, String teamId, String score) throws Exception;
	void plcTimerOn() throws ParseException;
	void readyForParamByPlcApi(String trainingId) throws JsonProcessingException;
}