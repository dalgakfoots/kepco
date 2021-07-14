package egovframework.com.dash.service.impl;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import egovframework.com.dam.spe.spe.service.EgovKnoSpecialistService;
import egovframework.com.dam.spe.spe.service.KnoSpecialist;
import egovframework.com.dam.spe.spe.service.KnoSpecialistVO;
import egovframework.com.dam.spe.spe.service.impl.KnoSpecialistDAO;
import egovframework.com.dash.service.EgovDashManageService;
import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.ui.ModelMap;
import org.springframework.web.servlet.ModelAndView;

import com.ibm.icu.text.SimpleDateFormat;

/**
 * 개요
 * - 지식전문가에 대한 ServiceImpl 클래스를 정의한다.
 *
 * 상세내용
 * - 지식전문가에 대한 등록, 수정, 삭제, 조회 기능을 제공한다.
 * - 지식전문가의 조회기능은 목록조회, 상세조회로 구분된다.
 * @author 박종선
 * @version 1.0
 * @created 12-8-2010 오후 3:44:43
 */

@Service("EgovDashManageService")
public class EgovDashManageServiceImpl extends EgovAbstractServiceImpl implements EgovDashManageService {
	@Resource(name="EgovDashManageDAO")
	private EgovDashManageDAO egovDashManageDAO;
	
	
	
	@Override
	public List<Map> selectTrainingTeams(String trainingId) throws Exception {
		
		return egovDashManageDAO.selectTrainingTeams(trainingId);
	}

	@Override
	public String selectTrainingName(String trainingId) throws Exception {
		// TODO Auto-generated method stub
		return egovDashManageDAO.selectTrainingName(trainingId);
	}

	@Override
	public List<?> selectDashTable(String trainingId) throws Exception {
		// score table에 있는 score들을 타입별로 더해서가져와
		List<Map> rankList = new ArrayList();
		
		List<Map> trainingTeams = selectTrainingTeams(trainingId);
		for (Map map : trainingTeams) {
			String teamId = (String) map.get("team_id");
			rankList.add(egovDashManageDAO.selectDashTable(trainingId, teamId));
		}

		for (int i=0 ; i < (rankList.size()-1) ; i++) {
			
			int item1 = Integer.parseInt(rankList.get(i).get("total").toString());
			int item2 = Integer.parseInt(rankList.get(i+1).get("total").toString());
			Map temp = new HashMap();
			
			if (item1 < item2) {
				temp = rankList.get(i+1);
				rankList.remove(i+1);
				rankList.add(i,temp);
				i=0;
			}
		}
		
		return rankList;
	}

	@Override
	public ModelAndView selectDashGragh(String trainingId, ModelAndView resultMap) throws Exception {
		int intervalTime = 5;
		
		// 현재시각과 trainingId로 훈련 시작 시간을 불러온다. 
			SimpleDateFormat dateTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			SimpleDateFormat time = new SimpleDateFormat("HH:mm");
			SimpleDateFormat time2 = new SimpleDateFormat("HH:mm:ss");
			Date standardTime = dateTime.parse("2021-07-14 15:00:00");
			Date today = new Date();
			
			
		//현재시각과 훈련시작시각의 차이를 구한다. (분 단위)
			long diff = today.getTime()- standardTime.getTime();
			long min = (diff / 1000) / 60;
			int intervalCount = ((int) min)/ intervalTime +1; 
			System.out.println("min : "+ min);
			System.out.println("intervalCount : " + intervalCount);
			
		// 시작 시간부터 현재시간을 인터벌 시간으로 나누어 splitedList를 만든다.
		   Calendar cal = Calendar.getInstance();
		   cal.setTime(standardTime);
		   
		   List<Date> splitedDateList = new ArrayList();
		   List<String> dashGraghColumn = new ArrayList();
		   for (int i=0 ; i<=intervalCount ; i++) {
			   if (i > 0) {
				   cal.add(Calendar.MINUTE, intervalTime);
			   }	   
			   splitedDateList.add(cal.getTime());
			   String column = time.format(cal.getTime());
			   dashGraghColumn.add(column);
			   
		   }
		   System.out.println("splitedDateList : "+ splitedDateList);
		   System.out.println("dashGraghColumn : "+ dashGraghColumn);
		   

		   
		// 훈련에 속한 팀리스트를 불러온다.
		   List<Map> graghList = new ArrayList();
//		   List<Map> trainingTeams = selectTrainingTeams(trainingId);
		   List<Map> trainingTeams = egovDashManageDAO.selectSortedTeamIdByRank(trainingId);
		   for (Map map : trainingTeams) {
			   String teamId = (String) map.get("team_id");
			   String teamName = (String) map.get("team_name");
			  
			   Map graghData = new HashMap();
			   graghData.put("team_name", teamName);
			   
			   List<Integer> scoreDataList = new ArrayList(); 
			   for (Date date : splitedDateList) {
				  String selectTime = dateTime.format(date);
				  int score = egovDashManageDAO.selectTeamTotalScoreByDateTime(trainingId, teamId, selectTime);
				  scoreDataList.add(score);
			   }
			   
			   System.out.println("scoreDataList : "+ scoreDataList);
			   graghData.put("data", scoreDataList);
			   graghList.add(graghData);
		   }
		   
		   resultMap.addObject("graghList", graghList);
		   resultMap.addObject("dateList", dashGraghColumn);
		   resultMap.addObject("updateTime", time2.format(today));
	
		   
	
		return resultMap;
	}
	
	
	
	@Override
	public void insertDashScore(String trainingId, HashMap model) throws Exception {
		
		
		model.put("trainingId", trainingId);
		List<Map> trainingTeams = selectTrainingTeams(trainingId);
		int index = 0;
		for (Map map : trainingTeams) {
			index++;
			model.put("teamId", map.get("team_id"));
			String question = "test007_";
			
			for (int i =0 ; i<5 ; i++) {
				switch (i) {
					case 0:
						model.put("questionId", question+index+i);
						model.put("trainingType", "예방보안");
						break;
					case 1:
						model.put("questionId", question+index+i);
						model.put("trainingType", "실시간대응");
						break;
					case 2:
						model.put("questionId", question+index+i);
						model.put("trainingType", "사후대응");
						break;
					case 3: 
						model.put("questionId", question+index+i);
						model.put("trainingType", "vm복구");
						break;
					case 4:
						model.put("questionId", question+index+i);
						model.put("trainingType", "가용성");
						break;
				}
				
				double dValue = Math.random();
				model.put("score", (int)(dValue * 100));
				egovDashManageDAO.insertDashScore(model);	
			}
		}
		
		
		
		
		
	}




}