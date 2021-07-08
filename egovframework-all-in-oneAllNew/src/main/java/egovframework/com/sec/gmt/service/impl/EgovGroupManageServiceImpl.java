package egovframework.com.sec.gmt.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.HashMap;

import egovframework.com.sec.gmt.service.EgovGroupManageService;
import egovframework.com.sec.gmt.service.GroupManage;
import egovframework.com.sec.gmt.service.GroupManageVO;
import egovframework.com.sec.gmt.service.TrainGroupVmVO;
import egovframework.com.sec.gmt.service.VmGroupType;
import egovframework.com.sec.gmt.service.VmType;
import egovframework.com.sec.gmt.service.VmTypeVO;
import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;
import egovframework.com.uss.umt.service.MberManageVO;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

/**
 * 그룹관리에 관한 ServiceImpl 클래스를 정의한다.
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

@Service("egovGroupManageService")
public class EgovGroupManageServiceImpl extends EgovAbstractServiceImpl implements EgovGroupManageService {

	@Resource(name="groupManageDAO")
    private GroupManageDAO groupManageDAO;
	
	/**
	 * 시스템사용 목적별 그룹 목록 조회
	 * @param groupManageVO GroupManageVO
	 * @return List<GroupManageVO>
	 * @exception Exception
	 */
	public List<GroupManageVO> selectGroupList(GroupManageVO groupManageVO) throws Exception {
		return groupManageDAO.selectGroupList(groupManageVO);
	}
	
	public List<GroupManageVO> selectGroupListKepco(GroupManageVO groupManageVO) throws Exception {
		return groupManageDAO.selectGroupListKepco(groupManageVO);
	}
	
	/**
	 * 검색조건에 따른 그룹정보를 조회
	 * @param groupManageVO GroupManageVO
	 * @return GroupManageVO
	 * @exception Exception
	 */
	public GroupManageVO selectGroup(GroupManageVO groupManageVO) throws Exception {
		return groupManageDAO.selectGroup(groupManageVO);
	}

	/**
	 * 그룹 기본정보를 화면에서 입력하여 항목의 정합성을 체크하고 데이터베이스에 저장
	 * @param groupManage GroupManage
	 * @param groupManageVO GroupManageVO
	 * @return GroupManageVO
	 * @exception Exception
	 */
	public GroupManageVO insertGroup(GroupManage groupManage, GroupManageVO groupManageVO) throws Exception {
		groupManageDAO.insertGroup(groupManage);
		groupManageVO.setGroupId(groupManage.getGroupId());
		return groupManageDAO.selectGroup(groupManageVO);
	}

	/**
	 * 화면에 조회된 그룹의 기본정보를 수정하여 항목의 정합성을 체크하고 수정된 데이터를 데이터베이스에 반영
	 * @param groupManage GroupManage
	 * @exception Exception
	 */
	public void updateGroup(GroupManage groupManage) throws Exception {
		groupManageDAO.updateGroup(groupManage);
	}
	
	/**
	 * 불필요한 그룹정보를 화면에 조회하여 데이터베이스에서 삭제
	 * @param groupManage GroupManage
	 * @exception Exception
	 */
	public void deleteGroup(GroupManage groupManage) throws Exception {
		groupManageDAO.deleteGroup(groupManage);
	}
	
    /**
	 * 목록조회 카운트를 반환한다
	 * @param groupManageVO GroupManageVO
	 * @return int
	 * @exception Exception
	 */
	public int selectGroupListTotCnt(GroupManageVO groupManageVO) throws Exception {
		return groupManageDAO.selectGroupListTotCnt(groupManageVO);
	}
	@Override
	public List<HashMap> selectUsers(GroupManageVO groupManageVO) throws Exception {
		return groupManageDAO.selectUsers(groupManageVO);
	}

	@Override
	public void updateUserGroupId(HashMap map) throws Exception {
		groupManageDAO.updateUserGroupId(map);
		
	}

	@Override
	public void insertUserGroupMapping(HashMap map) throws Exception {
		groupManageDAO.insertUserGroupMapping(map);
		
	}

	@Override
	public void deleteUserGroupMapping(HashMap param) throws Exception {
		groupManageDAO.deleteUserGroupMapping(param);
		
	}

	@Override
	public List<?> selectSelectedTeamUsers(HashMap param) throws Exception {
		return groupManageDAO.selectSelectedTeamUsers(param);
	}

	

	public GroupManageVO insertVmGroup(GroupManage groupManage, GroupManageVO groupManageVO, String typeUrl) throws Exception {
		groupManageDAO.insertVmGroup(groupManage);
		
		String[] splitedTypeUrl = typeUrl.split("&gt;&gt;&gt;");
		for (int i =0; i< splitedTypeUrl.length ; i++) {
			System.out.println("splitedTypeUrl : "+ splitedTypeUrl[i]);
			String[] typeUrlItems = splitedTypeUrl[i].split(",");
			
			System.out.println("typeUrlItems : "+ typeUrlItems[0]);
			System.out.println("typeUrlItems : "+ typeUrlItems[1]);
			String[] typeItem = typeUrlItems[0].split("&lt;&lt;&lt;");
			String[] urlItem = typeUrlItems[1].split("&lt;&lt;&lt;");
			

			System.out.println("typeItem : "+ typeItem[1]);
			System.out.println("urlItem : "+ urlItem[1]);
			
			
			String type = typeItem[1];
					
					
			String typeId = groupManageDAO.selectVmTypeIdByVmType(type)+"";
			String groupId =groupManage.getGroupId();
			String url = urlItem[1];
			
			VmGroupType vmGroupType = new VmGroupType();
			vmGroupType.setGroupId(groupId);
			vmGroupType.setTypeId(typeId);
			vmGroupType.setUrl(url);
			
			
			groupManageDAO.insertVmGroupTypes(vmGroupType);
		}
			
		groupManageVO.setGroupId(groupManage.getGroupId());
		return groupManageDAO.selectVmGroup(groupManageVO);
	}
	public List<GroupManageVO> selectVmGroupList(GroupManageVO groupManageVO) throws Exception {
		return groupManageDAO.selectVmGroupList(groupManageVO);
	}
	public int selectVmGroupListTotCnt(GroupManageVO groupManageVO) throws Exception {
		return groupManageDAO.selectVmGroupListTotCnt(groupManageVO);
	}
	public void deleteVmGroup(GroupManage groupManage) throws Exception {
		groupManageDAO.deleteVmGroup(groupManage);
	}
	public GroupManageVO selectVmGroup(GroupManageVO groupManageVO) throws Exception {
		return groupManageDAO.selectVmGroup(groupManageVO);
	}
	public void updateVmGroup(GroupManage groupManage) throws Exception {
		groupManageDAO.updateVmGroup(groupManage);
	}
	public List<VmTypeVO> selectVmTypeList(VmTypeVO vmTypeVO) throws Exception {
		return groupManageDAO.selectVmTypeList(vmTypeVO);
	}

	@Override
	public List<HashMap> selectTrainGroupVmList(TrainGroupVmVO trainGroupVmVO) {
		return groupManageDAO.selectTrainGroupVmList(trainGroupVmVO);
	}

	@Override
	public List<HashMap> selectTrainList() throws Exception {
		return groupManageDAO.selectTrainList();
	}

	@Override
	public List<HashMap> selectTeamList() throws Exception {
		return groupManageDAO.selectTeamList();
	}

	@Override
	public List<HashMap> selectVmGroupList() throws Exception {
		return groupManageDAO.selectVmGroupList();
	}

	@Override
	public int selectTrainGroupVmListTotCnt(TrainGroupVmVO trainGroupVmVO) throws Exception {
		return groupManageDAO.selectTrainGroupVmListTotCnt(trainGroupVmVO);
	}

	@Override
	public void insertTrainGroupVmManage(HashMap insertMap) {
		groupManageDAO.insertTrainGroupVmManage(insertMap);
	}

	@Override
	public void updateTrainGroupVmManage(HashMap updateMap) {
		groupManageDAO.updateTrainGroupVmManage(updateMap);
	}

	@Override
	public void deleteTrainGroupVmManage(HashMap deleteMap) {
		groupManageDAO.deleteTrainGroupVmManage(deleteMap);
	}
}