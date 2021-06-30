package kepco.com.login.service.impl;

import org.springframework.stereotype.Repository;

import egovframework.rte.psl.dataaccess.EgovAbstractDAO;
import kepco.com.cmm.KepcoLoginVO;

@Repository("kepcoLoginDAO")
public class KepcoLoginDAO extends EgovAbstractDAO {
	
	public KepcoLoginVO actionLogin(KepcoLoginVO vo) throws Exception {
		return (KepcoLoginVO)select("kepcoLoginDAO.actionLogin", vo);
	}
}
