package egovframework.com.utl.socket.web;

import java.util.Iterator;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import egovframework.com.cmm.LoginVO;
import egovframework.com.cmm.util.EgovUserDetailsHelper;

@Controller
public class SocketTestController {
	
	@RequestMapping(value = "/utl/socket/adminChat.do")
	public String adminChat() {
		return "egovframework/com/utl/socket/adminChat";
	}
	
	@RequestMapping(value = "/utl/socket/userChat.do")
	public String userChat(HttpServletRequest request ,ModelMap model) {
		LoginVO user = (LoginVO)EgovUserDetailsHelper.getAuthenticatedUser();
		model.addAttribute("user", user);
		
		return "egovframework/com/utl/socket/userChat";
	}
	
}
