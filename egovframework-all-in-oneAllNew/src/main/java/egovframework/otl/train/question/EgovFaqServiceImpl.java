package egovframework.otl.train.question;

import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;
import egovframework.rte.fdl.cmmn.exception.FdlException;
import egovframework.rte.fdl.idgnr.EgovIdGnrService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;

@Service("EgovFaqService")
public class EgovFaqServiceImpl extends EgovAbstractServiceImpl implements EgovFaqService {

	@Resource(name = "EgovFaqDAO")
	private EgovFaqDAO egovFaqDao;

	/** ID Generation */
	@Resource(name = "egovFaqManageIdGnrService")
	private EgovIdGnrService idgenService;
	
	@Override
	public List<?> selectFaqList(FaqVO searchVO) {
		return egovFaqDao.selectFaqList(searchVO);
	}

	@Override
	public int selectFaqListCnt(FaqVO searchVO) {
		return egovFaqDao.selectFaqListCnt(searchVO);
	}

	@Override
	public FaqVO selectFaqDetail(FaqVO searchVO) throws Exception {
		
		//조회수 증가
		//egovFaqDao.updateFaqInqireCo(searchVO);
		
		FaqVO resultVO = egovFaqDao.selectFaqDetail(searchVO);
		if (resultVO == null)
			throw processException("info.nodata.msg");
		return resultVO;
	}

	@Override
	public void insertFaq(FaqVO faqVO) throws FdlException {
		String faqId = idgenService.getNextStringId();
		faqVO.setFaqId(faqId);
		egovFaqDao.insertFaq(faqVO);
	}

	@Override
	public void updateFaq(FaqVO faqVO) {
		egovFaqDao.updateFaq(faqVO);
	}

	@Override
	public void deleteFaq(FaqVO faqVO) {
		egovFaqDao.deleteFaq(faqVO);
	}

	@Override
	public List<?> selectFaqGroupList(FaqGroupVO searchVO) throws Exception {
		return egovFaqDao.selectFaqGroupList(searchVO);
	}

	@Override
	public int selectFaqGroupListCnt(FaqGroupVO searchVO) throws Exception {
		return egovFaqDao.selectFaqGroupListCnt(searchVO);
	}

	@Override
	public void insertFaqGroup(FaqGroupVO faqGroupVO) throws Exception {
		egovFaqDao.insertFaqGroup(faqGroupVO);
	}

	@Override
	public FaqGroupVO selectFaqGroup(FaqGroupVO faqGroupVO) throws Exception {
		return egovFaqDao.selectFaqGroup(faqGroupVO);
	}

	@Override
	public void deleteFaqGroup(FaqGroupVO faqGroupVO) throws Exception {
		egovFaqDao.deleteFaqGroup(faqGroupVO);
	}

	@Override
	public void updateFaqGroup(FaqGroupVO faqGroupVO) throws Exception {
		egovFaqDao.updateFaqGroup(faqGroupVO);
	}

	@Override
	public void deleteFaqGroupRelation(FaqGroupVO faqGroupVO) throws Exception {
		egovFaqDao.deleteFaqGroupRelation(faqGroupVO);
	}

	@Override
	public void insertFaqGroupRelation(FaqGroupRelationVO vo) throws Exception {
		egovFaqDao.insertFaqGroupRelation(vo);
	}

	@Override
	public List<HashMap> selectFaqGroupRelationList(FaqGroupVO faqGroupVO) throws Exception {
		return egovFaqDao.selectFaqGroupRelationList(faqGroupVO);
	}

}
