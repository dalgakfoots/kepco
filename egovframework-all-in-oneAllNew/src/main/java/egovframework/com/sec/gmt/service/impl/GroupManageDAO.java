package egovframework.com.sec.gmt.service.impl;
import java.util.HashMap;
import java.util.List;

import egovframework.com.cmm.service.impl.EgovComAbstractDAO;
import egovframework.com.sec.gmt.service.GroupManage;
import egovframework.com.sec.gmt.service.GroupManageVO;
import egovframework.com.sec.gmt.service.TrainGroupVmVO;
import egovframework.com.sec.gmt.service.VmGroupType;
import egovframework.com.sec.gmt.service.VmGroupTypeVO;
import egovframework.com.sec.gmt.service.VmTypeVO;
import egovframework.com.uss.umt.service.MberManageVO;
import org.springframework.stereotype.Repository;

/**
 * 그룹관리에 대한 DAO 클래스를 정의한다.
 * @author 공통서비스 개발팀 이문준
 * @since 2009.06.01
 * @version 1.0
 * @see
 *
 * <pre>
 * << 개정이력(Modification Information) >>
 *   
 *   수정일      수정자           수정내용
 *  -------    --------    ---------------------------
 *   2009.03.11  이문준          최초 생성
 *
 * </pre>
 */

@Repository("groupManageDAO")
public class GroupManageDAO extends EgovComAbstractDAO {

	/**
	 * 검색조건에 따른 그룹정보를 조회
	 * @param groupManageVO GroupManageVO
	 * @return GroupManageVO
	 * @exception Exception
	 */
	public GroupManageVO selectGroup(GroupManageVO groupManageVO) throws Exception {
		return (GroupManageVO) selectOne("groupManageDAO.selectGroup", groupManageVO);
	}

	/**
	 * 시스템사용 목적별 그룹 목록 조회
	 * @param groupManageVO GroupManageVO
	 * @return GroupManageVO
	 * @exception Exception
	 */
	@SuppressWarnings("unchecked")
	public List<GroupManageVO> selectGroupList(GroupManageVO groupManageVO) throws Exception {
		return (List<GroupManageVO>) list("groupManageDAO.selectGroupList", groupManageVO);
	}
	
	@SuppressWarnings("unchecked")
	public List<GroupManageVO> selectGroupListKepco(GroupManageVO groupManageVO) throws Exception {
		return (List<GroupManageVO>) list("groupManageDAO.selectGroupListKepco", groupManageVO);
	}
	
	/**
	 * 그룹 기본정보를 화면에서 입력하여 항목의 정합성을 체크하고 데이터베이스에 저장
	 * @param groupManage GroupManage
	 * @exception Exception
	 */
	public void insertGroup(GroupManage groupManage) throws Exception {
		insert("groupManageDAO.insertGroup", groupManage);
	}

	/**
	 * 화면에 조회된 그룹의 기본정보를 수정하여 항목의 정합성을 체크하고 수정된 데이터를 데이터베이스에 반영
	 * @param groupManage GroupManage
	 * @exception Exception
	 */
	public void updateGroup(GroupManage groupManage) throws Exception {
		update("groupManageDAO.updateGroup", groupManage);
	}
	
	/**
	 * 불필요한 그룹정보를 화면에 조회하여 데이터베이스에서 삭제
	 * @param groupManage GroupManage
	 * @exception Exception
	 */
	public void deleteGroup(GroupManage groupManage) throws Exception {
		delete("groupManageDAO.deleteGroup", groupManage);
	}

    /**
	 * 롤목록 총 갯수를 조회한다.
	 * @param groupManageVO GroupManageVO
	 * @return int
	 * @exception Exception
	 */
    public int selectGroupListTotCnt(GroupManageVO groupManageVO) throws Exception {
        return (Integer)selectOne("groupManageDAO.selectGroupListTotCnt", groupManageVO);
    }
    
	public List<HashMap> selectUsers(GroupManageVO groupManageVO) {
		
		return selectList("groupManageDAO.selectUsers", groupManageVO);
		
	}

