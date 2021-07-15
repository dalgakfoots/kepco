package egovframework.com.train.web;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import egovframework.com.cmm.LoginVO;
import egovframework.com.cmm.util.EgovUserDetailsHelper;
import egovframework.com.train.service.EgovTrainService;

@Controller
public class EgovTrainController {
	
	
	@Resource(name="EgovTrainService")
	EgovTrainService egovTrainService;
	
	@RequestMapping(value="/train/enterTrainingSystem.do")
	public String enterTrainingSystem(@RequestParam("trainType") String trainType, ModelMap model ) throws Exception {
		
		LoginVO user = (LoginVO)EgovUserDetailsHelper.getAuthenticatedUser();
		// 현재 로그인 한 유저의 esntl_id, trainType을 통해 
		HashMap param = new HashMap();
		String esntlId = user.getUniqId();
		param.put("esntlId", esntlId);
		/*
		 * trainType
		 * pst : 예방보안훈련 (Prevention Security Training)
		 * mdt : 악성코드탐지대응훈련 (Malware Detection Training)
		 * wat : 웹공격대응훈련 (Web Attack response Training)
		 * 
		 * */
		param.put("trainType", trainType);
		// trainType에 맞는 vmGroupId를 가지고 온다.
		param.put("vmGroupId", egovTrainService.selectUserVmGroupId(param).get("VM_GROUP_ID"));
		/*Service 기능*/
		// 1. group_id를 조회
		// 2. 조회된 group_id, trainType을 통해 해당하는  pst , mdt, wat vm그룹-vm 리스트를 가지고옴.
		List<HashMap> userVmLists = egovTrainService.selectUserVmLists(param);
		
		for(HashMap a : userVmLists) {
			Iterator it = a.entrySet().iterator();
			while(it.hasNext()) {
				System.out.println(it.next());
			}
		}
		
		// trainType을 view 쪽으로 던져줌.
		// vm 리스트를 view 쪽으로 던져줌.
		model.addAttribute("trainType", trainType);
		model.addAttribute("userVmLists", userVmLists);
		
		return "egovframework/com/utl/train/EnterTrainingSystem";
	}
	
}
