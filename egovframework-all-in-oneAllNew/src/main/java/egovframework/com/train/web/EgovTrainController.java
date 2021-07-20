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
		LoginVO user = (LoginVO)EgovUserDetailsHelper.getAuthenticatedUser();
		// 문제id를 통해 문제의 제목 , 문제 내용 조회하여 화면으로 전달
		HashMap param = new HashMap();
		param.put("faqId", frm.getFaqId());
		
		HashMap detail = egovTrainService.selectQuestionDetail(param);
		model.addAttribute("detail", detail);
		model.addAttribute("trainTypeName", frm.getTrainTypeName());
		// 사용자의 아이디, 문제 아이디를 통해 해당 문제를 사용자의 그룹이 몇번이나 풀었는지 확인
		HashMap submitCntMap = new HashMap();
		submitCntMap.put("esntlId", user.getUniqId());
		submitCntMap.put("quizId", frm.getFaqId());
		String submitCnt = egovTrainService.selectSubmitCnt(submitCntMap);
		
		// '사용자'가 제출한 답 중 최신 것을 들고옴. 없으면 빈값 ""
		String userAnswer = egovTrainService.selectUserAnswer(submitCntMap);
		if (userAnswer == null || userAnswer.equals("")) {
			userAnswer = "";
		}
		
		//사용자가 속한 그룹이 해당 문제를 풀이완료 하였는지 확인
		String isFinish = egovTrainService.selectQuestionFinishYnByUserId(submitCntMap);
		isFinish = isFinish == null ? "" : isFinish;
		model.addAttribute("isFinish", isFinish);
		model.addAttribute("submitCnt", submitCnt);
		model.addAttribute("userAnswer", userAnswer);
		model.addAttribute("frm", frm);
		
		return "egovframework/com/utl/train/selectQuestionDetail";
	}
	
	@RequestMapping("/train/submitAnswer.do")
	public String submitAnswer(@ModelAttribute("frm") EgovTrainVO frm , ModelMap model) throws Exception {
		LoginVO user = (LoginVO)EgovUserDetailsHelper.getAuthenticatedUser();
		String esntlId = user.getUniqId();
		String faqId = frm.getFaqId();
		
		HashMap esntlIdMap = new HashMap();
		esntlIdMap.put("esntlId", esntlId);
		HashMap groupIdMap = egovTrainService.selectUserVmGroupId(esntlIdMap);
		
		String groupId = (String) groupIdMap.get("GROUP_ID");
		
		String answer = frm.getAnswer() == null? "" : frm.getAnswer();
		// 문제에 대한 사용자의 답변을 저장
		HashMap param = new HashMap();
		// 사용자가 답  
		param.put("id", egovQuizUserAnswerManageIdGnrService.getNextStringId());
		param.put("esntlId", esntlId);
		param.put("faqId", faqId);
		param.put("answer", answer);
		param.put("groupId", groupId);
		/* TODO 최대 제출 횟수를 넘지 않으면 insert 넘었다면 alert을 화면에 날린다. */
		
		// 1. 해당 문제의 최대제출횟수 확인
		try {
			HashMap maxSubmitCntMap = egovTrainService.selectQuestionDetail(param);
			int maxSubmitCnt = (int) maxSubmitCntMap.get("MAX_SUBMIT_CNT");
			String questionType = (String) maxSubmitCntMap.get("TYPE"); // 해당 문제가 QUIZ 인지 QUESTION 인지 확인
			// 2. 해당 사용자가 속한 그룹의 해당 문제 풀이 횟수  확인
			HashMap userSubmitCntMap = new HashMap();
			userSubmitCntMap.put("esntlId", esntlId);
			userSubmitCntMap.put("quizId", faqId);
			int userSubmitCnt = Integer.parseInt(egovTrainService.selectSubmitCnt(userSubmitCntMap));
			String isFinished = egovTrainService.selectQuestionFinishYnByUserId(userSubmitCntMap);
			isFinished = isFinished == null ? "" : isFinished;
			// 3. 비교하여 그룹의 해당 문제 풀이 횟수가 해당 문제의 최대제출횟수를 넘지 않았는지 && 타입이 QUIZ인지 확인한다.
			if(isFinished.equals("")) { // 사용자의 그룹이 해당 문제를 완료 하였는지 체크한다.
				if(maxSubmitCnt > userSubmitCnt && questionType.equals("QUIZ")) {
					egovTrainService.insertUserAnswer(param);
				}else {
					model.addAttribute("msg", "해당 문제의 최대 제출 횟수를 초과하였습니다.");
				}
			}else {
				model.addAttribute("msg", "해당 문제는 풀이 완료되었습니다.");
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		return "forward:/train/enterExam.do";
	}
	
	@RequestMapping("/train/finishQuestion.do")
	public String finishQuestion(@ModelAttribute("frm") EgovTrainVO frm , ModelMap model) throws Exception{
		
		//현재 사용자가 속한 그룹이 해당 문제를 풀이완료 하였는지 확인 로직
		LoginVO user = (LoginVO)EgovUserDetailsHelper.getAuthenticatedUser();
		String esntlId = user.getUniqId();
		HashMap esntlIdMap = new HashMap();
		esntlIdMap.put("esntlId", esntlId);
		
		HashMap groupIdMap = egovTrainService.selectUserVmGroupId(esntlIdMap);
		String groupId = (String) groupIdMap.get("GROUP_ID");
		String trainingId = (String) groupIdMap.get("TRAINING_ID");
		HashMap param = new HashMap();
		param.put("groupId", groupId);
		param.put("quizId", frm.getFaqId());
		
		String finishYn = egovTrainService.selectQuestionFinishYn(param);
		finishYn = finishYn == null? "" : finishYn;
		try {
		if(finishYn.equals("") || finishYn == null || finishYn.equals("N")) {
		//풀이완료 하지 않았다면
			
			//현재 사용자가 속한 그룹이 해당 문제를 푼 것에 대한 점수 계산 로직
			HashMap resultMap = egovTrainService.selectUserGroupQuestionScore(param);
			/*
			 * resultMap
			 * "QUIZ_ID" , "GROUP_ID", "SCORE", "DEDUCT_SCORE"
			 * 
			 * */
			Long temp = (long)resultMap.get("SCORE");
			int score = temp.intValue();
			temp = (long)resultMap.get("DEDUCT_SCORE");
			int deductScore = temp.intValue();
			int tot = score - deductScore;
			tot = tot < 0 ? 0 : tot;
			resultMap.put("trainId", trainingId);
			resultMap.put("scoreId", 1);
			resultMap.put("TOT", tot);
			resultMap.put("trainType", frm.getTrainType());
			resultMap.put("userId", esntlId);
			
			//kepco_training_team_scores 테이블에 {훈련id , 그룹id, 문제id, (임의의)score_id , 트레이닝타입(한글), 점수, 사용자id} insert
			egovTrainService.insertUserGroupQuestionScore(resultMap);
			//해당 문제가 풀이 완료 되었음을 KEPCO_GROUP_QUIZ_INFO 테이블에 INSERT
			egovTrainService.insertQuestionFinishYn(resultMap);
		}
		}catch(Exception e) {
			e.printStackTrace();
		}
		//풀이완료 했다면
		model.addAttribute("msg", "해당 문제의 풀이를 완료하였습니다.");
		return "forward:/train/enterExam.do";
	}
	
}
