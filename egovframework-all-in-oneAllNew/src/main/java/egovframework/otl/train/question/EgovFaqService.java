package egovframework.otl.train.question;

import egovframework.rte.fdl.cmmn.exception.FdlException;

import java.util.HashMap;
import java.util.List;

public interface EgovFaqService {

	List<?> selectFaqList(FaqVO searchVO);

	int selectFaqListCnt(FaqVO searchVO);

	FaqVO selectFaqDetail(FaqVO searchVO) throws Exception;

	void insertFaq(FaqVO faqVO) throws FdlException;

	void updateFaq(FaqVO faqVO);

	void deleteFaq(FaqVO faqVO);

	List<?> selectFaqGroupList(FaqGroupVO searchVO) throws Exception;

	int selectFaqGroupListCnt(FaqGroupVO searchVO) throws Exception;

	void insertFaqGroup(FaqGroupVO faqGroupVO) throws Exception;

	FaqGroupVO selectFaqGroup(FaqGroupVO faqGroupVO) throws Exception;

	void deleteFaqGroup(FaqGroupVO faqGroupVO) throws Exception;

	void updateFaqGroup(FaqGroupVO faqGroupVO) throws Exception;

	void deleteFaqGroupRelation(FaqGroupVO faqGroupVO) throws Exception;

	void insertFaqGroupRelation(FaqGroupRelationVO vo) throws Exception;

	List<HashMap> selectFaqGroupRelationList(FaqGroupVO faqGroupVO) throws Exception;

}
