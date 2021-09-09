package egovframework.otl.message.service;

import java.util.HashMap;
import java.util.List;

public interface TrainMessageService {
    List<HashMap> searchTeamIdList(String trainId) throws Exception;

    void trainMessageSubmit(TrainMessageVO vo) throws Exception;

    List<TrainMessageVO> trainMessageList(HashMap parameterMap) throws Exception;
    int trainMessageCount(HashMap parameterMap) throws Exception;

    TrainMessageVO trainMessageDetail(TrainMessageVO vo) throws Exception;
}
