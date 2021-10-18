package egovframework.com.dash.service.impl;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;

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

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
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
		
//		List<Map> trainingTeams = selectTrainingTeams(trainingId);
//		for (Map map : trainingTeams) {
//			String teamId = (String) map.get("team_id");
//			rankList.add(egovDashManageDAO.selectDashTable(trainingId, teamId));
//		}
		return egovDashManageDAO.selectDashTableList(trainingId);
	}

	@Override
	public ModelAndView selectDashGragh(String trainingId, ModelAndView resultMap) throws Exception {
		int intervalTime = 1;
		
		
		// 현재시각과 trainingId로 훈련 시작 시간을 불러온다. 
			SimpleDateFormat dateTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			SimpleDateFormat time = new SimpleDateFormat("HH:mm");
			SimpleDateFormat time2 = new SimpleDateFormat("HH:mm:ss");
			
			
			Map trainingTime = egovDashManageDAO.selectTrainingTimeByTrainingId(trainingId);
			Date standardTime = dateTime.parse(trainingTime.get("start_datetime").toString());
			Date endtime = dateTime.parse(trainingTime.get("end_datetime").toString());
			Date today = new Date();
//			System.out.println("standardTime : "+ standardTime);
//			System.out.println("endtime : "+ endtime);
//			System.out.println("today : "+ today);
			
			
			int compare = today.compareTo(endtime);
			if (compare > 0) {
//				System.out.println("today > endtime");
			} else {
//				System.out.println("today <= endtime");
				endtime = today;
			}
			
		//현재시각과 훈련시작시각의 차이를 구한다. (분 단위)
			long diff = endtime.getTime()- standardTime.getTime();
			long min = (diff / 1000) / 60;
			int intervalCount = ((int) min)/ intervalTime ; 
//			System.out.println("min : "+ min);
//			System.out.println("intervalCount : " + intervalCount);
			
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
//		   System.out.println("splitedDateList : "+ splitedDateList);
//		   System.out.println("dashGraghColumn : "+ dashGraghColumn);
		   

		   
		// 훈련에 속한 팀리스트를 불러온다.
		   List<Map> graghList = new ArrayList();
//		   List<Map> trainingTeams = selectTrainingTeams(trainingId);
//		   List<Map> trainingTeams = egovDashManageDAO.selectSortedTeamIdByRank(trainingId);
		   List<Map> trainingTeams = egovDashManageDAO.selectSortedTeamIdByRankList(trainingId);
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
	public String selectTeamIdByUserId(String userId) throws Exception {
		return egovDashManageDAO.selectTeamIdByUserId(userId);
	}
	
	@Override
	public List<Map> selectScoreLogList(String trainingId, String teamId) throws Exception {
		final List<Map> scoreLogList = egovDashManageDAO.selectScoreLogList(trainingId, teamId);
		return scoreLogList;
	}
	
	@Override
	public List<Map> selectScoreLogListForDeduction(String trainingId) throws Exception {
		final List<Map> scoreLogList = egovDashManageDAO.selectScoreLogListForDeduction(trainingId, "vm복구");
		return scoreLogList;
	}
	
	@Override
	public Map selectCurrentScore(String trainingId, String teamId) throws Exception {
		final Map currentScore = egovDashManageDAO.selectDashTable(trainingId, teamId);
		return currentScore;
	}
	@Override
	public void insertEgovDeductionScore(String trainingId, String teamId, String score) {
		egovDashManageDAO.insertEgovDeductionScore(trainingId, teamId, score);
	}
	
	
	@Override
	public void insertDashScore(String trainingId, HashMap model) throws Exception {
		
		
		model.put("trainingId", trainingId);
		List<Map> trainingTeams = selectTrainingTeams(trainingId);
		int index = 0;
		for (Map map : trainingTeams) {
			index++;
			model.put("teamId", map.get("team_id"));
			String question = "test001_";
			
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
				model.put("score", (int)(dValue * 1000));
				egovDashManageDAO.insertDashScore(model);	
			}
		}
	}
	
	
	@Override
	public void plcTimerOn(final String trainingId) throws ParseException {
		
		final Timer timer = new Timer();
		final TimerTask timerTask = new TimerTask() {
            @Override
            public void run() {
//            	startTime과 endTime을 가지고 온다.
            	Map trainingTime = egovDashManageDAO.selectTrainingTimeByTrainingId(trainingId);
				
            	try {
					long isEnd = compareWithNow(trainingTime.get("end_datetime").toString());
					long isStart = compareWithNow(trainingTime.get("start_datetime").toString());

					if(isStart < 0 && 0 < isEnd ) {
	                    System.out.println("훈련 중...");
	                    readyForParamByPlcApi(trainingId);
	                } 
					else if (trainingTime.get("scheduling_state").toString().equals("N")) {
	                	System.out.println("훈련 끝.");
	                	timer.cancel();
	                } 
					else {
	                	System.out.println("훈련전 or 훈련끝");
	                }
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (JsonProcessingException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
            }
        };
        timer.schedule(timerTask, 0, 1000);
	}

	
	// 현재시간과 훈련끝시간을 계산.
	// 현재시간과 훈련시작시간을 계산.
	private long compareWithNow(String time) throws ParseException {
		SimpleDateFormat datetimeForm = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date convertedTime = datetimeForm.parse(time);
		Date now = new Date();
		
		long diff = convertedTime.getTime() - now.getTime();
		
		return diff;
	}
	

// 디테일한 서비스 로직.
		
	// 훈련에 속한팀들의 totalScore, teamId, 공격 스택, 방어스택에 대한 정보를 수집.
	private void readyForParamByPlcApi(String trainingId) throws JsonProcessingException {
		List<Map> objectForPlcList = egovDashManageDAO.selectForParamByPlcApi(trainingId);
		String jsonForPlcParam = convertJsonByObject(objectForPlcList);
		plcRestApiCall(jsonForPlcParam);
	}
	
	// json파일 형식으로 convert
	private String convertJsonByObject(List<Map> objectForPlcList) throws JsonProcessingException {
		String jsonForPlcParam = new ObjectMapper().writeValueAsString(objectForPlcList);
		System.out.println("jsonForPlcParam : " + jsonForPlcParam);
		return jsonForPlcParam;
	}
	
	// json파일을 보내야하는 restAPI call
	private void plcRestApiCall(String jsonForPlcParam) {
		System.out.println("plcRestApiCall");
//		
//		
//		try {
//			URL url = new URL("");
//			HttpURLConnection con = (HttpURLConnection) url.openConnection();
//			con.setConnectTimeout(5000); //서버에 연결되는 Timeout 시간 설정
//			con.setReadTimeout(5000); // InputStream 읽어 오는 Timeout 시간 설정
//			con.addRequestProperty("x-api-key", RestTestCommon.API_KEY); //key값 설정
//
//			con.setRequestMethod("POST");
//
//                                     //json으로 message를 전달하고자 할 때 
//			con.setRequestProperty("Content-Type", "application/json");
//			con.setDoInput(true);
//			con.setDoOutput(true); //POST 데이터를 OutputStream으로 넘겨 주겠다는 설정 
//			con.setUseCaches(false);
//			con.setDefaultUseCaches(false);
//
//			OutputStreamWriter wr = new OutputStreamWriter(con.getOutputStream());
//			wr.write(jsonForPlcParam); //json 형식의 message 전달 
//			wr.flush();
//
//			StringBuilder sb = new StringBuilder();
//			if (con.getResponseCode() == HttpURLConnection.HTTP_OK) {
//				
//				BufferedReader br = new BufferedReader(
//						new InputStreamReader(con.getInputStream(), "utf-8"));
//				String line;
//				while ((line = br.readLine()) != null) {
//					sb.append(line).append("\n");
//				}
//				br.close();
//				System.out.println("" + sb.toString());
//			} else {
//				System.out.println(con.getResponseMessage());
//			}
//		} catch (Exception e){
//			System.err.println(e.toString());
//		}

	}

}