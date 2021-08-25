package egovframework.otl.train.train;

import egovframework.com.uss.ion.ecc.service.EventCmpgnVO;
import egovframework.com.uss.ion.ecc.service.TnextrlHrVO;
import egovframework.com.uss.ion.ecc.service.impl.EgovEventCmpgnDAO;
import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;
import egovframework.rte.fdl.cmmn.exception.FdlException;
import egovframework.rte.fdl.idgnr.EgovIdGnrService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.text.ParseException;
import java.util.List;

@Service("EgovEventCmpgnService")
public class EgovEventCmpgnServiceImpl extends EgovAbstractServiceImpl implements EgovEventCmpgnService {

	@Resource(name="EgovEventCmpgnDAO")
	private EgovEventCmpgnDAO egovEventCmpgnDao;

	@Resource(name="egovEventInfoIdGnrService")
	private EgovIdGnrService idgenService1;

	@Resource(name="egovExtrlhrInfoIdGnrService")
	private EgovIdGnrService idgenService2;
	
	@Override
	public List<?> selectEventCmpgnList(EventCmpgnVO searchVO) {
		return egovEventCmpgnDao.selectEventCmpgnList(searchVO);
	}

	@Override
	public List<?> selectEventCmpgnListForMonitor() {
		return egovEventCmpgnDao.selectEventCmpgnListForMonitor();
	}
	
	@Override
	public List<?> selectEventCmpgnListForScoreView(EventCmpgnVO searchVO) {
		return egovEventCmpgnDao.selectEventCmpgnList(searchVO);
	}

	@Override
	public int selectEventCmpgnListCnt(EventCmpgnVO searchVO) {
		return egovEventCmpgnDao.selectEventCmpgnListCnt(searchVO);
	}

	@Override
	public void insertEventCmpgn(EventCmpgnVO eventCmpgnVO) throws Exception{
		String eventId = idgenService1.getNextStringId();
		eventCmpgnVO.setEventId(eventId);
		
//		SimpleDateFormat dateForm1 = new SimpleDateFormat("yyyy-MM-dd HH:mm");
//		SimpleDateFormat dateForm2 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//		Date tempDate = null;
//		
//		String startDatetime = eventCmpgnVO.getEventSvcBeginDe() + " " + eventCmpgnVO.getEventSvcBeginTime();
//		tempDate = dateForm1.parse(startDatetime);
//		eventCmpgnVO.setEventSvcBeginDe(dateForm2.format(tempDate));
//		
//		String endDatatime = eventCmpgnVO.getEventSvcEndDe() + " " + eventCmpgnVO.getEventSvcEndTime();
//		tempDate = dateForm1.parse(endDatatime);
//		eventCmpgnVO.setEventSvcEndDe(dateForm2.format(tempDate));
		
		egovEventCmpgnDao.insertEventCmpgn(eventCmpgnVO);
		
		
//		insertTrainingTime(eventCmpgnVO.getEventId(), tempDate);
		
	}
	
//	private void insertTrainingTime(String trainingId, Date start) {
//		SimpleDateFormat dateForm = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//		Map param = new HashMap();
//		
//		Calendar cal = Calendar.getInstance();
//		cal.setTime(start);
//		
//		
//		param.put("trainingId", trainingId);
//		param.put("start", dateForm.format(start));
//		
//		
//
//		cal.add(Calendar.MINUTE, 90);
//		param.put("start", dateForm.format(cal.getTime()));
//
//		cal.add(Calendar.MINUTE, 90);
//		param.put("start", dateForm.format(cal.getTime()));
//
//		cal.add(Calendar.MINUTE, 90);
//		param.put("start", dateForm.format(cal.getTime()));
//		
//		cal.add(Calendar.MINUTE, 90);
//		param.put("start", dateForm.format(cal.getTime()));
//		
//		
//	}

