package egovframework.com.dash.service.impl;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
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
import egovframework.com.dash.service.PlcApiVO;
import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;
import org.springframework.stereotype.Service;
import org.springframework.ui.ModelMap;
import org.springframework.web.servlet.ModelAndView;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ibm.icu.text.SimpleDateFormat;
import com.sun.star.io.IOException;


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

			
			int compare = today.compareTo(endtime);
			if (compare > 0) {
			} else {
				endtime = today;
			}
			
		//현재시각과 훈련시작시각의 차이를 구한다. (분 단위)
			long diff = endtime.getTime()- standardTime.getTime();
			long min = (diff / 1000) / 60;
			int intervalCount = ((int) min)/ intervalTime ; 
			
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
						model.put("trainingType", "ddos");
						break;
					case 1:
						model.put("questionId", question+index+i);
						model.put("trainingType", "ransom");
						break;
					case 2:
						model.put("questionId", question+index+i);
						model.put("trainingType", "wh");
						break;
					case 3: 
						model.put("questionId", question+index+i);
						model.put("trainingType", "apt01");
						break;
					case 4:
						model.put("questionId", question+index+i);
						model.put("trainingType", "apt02");
						break;
				}
				
				double dValue = Math.random();
				model.put("score", (int)(dValue * 100));
				egovDashManageDAO.insertDashScore(model);	
			}
		}
	}
	
	
