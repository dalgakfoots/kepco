package egovframework.com.uss.ion.ecc.service;

import java.text.ParseException;
import java.util.List;

import egovframework.rte.fdl.cmmn.exception.FdlException;

public interface EgovEventCmpgnService {

	List<?> selectEventCmpgnList(EventCmpgnVO searchVO);
	List<?> selectEventCmpgnListForMonitor();
	List<?> selectEventCmpgnListForScoreView(EventCmpgnVO searchVO);

	int selectEventCmpgnListCnt(EventCmpgnVO searchVO);

	void insertEventCmpgn(EventCmpgnVO eventCmpgnVO) throws Exception;

	EventCmpgnVO selectEventCmpgnDetail(EventCmpgnVO eventCmpgnVO) throws Exception;

	void updateEventCmpgn(EventCmpgnVO eventCmpgnVO) throws ParseException;

	void deleteEventCmpgn(EventCmpgnVO eventCmpgnVO);

	List<?> selectTnextrlHrList(TnextrlHrVO searchVO);

	int selectTnextrlHrListCnt(TnextrlHrVO searchVO);

	void insertTnextrlHr(TnextrlHrVO tnextrlHrVO)  throws Exception;

	TnextrlHrVO selectTnextrlHrDetail(TnextrlHrVO tnextrlHrVO) throws Exception;

	void updateTnextrlHr(TnextrlHrVO tnextrlHrVO);

	void deleteTnextrlHr(TnextrlHrVO tnextrlHrVO);

}