	@Override
	public EventCmpgnVO selectEventCmpgnDetail(EventCmpgnVO eventCmpgnVO) throws Exception {
		EventCmpgnVO resultVO = egovEventCmpgnDao.selectEventCmpgnDetail(eventCmpgnVO);
		
		String[] sptitedStartDatetime = resultVO.getEventSvcBeginDe().split(" ");
		if (sptitedStartDatetime.length > 0) resultVO.setEventSvcBeginDe(sptitedStartDatetime[0]);
		if (sptitedStartDatetime.length > 1) resultVO.setEventSvcBeginTime(sptitedStartDatetime[1]);
		
		
		String[] sptitedEndDatetime = resultVO.getEventSvcEndDe().split(" ");
		if (sptitedEndDatetime.length > 0) resultVO.setEventSvcEndDe(sptitedEndDatetime[0]);
		if (sptitedEndDatetime.length > 1) resultVO.setEventSvcEndTime(sptitedEndDatetime[1]);
		
		
        if (resultVO == null)
            throw processException("info.nodata.msg");
        
        return resultVO;
	}

	@Override
	public void updateEventCmpgn(EventCmpgnVO eventCmpgnVO) throws ParseException {
		
//		SimpleDateFormat dateForm1 = new SimpleDateFormat("yyyy-MM-dd HH:mm");
//		SimpleDateFormat dateForm2 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//		Date tempDate = null;
//		
//		String startDatetime = eventCmpgnVO.getEventSvcBeginDe() + " " + eventCmpgnVO.getEventSvcBeginTime();
//		tempDate = dateForm1.parse(startDatetime);
//		eventCmpgnVO.setEventSvcBeginDe(dateForm2.format(tempDate));
//		
//		String endDatatime = eventCmpgnVO.getEventSvcEndDe() + " " + eventCmpgnVO.getEventSvcEndTime();
//		tempDate = dateForm1.parse(endDatatime);
//		eventCmpgnVO.setEventSvcEndDe(dateForm2.format(tempDate));
		egovEventCmpgnDao.updateEventCmpgn(eventCmpgnVO);
	}

	@Override
	public void deleteEventCmpgn(EventCmpgnVO eventCmpgnVO) {
		//행사/이벤트/캠페인에 속한 외부인사정보를 삭제한다.
		egovEventCmpgnDao.deleteEventCmpgnTnextrlHr(eventCmpgnVO);

		//행사/이벤트/캠페인을 삭제한다.
		egovEventCmpgnDao.deleteEventCmpgn(eventCmpgnVO);
		
	}

	@Override
	public List<?> selectTnextrlHrList(TnextrlHrVO searchVO) {
		return egovEventCmpgnDao.selectTnextrlHrList(searchVO);
	}

	@Override
	public int selectTnextrlHrListCnt(TnextrlHrVO searchVO) {
		return egovEventCmpgnDao.selectTnextrlHrListCnt(searchVO);
	}

	@Override
	public void insertTnextrlHr(TnextrlHrVO tnextrlHrVO) throws FdlException {
		String extrlHrId = idgenService2.getNextStringId();
		tnextrlHrVO.setExtrlHrId(extrlHrId);
		
		egovEventCmpgnDao.insertTnextrlHr(tnextrlHrVO);
	}

	@Override
	public TnextrlHrVO selectTnextrlHrDetail(TnextrlHrVO tnextrlHrVO) throws Exception {
		TnextrlHrVO resultVO = egovEventCmpgnDao.selectTnextrlHrDetail(tnextrlHrVO);
        if (resultVO == null)
            throw processException("info.nodata.msg");
        return resultVO;
	}

	@Override
	public void updateTnextrlHr(TnextrlHrVO tnextrlHrVO) {
		egovEventCmpgnDao.updateTnextrlHr(tnextrlHrVO);
	}

	@Override
	public void deleteTnextrlHr(TnextrlHrVO tnextrlHrVO) {
		egovEventCmpgnDao.deleteTnextrlHr(tnextrlHrVO);
	}

}
