package egovframework.com.uss.olh.faq.service.impl;

import java.util.HashMap;
import java.util.List;

import org.springframework.stereotype.Repository;

import egovframework.com.cmm.service.impl.EgovComAbstractDAO;
import egovframework.com.uss.olh.faq.service.FaqGroupRelationVO;
import egovframework.com.uss.olh.faq.service.FaqGroupVO;
import egovframework.com.uss.olh.faq.service.FaqVO;

@Repository("EgovFaqDAO")
public class EgovFaqDAO extends EgovComAbstractDAO {

	public List<?> selectFaqList(FaqVO searchVO) {
		return list("FaqManage.selectFaqList", searchVO);
	}

	public int selectFaqListCnt(FaqVO searchVO) {
		return (Integer) selectOne("FaqManage.selectFaqListCnt", searchVO);
	}

	public void updateFaqInqireCo(FaqVO searchVO) {
		update("FaqManage.updateFaqInqireCo", searchVO);
	}

	public FaqVO selectFaqDetail(FaqVO searchVO) {
		return (FaqVO) selectOne("FaqManage.selectFaqDetail", searchVO);
	}

	public void insertFaq(FaqVO faqVO) {
		insert("FaqManage.insertFaq", faqVO);
	}

	public void updateFaq(FaqVO faqVO) {
		update("FaqManage.updateFaq", faqVO);
	}

	public void deleteFaq(FaqVO faqVO) {
		delete("FaqManage.deleteFaq", faqVO);
	}

	public List<?> selectFaqGroupList(FaqGroupVO searchVO) {
		return selectList("FaqManage.selectFaqGroupList", searchVO);
	}

	public int selectFaqGroupListCnt(FaqGroupVO searchVO) {
		return (Integer) selectOne("FaqManage.selectFaqGroupListCnt", searchVO);
	}

	public void insertFaqGroup(FaqGroupVO faqGroupVO) {
		insert("FaqManage.insertFaqGroup", faqGroupVO);
	}

	public FaqGroupVO selectFaqGroup(FaqGroupVO faqGroupVO) {
		return selectOne("FaqManage.selectFaqGroup", faqGroupVO);
	}

	public void deleteFaqGroup(FaqGroupVO faqGroupVO) {
		delete("FaqManage.deleteFaqGroup", faqGroupVO);
	}

	public void updateFaqGroup(FaqGroupVO faqGroupVO) {
		update("FaqManage.updateFaqGroup", faqGroupVO);
	}

	public void deleteFaqGroupRelation(FaqGroupVO faqGroupVO) {
		delete("FaqManage.deleteFaqGroupRelation", faqGroupVO);
	}

	public void insertFaqGroupRelation(FaqGroupRelationVO vo) {
		insert("FaqManage.insertFaqGroupRelation", vo);
	}

	public List<HashMap> selectFaqGroupRelationList(FaqGroupVO faqGroupVO) {
		return selectList("FaqManage.selectFaqGroupRelationList", faqGroupVO);
	}

}
