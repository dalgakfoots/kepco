package egovframework.otl.common.service.impl;

import egovframework.com.cmm.service.impl.EgovComAbstractDAO;
import org.springframework.stereotype.Repository;

@Repository("OnTheLiveCommonDAO")
public class OnTheLiveCommonDAO extends EgovComAbstractDAO {

    public String getUserTeamName(String teamId) {
        return selectOne("OnTheLiveCommonDAO.getUserTeamName", teamId);
    }

}
