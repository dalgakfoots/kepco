package kepco.com.login.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import egovframework.com.cmm.LoginVO;
import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;
import kepco.com.cmm.KepcoLoginVO;
import kepco.com.login.service.KepcoLoginService;

@Service("kepcoLoginService")
public class KepcoLoginServiceImpl extends EgovAbstractServiceImpl implements KepcoLoginService {
	
	@Resource(name="kepcoLoginDAO")
	private KepcoLoginDAO kepcoLoginDAO;
	
	@Override
	public KepcoLoginVO actionLogin(KepcoLoginVO vo) throws Exception {
		
		String email = vo.getEmail();
		String password = vo.getPassword();
		
		KepcoLoginVO loginVO = kepcoLoginDAO.actionLogin(vo);
		
		if (loginVO != null && !loginVO.getId().equals("") && !loginVO.getPassword().equals("")) {
			System.out.println(loginVO.toString());
    		return loginVO;
    	} else {
    		System.out.println("is empty");
    		loginVO = new KepcoLoginVO();
    	}
		return loginVO;
		
	}

}
