package egovframework.otl.train.train;

import egovframework.com.uss.ion.ecc.service.impl.EgovEventCmpgnDAO;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;

@Service("KepcoEventCmpgnTeamService")
public class KepcoEventCmpgnTeamServiceImpl implements KepcoEventCmpgnTeamService {
	
	@Resource(name="EgovEventCmpgnDAO")
	private EgovEventCmpgnDAO egovEventCmpgnDao;
	
	@Override
	public void insertEventCmpgnTeam(HashMap map) throws Exception {
		egovEventCmpgnDao.insertEventCmpgnTeam(map);

	}

	@Override
	public List<?> selectEventCmpgnTeam(HashMap map) throws Exception {
		return egovEventCmpgnDao.selectEventCmpgnTeam(map);
	}

	@Override
	public void deleteEventCmpgnTeam(HashMap map) throws Exception {
		egovEventCmpgnDao.deleteEventCmpgnTeam(map);
		
	}

}
