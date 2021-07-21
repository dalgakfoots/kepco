package egovframework.com.utl.socket.web;

import java.util.HashMap;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import egovframework.com.cmm.LoginVO;
import egovframework.com.cmm.util.EgovUserDetailsHelper;
import egovframework.com.train.service.EgovTrainService;



@Controller
public class SocketTestController {

	@Resource(name="EgovTrainService")
	EgovTrainService egovTrainService;

	
	@RequestMapping(value = "/utl/socket/adminChat.do")
	public String adminChat() {
		return "egovframework/com/utl/socket/adminChat";
	}
	
	@RequestMapping(value = "/utl/socket/userChat.do")
	public String userChat(HttpServletRequest request ,ModelMap model) throws Exception {
		LoginVO user = (LoginVO)EgovUserDetailsHelper.getAuthenticatedUser();
		HashMap param = new HashMap();
		param.put("esntlId", user.getUniqId());
		HashMap result = egovTrainService.selectUserVmGroupId(param);
		model.addAttribute("result", result);
		
		return "egovframework/com/utl/socket/userChat";
	}
	
}