//	@PostConstruct
	@Override
	public void plcTimerOn() throws ParseException{
		// select trainingId
		final String trainingId = egovDashManageDAO.selectTrainingId();
		final Timer timer = new Timer();
		final TimerTask timerTask = new TimerTask() {
            @Override
            public void run() {

            	try {
            		boolean isAction = validationActionTime(trainingId);

					if(isAction) {
	                    System.out.println("훈련 중...");
	                    readyForParamByPlcApi(trainingId);
	                } 
//					else if (trainingTime.get("scheduling_state").toString().equals("N")) {
//	                	System.out.println("훈련 끝.");
//	                	timer.cancel();
//	                } 
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
        timer.schedule(timerTask, 0, 1000 * 60);
	}
	
	
	
	private long compareWithNow(String time) throws ParseException {
		SimpleDateFormat datetimeForm = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date convertedTime = datetimeForm.parse(time);
		Date now = new Date();
		
		long diff = convertedTime.getTime() - now.getTime();
		
		return diff;
	}
	
	private String convertTimeByString(String time, String time2) throws ParseException {
		final SimpleDateFormat datetimeForm = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		final SimpleDateFormat convertForm = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String[] splitList = time2.split(" ");
		splitList[1] = time;
		time = String.join(" ", splitList);
		Date date = datetimeForm.parse(time);
		time = convertForm.format(date);
		
		return time;
	}
	
	@Override
	public void readyForParamByPlcApi(String trainingId) throws JsonProcessingException {
		List<PlcApiVO> plcApiVOList = egovDashManageDAO.selectForParamByPlcApi(trainingId);
		String jsonForPlcParam = convertJsonByObject(plcApiVOList);
		plcRestApiCall(jsonForPlcParam);
	}
	
	private String convertJsonByObject(List<PlcApiVO> plcApiVOList) throws JsonProcessingException {
		String jsonForPlcParam = new ObjectMapper().writeValueAsString(plcApiVOList);
		System.out.println("jsonForPlcParam : " + jsonForPlcParam);
		
		return jsonForPlcParam;
	}
	
	// json파일을 보내야하는 restAPI call
	@Deprecated 
	private void plcRestApiCall(String jsonForPlcParam) {
		System.out.println("plcRestApiCall");
		 HttpClient httpClient = HttpClientBuilder.create().build();
		try {
	
			    HttpPost request = new HttpPost("http://49.50.162.233:6969/dataset/insertion");
			    StringEntity params = new StringEntity("dataset="+ jsonForPlcParam);
			    request.addHeader("content-type", "application/x-www-form-urlencoded");
			    request.setEntity(params);
			    HttpResponse response = httpClient.execute(request);
				
		    } catch (Exception e) {
		        System.out.println("not JSON Format response");
		        e.printStackTrace();
		    } finally {
		    	httpClient.getConnectionManager().shutdown();
		    }
	}
	
	
//	private void plcRestApiCall(String jsonForPlcParam) throws Exception {
//		System.out.println("plcRestApiCall");
//
//		
//		HttpClient httpclient = HttpClients.createDefault();
//		HttpPost httppost = new HttpPost("http://www.a-domain.com/foo/");
//		
//		List<PlcApiVO> plcApiVOList = egovDashManageDAO.selectForParamByPlcApi("EVENT_00000000000181");
//		JSONObject test = new JSONObject();
//		test.append("dataset", plcApiVOList);
//		
//		
//		httppost.setEntity(new UrlEncodedFormEntity((List<? extends NameValuePair>) test, "UTF-8"));
//		
//		HttpResponse response = httpclient.execute(httppost);
//		HttpEntity entity = response.getEntity();
//		
//		System.out.println("response : " + response);
//
//		if (entity != null) {
//		    try (InputStream instream = entity.getContent()) {
//		        // do something useful
//		    }
//		}
//	}

	
	
	
		
		
	private boolean validationActionTime(String trainingId) throws ParseException {
		Map trainingTime = egovDashManageDAO.selectTrainingTimeByTrainingId(trainingId);
		boolean isAction = true;
		String startTime = trainingTime.get("start_datetime").toString();
		String endTime = trainingTime.get("end_datetime").toString();
		
		long isStart = compareWithNow(trainingTime.get("start_datetime").toString());
		long isEnd = compareWithNow(trainingTime.get("end_datetime").toString());
		
		String ddosStartTime = convertTimeByString(trainingTime.get("ddos_start_datetime").toString(), startTime);
		String ddosEndTime = convertTimeByString(trainingTime.get("ddos_end_datetime").toString(), endTime);
		String ransomStartTime = convertTimeByString(trainingTime.get("ransom_start_datetime").toString(), startTime);
		String ransomEndTime = convertTimeByString(trainingTime.get("ransom_end_datetime").toString(), endTime);
		String whStartTime = convertTimeByString(trainingTime.get("wh_start_datetime").toString(), startTime);
		String whEndTime = convertTimeByString(trainingTime.get("wh_end_datetime").toString(), endTime);
		String apt01StartTime = convertTimeByString(trainingTime.get("apt01_start_datetime").toString(), startTime);
		String apt01EndTime = convertTimeByString(trainingTime.get("apt01_end_datetime").toString(), endTime);
		String apt02StartTime = convertTimeByString(trainingTime.get("apt02_start_datetime").toString(), startTime);
		String apt02EndTime= convertTimeByString(trainingTime.get("apt02_end_datetime").toString(), endTime);
		
		long ddosIsStart = compareWithNow(ddosStartTime);
		long ddosIsEnd = compareWithNow(ddosEndTime);
		long ransomIsStart = compareWithNow(ransomStartTime);
		long ransomIsEnd = compareWithNow(ransomEndTime);
		long whIsStart = compareWithNow(whStartTime);
		long whIsEnd = compareWithNow(whEndTime);
		long apt01IsStart = compareWithNow(apt01StartTime);
		long apt01IsEnd = compareWithNow(apt01EndTime);
		long apt02IsStart = compareWithNow(apt02StartTime);
		long apt02IsEnd = compareWithNow(apt02EndTime);
		
		if (isStart < 0 && 0 < isEnd) {
			if (ddosIsStart <0 && 0 < ddosIsEnd) {
				isAction = true;
			} else if (ransomIsStart < 0 && 0 < ransomIsEnd) {
				isAction = true;
			} else if (whIsStart < 0 && 0 < whIsEnd) {
				isAction = true;
			} else if (apt01IsStart < 0 && 0 < apt01IsEnd) {
				isAction = true;	
			} else if (apt02IsStart < 0 && 0 < apt02IsEnd) {
				isAction = true;
			} else {
				isAction = false;
			}
		} else {
			isAction = false;
		}
		
		return isAction;
	}
	
	

}