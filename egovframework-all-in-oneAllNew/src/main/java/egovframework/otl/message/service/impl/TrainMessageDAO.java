package egovframework.otl.message.service.impl;

import egovframework.com.cmm.service.impl.EgovComAbstractDAO;
import egovframework.otl.message.service.TrainMessageVO;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;

@Repository("TrainMessageDAO")
public class TrainMessageDAO extends EgovComAbstractDAO {

    public void trainMessageSubmit(TrainMessageVO vo) {
        insert("TrainMessageDAO.trainMessageSubmit",vo);
    }

    public List<HashMap> searchTeamIdList(String trainId) {
        return selectList("TrainMessageDAO.searchTeamIdList", trainId);
    }

    public List<TrainMessageVO> trainMessageList(HashMap parameterMap) {
        return selectList("TrainMessageDAO.trainMessageList", parameterMap);
    }

    public int trainMessageListCount(HashMap parameterMap) {
        return (Integer) selectOne("TrainMessageDAO.trainMessageListCount", parameterMap);
    }

    public TrainMessageVO searchUsersTeamId(String esntlId) {
        return selectOne("TrainMessageDAO.searchUsersTeamId", esntlId);
    }

    public TrainMessageVO trainMessageDetail(TrainMessageVO vo) {
        return selectOne("TrainMessageDAO.trainMessageDetail", vo);
    }

    public void userMessageCheck(HashMap param) {
        insert("TrainMessageDAO.userMessageCheck", param);
    }
}
