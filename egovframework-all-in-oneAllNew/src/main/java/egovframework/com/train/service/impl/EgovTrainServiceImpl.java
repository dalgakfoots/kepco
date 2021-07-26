package egovframework.com.train.service.impl;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.ibm.icu.text.SimpleDateFormat;

import egovframework.com.train.service.EgovTrainService;
import egovframework.com.train.service.EgovTrainTimeSettingVO;

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

	@Override
	public HashMap selectTrainingTimeSetting() throws Exception {
		return egovTrainDAO.selectTrainingTimeSetting();
	}

	@Override
	public List<HashMap> selectTrainingIdList() throws Exception {
		return egovTrainDAO.selectTrainingIdList();
	}

	@Override
	public void setTrainingTimeSetting(EgovTrainTimeSettingVO frm) throws Exception {
		frm = setTrainingTotalTime(frm);
		egovTrainDAO.setTrainingTimeSetting(frm);
	}
	
	
	private EgovTrainTimeSettingVO setTrainingTotalTime(EgovTrainTimeSettingVO frm) throws Exception {

		SimpleDateFormat time = new SimpleDateFormat("HH:mm");

		List<String> startTempTimeList = new ArrayList();
		startTempTimeList.add(frm.getPstStartDatetime());
		startTempTimeList.add(frm.getMdtStartDatetime());
		startTempTimeList.add(frm.getWatStartDatetime());
		startTempTimeList.add(frm.getAstStartDatetime());
		startTempTimeList.add(frm.getSrgStartDatetime());
		
		String startTempTime = "23:59";
		for (String startTime : startTempTimeList) {
			int compare = time.parse(startTempTime).compareTo(time.parse(startTime));
			if (compare > 0) {
				startTempTime = startTime;
			}
		}
		
		List<String> endTempTimeList = new ArrayList();
		endTempTimeList.add(frm.getPstEndDatetime());
		endTempTimeList.add(frm.getMdtEndDatetime());
		endTempTimeList.add(frm.getWatEndDatetime());
		endTempTimeList.add(frm.getAstEndDatetime());
		endTempTimeList.add(frm.getSrgEndDatetime());
		
		String endTempTime = "00:00";
		for (String endTime : endTempTimeList) {
			int compare = time.parse(endTempTime).compareTo(time.parse(endTime));
			if (compare < 0) {
				endTempTime = endTime;
			}
		}
	
		
		SimpleDateFormat dateTime = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		SimpleDateFormat dateTime2 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		
		String trainingDate = frm.getTrainingDate();
		
		System.out.println("frm.getTrainingDate() : " + frm.getTrainingDate());
		
		Date startDatetime = dateTime.parse(trainingDate+" "+startTempTime);
		Date endDatetime = dateTime.parse(trainingDate+" "+endTempTime);
		
		frm.setStartDatetime(dateTime2.format(startDatetime));
		frm.setEndDatetime(dateTime2.format(endDatetime));
		
		System.out.println("dateTime2.format(startDatetime) : " + dateTime2.format(startDatetime));
		System.out.println("dateTime2.format(endDatetime) : " + dateTime2.format(endDatetime));
		return frm;
	}

	@Override
	public String selectCurrentExamAvailable(HashMap trainType) throws Exception {
		return egovTrainDAO.selectCurrentExamAvailable(trainType);
	}

	@Override
	public void updateWatExamOpenTime(HashMap param) throws Exception {
		egovTrainDAO.updateWatExamOpenTime(param);
	}

	@Override
	public String selectRealAnswer(HashMap param) throws Exception {
		return egovTrainDAO.selectRealAnswer(param);
	}

}
