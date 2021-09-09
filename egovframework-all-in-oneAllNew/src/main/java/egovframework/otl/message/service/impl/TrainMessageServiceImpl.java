package egovframework.otl.message.service.impl;

import egovframework.com.cmm.LoginVO;
import egovframework.com.cmm.util.EgovUserDetailsHelper;
import egovframework.com.sec.rgm.service.EgovAuthorGroupService;
import egovframework.otl.message.service.TrainMessageService;
import egovframework.otl.message.service.TrainMessageVO;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;

@Service("TrainMessageService")
public class TrainMessageServiceImpl implements TrainMessageService {

    @Resource(name="egovAuthorGroupService")
    private EgovAuthorGroupService egovAuthorGroupService;

    @Resource(name = "TrainMessageDAO")
    TrainMessageDAO trainMessageDAO;

    @Override
    public List<HashMap> searchTeamIdList(String trainId) throws Exception {
        return trainMessageDAO.searchTeamIdList(trainId);
    }

    @Override
    public void trainMessageSubmit(TrainMessageVO vo) throws Exception {
        trainMessageDAO.trainMessageSubmit(vo);
    }

    @Override
    public List<TrainMessageVO> trainMessageList(HashMap parameterMap) throws Exception {
        HashMap<String , String> userRole = egovAuthorGroupService.selectUserRole(parameterMap); //[mber_nm , author_code , mber_id , esntl_id]
        parameterMap.put("role",userRole.get("author_code"));

        String esntlId = userRole.get("esntl_id");
        TrainMessageVO temp = trainMessageDAO.searchUsersTeamId(esntlId);
        try{
            parameterMap.put("teamId",temp.getTeamId());
        }catch (Exception e){
            if (e.getClass() == NullPointerException.class) {
                parameterMap.put("teamId", "");
            }else{
                e.printStackTrace();
                throw e;
            }
        }

        return trainMessageDAO.trainMessageList(parameterMap);
    }

    @Override
    public int trainMessageCount(HashMap parameterMap) throws Exception {
        return trainMessageDAO.trainMessageListCount(parameterMap);
    }

    @Override
    public TrainMessageVO trainMessageDetail(TrainMessageVO vo) throws Exception {

        HashMap param = new HashMap();
        LoginVO user = (LoginVO) EgovUserDetailsHelper.getAuthenticatedUser();
        param.put("messageId", vo.getMessageId());
        param.put("userId", user.getUniqId());
        userMessageCheck(param); // INSERT INTO USER_MESSAGE_CHECK

        return trainMessageDAO.trainMessageDetail(vo);
    }

    private void userMessageCheck(HashMap param){
        trainMessageDAO.userMessageCheck(param);
    }
}
