package egovframework.com.login.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import egovframework.com.login.service.LoginService;
import egovframework.com.login.service.LoginVO;
import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;

@Service("loginService")
public class LoginServiceImpl extends EgovAbstractServiceImpl implements LoginService {
	
	@Resource(name="loginMapper")
	private LoginMapper loginDAO;
	
	@Override
	public String selectLoginCheck(LoginVO loginVO) {
		return loginDAO.selectLoginCheck(loginVO);
	}	
	
}
