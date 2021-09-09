package egovframework.otl.message.web;

import egovframework.com.cmm.LoginVO;
import egovframework.com.cmm.util.EgovUserDetailsHelper;
import egovframework.com.sec.rgm.service.EgovAuthorGroupService;
import egovframework.com.train.service.EgovTrainService;
import egovframework.otl.message.service.TrainMessageSearchVO;
import egovframework.otl.message.service.TrainMessageService;
import egovframework.otl.message.service.TrainMessageVO;
import egovframework.rte.fdl.idgnr.EgovIdGnrService;
import egovframework.rte.fdl.property.EgovPropertyService;
import egovframework.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;

@Controller
public class TrainMessageController {

    @Resource(name = "TrainMessageService")
    private TrainMessageService trainMessageService;

    @Resource(name = "trainMessageIdService")
    private EgovIdGnrService trainMessageIdGenerator;

    @Resource(name="EgovTrainService")
    private EgovTrainService egovTrainService;

    @Resource(name = "egovAuthorGroupService")
    private EgovAuthorGroupService egovAuthorGroupService;

    @Resource(name = "propertiesService")
    protected EgovPropertyService propertyService;

    // 전송된 메시지 리스트 요청
    @RequestMapping("/message/trainMessageList.do")
    public String trainMessageList(@ModelAttribute("searchForm") TrainMessageSearchVO searchVO, ModelMap model) throws Exception {

        System.out.println("searchVO.toString() = " + searchVO.toString());
        
        HashMap parameterMap = new HashMap();
        LoginVO user = (LoginVO) EgovUserDetailsHelper.getAuthenticatedUser();

        searchVO.setPageUnit(propertyService.getInt("pageUnit"));
        searchVO.setPageSize(propertyService.getInt("pageSize"));

        PaginationInfo paginationInfo = new PaginationInfo();
        paginationInfo.setCurrentPageNo(searchVO.getPageIndex());
        paginationInfo.setRecordCountPerPage(searchVO.getPageUnit());
        paginationInfo.setPageSize(searchVO.getPageSize());

        parameterMap.put("esntlId", user.getUniqId());
        parameterMap.put("recordCountPerPage", paginationInfo.getRecordCountPerPage());
        parameterMap.put("firstIndex", paginationInfo.getFirstRecordIndex());

        List<TrainMessageVO> messageList = trainMessageService.trainMessageList(parameterMap);
        HashMap role = egovAuthorGroupService.selectUserRole(parameterMap);
        paginationInfo.setTotalRecordCount(trainMessageService.trainMessageCount(parameterMap));


        model.addAttribute("messageList", messageList);
        model.addAttribute("role",role);
        model.addAttribute("paginationInfo", paginationInfo);

        return "egovframework/otl/message/TrainMessageList";
    }

    // 메시지 상세화면 요청
    @RequestMapping("/message/trainMessageDetail.do")
    public String trainMessageDetail(@ModelAttribute("messageDetail") TrainMessageVO vo, ModelMap model) throws Exception {
        TrainMessageVO messageVO = trainMessageService.trainMessageDetail(vo);
        model.addAttribute("messageVO", messageVO);
        return "egovframework/otl/message/TrainMessageDetail";
    }
    // 메시지 작성 폼 화면 요청
    @RequestMapping("/message/trainMessageWrite.do")
    public String trainMessageWrite(HttpServletRequest request, ModelMap model) throws Exception{
        try{
            String selectedTeamId = (String)request.getAttribute("selectedTeamId");
            if(selectedTeamId != null){
                model.addAttribute("selectedTeamId", selectedTeamId);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        model.addAttribute("teamList", trainMessageService.searchTeamIdList((String) egovTrainService.selectTrainingIdList().get(0).get("EVENT_ID")));
        return "egovframework/otl/message/TrainMessageWrite";
    }

    // 메시지 INSERT 요청
    @RequestMapping("/message/trainMessageSubmit.do")
    public String trainMessageSubmit(@ModelAttribute("messageVO") TrainMessageVO vo) throws Exception{
        LoginVO user = (LoginVO) EgovUserDetailsHelper.getAuthenticatedUser();
        vo.setMessageId(trainMessageIdGenerator.getNextStringId());
        vo.setCreatedUserId(user.getUniqId());
        trainMessageService.trainMessageSubmit(vo);
        return "forward:/message/trainMessageList.do";
    }
}
