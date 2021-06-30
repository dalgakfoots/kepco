package kepco.com.login.service;

import kepco.com.cmm.KepcoLoginVO;

public interface KepcoLoginService {
	
	// 로그인 처리
	public KepcoLoginVO actionLogin(KepcoLoginVO vo) throws Exception;
	
}
