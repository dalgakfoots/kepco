package egovframework.com.login.service.impl;

import egovframework.com.login.service.LoginVO;
import egovframework.rte.psl.dataaccess.mapper.Mapper;

@Mapper("loginMapper")
public interface LoginMapper {

	String selectLoginCheck(LoginVO loginVO);
	
}
