package egovframework.com.train.web;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import egovframework.com.cmm.LoginVO;
import egovframework.com.cmm.util.EgovUserDetailsHelper;
import egovframework.com.train.service.EgovTrainService;
import egovframework.com.train.service.EgovTrainVO;
import egovframework.rte.fdl.idgnr.EgovIdGnrService;

@Controller
public class EgovTrainController {
	
	
	@Resource(name="EgovTrainService")
	EgovTrainService egovTrainService;
	
	@Resource(name = "egovQuizUserAnswerManageIdGnrService")
	private EgovIdGnrService egovQuizUserAnswerManageIdGnrService;
	
	@RequestMapping(value="/train/enterTrainingSystem.do")
	public String enterTrainingSystem(@RequestParam(value = "trainType", required=false, defaultValue="pst") String trainType, ModelMap model ) throws Exception {
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
		 * ast : 사후대응훈련 (After Situation Training)
		 * 
		 * */
		param.put("trainType", trainType);
		// trainType에 맞는 vmGroupId를 가지고 온다.
		param.put("vmGroupId", egovTrainService.selectUserVmGroupId(param).get("VM_GROUP_ID"));
		/*Service 기능*/
		// 1. group_id를 조회
		// 2. 조회된 group_id, trainType을 통해 해당하는  pst , mdt, wat vm그룹-vm 리스트를 가지고옴.
		List<HashMap> userVmLists = egovTrainService.selectUserVmLists(param);
		
		String trainTypeName = "";
		
		// trainType을 view 쪽으로 던져줌.
		// vm 리스트를 view 쪽으로 던져줌.
		if(trainType.equals("pst")) {
			trainTypeName = "예방보안훈련";
		}else if(trainType.equals("mdt")) {
			trainTypeName = "악성코드탐지대응훈련";
		}else if(trainType.equals("wat")) {
			trainTypeName = "Web공격대응훈련";
		}else if(trainType.equals("ast")) {
			trainTypeName = "사후대응훈련";
		}
		model.addAttribute("trainType", trainType);
		model.addAttribute("trainTypeName", trainTypeName);
		model.addAttribute("userVmLists", userVmLists);
		
		if(trainType.equals("ast")) {
			return "forward:/train/enterExam.do";
		}
		
		return "egovframework/com/utl/train/EnterTrainingSystem";
	}
	
	@RequestMapping("/train/enterExam.do")
	//public String enterExam(@RequestParam(value = "trainType") String trainType, ModelMap model ) throws Exception {
	public String enterExam(@ModelAttribute("frm") EgovTrainVO frm , ModelMap model ) throws Exception {
		LoginVO user = (LoginVO)EgovUserDetailsHelper.getAuthenticatedUser();
		
		// (풀이현황 데이터 조회 <- 세부사항이 명확하지 않음.)
		
		// 사용자의 아이디, 문제 타입 (pst, mdt, wat, ast)를 통해 문제 리스트를 불러온다.
		HashMap param = new HashMap();
		param.put("esntlId", user.getUniqId());
		param.put("trainType", frm.getTrainType());
		
		String trainType = frm.getTrainType();
		String trainTypeName ="";
		if(trainType.equals("pst")) {
			trainTypeName = "예방보안훈련";
		}else if(trainType.equals("mdt")) {
			trainTypeName = "악성코드탐지대응훈련";
		}else if(trainType.equals("wat")) {
			trainTypeName = "Web공격대응훈련";
		}else if(trainType.equals("ast")) {
			trainTypeName = "사후대응훈련";
		}
		
		List<HashMap> examList = egovTrainService.selectUserExamList(param);
		
		model.addAttribute("examList", examList);
		model.addAttribute("trainTypeName", trainTypeName);
		model.addAttribute("trainType", trainType);
		
		model.addAttribute("frm", frm);
		return "egovframework/com/utl/train/EnterExamSystem";
	}
	
	@RequestMapping("/train/selectQuestionDetail.do")
	//public String selectQuestionDetail(@RequestParam("faqId") String faqId , @RequestParam("trainType") String trainType ,@RequestParam("trainTypeName") String trainTypeName ,ModelMap model) throws Exception {
	public String selectQuestionDetail(@ModelAttribute("frm") EgovTrainVO frm ,ModelMap model) throws Exception {	
		// 문제id를 통해 문제의 제목 , 문제 내용 조회하여 화면으로 전달
		HashMap param = new HashMap();
		param.put("faqId", frm.getFaqId());
		
		HashMap detail = egovTrainService.selectQuestionDetail(param);
		model.addAttribute("detail", detail);
		model.addAttribute("trainTypeName", frm.getTrainTypeName());
		
		model.addAttribute("frm", frm);
		
		return "egovframework/com/utl/train/selectQuestionDetail";
	}
	
	@RequestMapping("/train/submitAnswer.do")
	public String submitAnswer(@ModelAttribute("frm") EgovTrainVO frm , ModelMap model) throws Exception {
		LoginVO user = (LoginVO)EgovUserDetailsHelper.getAuthenticatedUser();
		String esntlId = user.getUniqId();
		String faqId = frm.getFaqId();
		String answer = frm.getAnswer() == null? "" : frm.getAnswer();
		// 문제에 대한 사용자의 답변을 저장
		HashMap param = new HashMap();
		// 사용자가 답  
		param.put("id", egovQuizUserAnswerManageIdGnrService.getNextStringId());
		param.put("esntlId", esntlId);
		param.put("faqId", faqId);
		param.put("answer", answer);
		
		egovTrainService.insertUserAnswer(param);
		
		return "forward:/train/enterExam.do";
	}
}
