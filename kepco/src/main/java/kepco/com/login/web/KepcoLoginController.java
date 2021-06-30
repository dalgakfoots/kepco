package kepco.com.login.web;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import kepco.com.cmm.KepcoLoginVO;
import kepco.com.login.service.KepcoLoginService;

@Controller
public class KepcoLoginController {
	
	@Resource(name = "kepcoLoginService")
	private KepcoLoginService kepcoLoginService;
	
	@RequestMapping(value = "/com/login/loginPage.do")
	public String loginPage() {
		return "com/login/Login";
	}
	
	@RequestMapping(value = "/com/login/actionLogin.do")
	public String actionLogin(@ModelAttribute("kepcoLoginVO") KepcoLoginVO kepcoLoginVO, ModelMap model) throws Exception {
		
		KepcoLoginVO loginVO = kepcoLoginService.actionLogin(kepcoLoginVO);
		
		if(loginVO != null && loginVO.getEmail() != null && !loginVO.getEmail().equals("")) {
			
			return "forward:/let/main/mainPage.do";
		}
		
		
		return "com/login/Login";
		
	}
}