	public void updateUserGroupId(HashMap map) {
		update("groupManageDAO.updateUserGroupId", map);
		
	}

	public void insertUserGroupMapping(HashMap map) {
		insert("groupManageDAO.insertUserGroupMapping", map);
		
	}

	public void deleteUserGroupMapping(HashMap param) {
		delete("groupManageDAO.deleteUserGroupMapping", param);
		
	}

	public List<HashMap> selectSelectedTeamUsers(HashMap param) {
		return selectList("groupManageDAO.selectSelectedTeamUsers", param);
		
	}
	

	public void insertVmGroup(GroupManage groupManage) throws Exception {
		insert("groupManageDAO.insertVmGroup", groupManage);
	}
	public GroupManageVO selectVmGroup(GroupManageVO groupManageVO) throws Exception {
		return (GroupManageVO) selectOne("groupManageDAO.selectVmGroup", groupManageVO);
	}
	
	public void deleteVmGroupTypes(GroupManage groupManage) throws Exception {
		delete("groupManageDAO.deleteVmGroupTypes", groupManage);
	}
	@SuppressWarnings("unchecked")
	public List<GroupManageVO> selectVmGroupList(GroupManageVO groupManageVO) throws Exception {
		return (List<GroupManageVO>) list("groupManageDAO.selectVmGroupList", groupManageVO);
	}
	public int selectVmGroupListTotCnt(GroupManageVO groupManageVO) throws Exception {
	    return (Integer)selectOne("groupManageDAO.selectVmGroupListTotCnt", groupManageVO);
	}
	public void deleteVmGroup(GroupManage groupManage) throws Exception {
		delete("groupManageDAO.deleteVmGroup", groupManage);
	}
	public void updateVmGroup(GroupManage groupManage) throws Exception {
		update("groupManageDAO.updateVmGroup", groupManage);
	}
	public List<VmTypeVO> selectVmTypeList(VmTypeVO vmTypeVO) throws Exception {
		return (List<VmTypeVO>) list("groupManageDAO.selectVmTypeList", vmTypeVO); 
	}
	public List<HashMap> selectVmTypeList2() throws Exception {
		return selectList("groupManageDAO.selectVmTypeList2"); 
	}
	public List<HashMap> selectVmGroupTypeList(GroupManageVO groupManageVO) {
		return  selectList("groupManageDAO.selectVmGroupTypeList", groupManageVO);
	}
	public int selectVmTypeIdByVmType(String vmType) {
		return selectOne("groupManageDAO.selectVmTypeIdByVmType", vmType);
	}
	public void insertVmGroupTypes(VmGroupType vmGroupType) {
		insert("groupManageDAO.insertVmGroupTypes", vmGroupType);
		
	}

	public List<HashMap> selectTrainGroupVmList(TrainGroupVmVO trainGroupVmVO) {
		return selectList("groupManageDAO.selectTrainGroupVmList", trainGroupVmVO);
	}
	/*훈련매핑관리 화면에서 사용*/
	public List<HashMap> selectVmGroupList() {
		return selectList("groupManageDAO.selectVmGroupListForMapping");
	}

	public List<HashMap> selectTrainList() {
		return selectList("groupManageDAO.selectTrainList");
	}

	public List<HashMap> selectTeamList() {
		return selectList("groupManageDAO.selectTeamList");
	}

	public int selectTrainGroupVmListTotCnt(TrainGroupVmVO trainGroupVmVO) {
		return (Integer)selectOne("groupManageDAO.selectTrainGroupVmListTotCnt", trainGroupVmVO);
	}

	public void insertTrainGroupVmManage(HashMap insertMap) {
		insert("groupManageDAO.insertTrainGroupVmManage", insertMap);
	}

	public void updateTrainGroupVmManage(HashMap updateMap) {
		update("groupManageDAO.updateTrainGroupVmManage", updateMap);
		
	}

	public void deleteTrainGroupVmManage(HashMap deleteMap) {
		delete("groupManageDAO.deleteTrainGroupVmManage", deleteMap);
		
	}
		
}