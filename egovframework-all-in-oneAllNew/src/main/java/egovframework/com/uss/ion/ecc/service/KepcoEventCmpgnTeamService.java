package egovframework.com.uss.ion.ecc.service;

import java.util.HashMap;
import java.util.List;

public interface KepcoEventCmpgnTeamService {

	void insertEventCmpgnTeam(HashMap map) throws Exception;
	List<?> selectEventCmpgnTeam(HashMap map) throws Exception;
	void deleteEventCmpgnTeam(HashMap eventId) throws Exception;
}
