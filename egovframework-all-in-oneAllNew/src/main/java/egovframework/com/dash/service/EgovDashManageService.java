package egovframework.com.dash.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.ui.ModelMap;
import org.springframework.web.servlet.ModelAndView;

public interface EgovDashManageService {

	List<Map> selectTrainingTeams(String trainingId) throws Exception;
	String selectTrainingName(String trainingId) throws Exception;
	
	
	List<?> selectDashTable(String trainingId) throws Exception;
	ModelAndView selectDashGragh(String trainingId, ModelAndView modelAndView) throws Exception;
	
	void insertDashScore( String trainingId, HashMap model) throws Exception;
}