package egovframework.com.sec.gmt.web;

import java.util.HashMap;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springmodules.validation.commons.DefaultBeanValidator;

import egovframework.com.cmm.EgovMessageSource;
import egovframework.com.cmm.SessionVO;
import egovframework.com.sec.gmt.service.EgovGroupManageService;
import egovframework.com.sec.gmt.service.TrainGroupVmVO;
import egovframework.rte.fdl.idgnr.EgovIdGnrService;
import egovframework.rte.fdl.property.EgovPropertyService;
import egovframework.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;

/**
 * 훈련 - 회원그룹 - VM 그룹 매핑 화면 컨트롤러
 * 
 * @author 함승우
 * @since
 * @version 1.0
 * @see
 *
 *      <pre>
 * << 개정이력(Modification Information) >>
 *   
 *   수정일      수정자           수정내용
 *  -------    --------    ---------------------------
 * 
 *
 *      </pre>
 */

@Controller
@SessionAttributes(types = SessionVO.class)
public class EgovTrainGroupVmManageController {

	@Resource(name = "egovMessageSource")
	EgovMessageSource egovMessageSource;

	@Resource(name = "egovGroupManageService")
	private EgovGroupManageService egovGroupManageService;

	/** EgovPropertyService */
	@Resource(name = "propertiesService")
	protected EgovPropertyService propertiesService;

	/** Message ID Generation */
	@Resource(name = "egovGroupIdGnrService")
	private EgovIdGnrService egovGroupIdGnrService;
	
	@Resource(name = "egovTrainGroupVmManageIdGnrService")
	private EgovIdGnrService egovTGVMIdGnrService;
	
	@Autowired
	private DefaultBeanValidator beanValidator;
	
	/*
	 * 
	 * 훈련 - 회원그룹 - VM그룹 매핑 목록 화면 이동
	 * 
	 * */
	@RequestMapping("/sec/tmt/EgovTrainGroupVmManage.do")
	public String EgovTrainGroupVmManage(@ModelAttribute("trainGroupVmVO") TrainGroupVmVO trainGroupVmVO, ModelMap model, BindingResult result) throws Exception {
		/** paging */
    	PaginationInfo paginationInfo = new PaginationInfo();
		paginationInfo.setCurrentPageNo(trainGroupVmVO.getPageIndex());
		paginationInfo.setRecordCountPerPage(trainGroupVmVO.getPageUnit());
		paginationInfo.setPageSize(trainGroupVmVO.getPageSize());
		
		trainGroupVmVO.setFirstIndex(paginationInfo.getFirstRecordIndex());
		trainGroupVmVO.setLastIndex(paginationInfo.getLastRecordIndex());
		trainGroupVmVO.setRecordCountPerPage(paginationInfo.getRecordCountPerPage());
		trainGroupVmVO.setPageIndex(1);
		
		
		/* TODO 훈련명 select box option 조회*/
		List<HashMap> trainNameList = egovGroupManageService.selectTrainList();
		model.addAttribute("trainNameList", trainNameList);
		/* TODO 사용자그룹명 select box option 조회*/
		List<HashMap> teamNameList = egovGroupManageService.selectTeamList();
		model.addAttribute("teamNameList", teamNameList);
		/* TODO VM그룹명 select box option 조회*/
		List<HashMap> vmGroupNameList = egovGroupManageService.selectVmGroupList();
		model.addAttribute("vmGroupNameList", vmGroupNameList);
		/*TODO 훈련 - 유저그룹 - VM그룹 매핑 완료된 목록 가져오기*/
		
		List<HashMap> mappingList = egovGroupManageService.selectTrainGroupVmList(trainGroupVmVO);
		model.addAttribute("mappingList", mappingList);
		
		int totCnt = egovGroupManageService.selectTrainGroupVmListTotCnt(trainGroupVmVO);
		paginationInfo.setTotalRecordCount(totCnt);
        model.addAttribute("paginationInfo", paginationInfo);
        model.addAttribute("trainGroupVmVO", trainGroupVmVO);
		
		return "egovframework/com/sec/tmt/EgovTrainGroupVmManageList";
	}
	
	@RequestMapping("/sec/tmt/EgovTrainGroupVmManageSave.do")
	public String EgovTrainGroupVmManageUpdate(@RequestParam("insertRow") String insertRow , @RequestParam("updateRow") String updateRow 
			, @RequestParam("deleteRow") String deleteRow, ModelMap model) throws Exception {
		/* TODO KEPCO_TRAINING_TEAM_VMGROUP 테이블의 스키마가 변경될 시, 반드시 처리해줘야 함. */
		
		HashMap updateMap = new HashMap();
		if(updateRow.length()!=0) {
			String[] updates = updateRow.split("/");
			for(String update : updates){
				String[] elements = update.split(";");
				System.out.println(elements[0]);
				updateMap.put("id" , elements[0]);
				updateMap.put("training_id", elements[1]);
				updateMap.put("team_id", elements[2]);
				updateMap.put("vm_id", elements[3]);
				egovGroupManageService.updateTrainGroupVmManage(updateMap);
			}
		}
		
		HashMap deleteMap = new HashMap();
		if(deleteRow.length() != 0) {
			String[] deletes = deleteRow.split("/");
			for(String delete : deletes){
				String[] elements = delete.split(";");
				System.out.println(elements[0]);
				deleteMap.put("id" , elements[0]);
				deleteMap.put("training_id", elements[1]);
				deleteMap.put("team_id", elements[2]);
				deleteMap.put("vm_id", elements[3]);
				egovGroupManageService.deleteTrainGroupVmManage(deleteMap);
			}
		}
		
		HashMap insertMap = new HashMap();
		if(insertRow.length() != 0) {
			String[] inserts = insertRow.split("/");
			for(String insert : inserts){
				String[] elements = insert.split(";");
				System.out.println(elements[0]);
				insertMap.put("id" , egovTGVMIdGnrService.getNextStringId());
				insertMap.put("training_id", elements[1]);
				insertMap.put("team_id", elements[2]);
				insertMap.put("vm_id", elements[3]);
				egovGroupManageService.insertTrainGroupVmManage(insertMap);
			}
			
		}
		return "redirect:/sec/tmt/EgovTrainGroupVmManage.do";
	}
	
	
	/*
	*//**
		 * 그룹 목록화면 이동
		 * 
		 * @return String
		 * @exception Exception
		 */
	/*
	 * @RequestMapping("/sec/gmt/EgovGroupListView.do") public String
	 * selectGroupListView() throws Exception { return
	 * "egovframework/com/sec/gmt/EgovGroupManage"; }
	 * 
	 * 
	 * @RequestMapping("/sec/gmt/EgovMberListPopup.do") public String
	 * egovMberListPopup() {
	 * 
	 * return "egovframework/com/sec/gmt/EgovMberListPopup"; }
	 * 
	 *//**
		 * 시스템사용 목적별 그룹 목록 조회
		 * 
		 * @param groupManageVO GroupManageVO
		 * @return String
		 * @exception Exception
		 */
	/*
	 * @IncludedInfo(name="그룹관리", listUrl="/sec/gmt/EgovGroupList.do", order =
	 * 80,gid = 20)
	 * 
	 * @RequestMapping(value="/sec/gmt/EgovGroupList.do") public String
	 * selectGroupList(@ModelAttribute("groupManageVO") GroupManageVO groupManageVO,
	 * ModelMap model) throws Exception {
	 *//** paging */
	/*
	 * PaginationInfo paginationInfo = new PaginationInfo();
	 * paginationInfo.setCurrentPageNo(groupManageVO.getPageIndex());
	 * paginationInfo.setRecordCountPerPage(groupManageVO.getPageUnit());
	 * paginationInfo.setPageSize(groupManageVO.getPageSize());
	 * 
	 * groupManageVO.setFirstIndex(paginationInfo.getFirstRecordIndex());
	 * groupManageVO.setLastIndex(paginationInfo.getLastRecordIndex());
	 * groupManageVO.setRecordCountPerPage(paginationInfo.getRecordCountPerPage());
	 * 
	 * groupManageVO.setGroupManageList(egovGroupManageService.selectGroupList(
	 * groupManageVO)); model.addAttribute("groupList",
	 * groupManageVO.getGroupManageList());
	 * 
	 * int totCnt = egovGroupManageService.selectGroupListTotCnt(groupManageVO);
	 * paginationInfo.setTotalRecordCount(totCnt);
	 * model.addAttribute("paginationInfo", paginationInfo);
	 * model.addAttribute("message",
	 * egovMessageSource.getMessage("success.common.select"));
	 * 
	 * return "egovframework/com/sec/gmt/EgovGroupManage"; }
	 * 
	 *//**
		 * 검색조건에 따른 그룹정보를 조회
		 * 
		 * @param groupManageVO GroupManageVO
		 * @return String
		 * @exception Exception
		 */
	/*
	 * @RequestMapping(value="/sec/gmt/EgovGroup.do") public String
	 * selectGroup(@ModelAttribute("groupManageVO") GroupManageVO groupManageVO,
	 * 
	 * @ModelAttribute("groupManage") GroupManage groupManage, ModelMap model)
	 * throws Exception {
	 * 
	 * model.addAttribute("groupManage",
	 * egovGroupManageService.selectGroup(groupManageVO));
	 * model.addAttribute("selectUsers",
	 * egovGroupManageService.selectUsers(groupManageVO));
	 * 
	 * return "egovframework/com/sec/gmt/EgovGroupUpdate"; }
	 * 
	 *//**
		 * 그룹 등록화면 이동
		 * 
		 * @return String
		 * @exception Exception
		 */
	/*
	 * @RequestMapping(value="/sec/gmt/EgovGroupInsertView.do") public String
	 * insertGroupView(@ModelAttribute("groupManage") GroupManage groupManage)
	 * throws Exception { return "egovframework/com/sec/gmt/EgovGroupInsert"; }
	 * 
	 *//**
		 * 그룹 기본정보를 화면에서 입력하여 항목의 정합성을 체크하고 데이터베이스에 저장
		 * 
		 * @param groupManage   GroupManage
		 * @param groupManageVO GroupManageVO
		 * @return String
		 * @exception Exception
		 */
	/*
	 * @RequestMapping(value="/sec/gmt/EgovGroupInsert.do") public String
	 * insertGroup(@ModelAttribute("groupManage") GroupManage groupManage,
	 * 
	 * @ModelAttribute("groupManageVO") GroupManageVO groupManageVO, BindingResult
	 * bindingResult, ModelMap model) throws Exception {
	 * 
	 * beanValidator.validate(groupManage, bindingResult); //validation 수행
	 * 
	 * if (bindingResult.hasErrors()) { return
	 * "egovframework/com/sec/gmt/EgovGroupInsert"; } else {
	 * groupManage.setGroupId(egovGroupIdGnrService.getNextStringId());
	 * groupManageVO.setGroupId(groupManage.getGroupId());
	 * 
	 * model.addAttribute("message",
	 * egovMessageSource.getMessage("success.common.insert"));
	 * model.addAttribute("groupManage",
	 * egovGroupManageService.insertGroup(groupManage, groupManageVO)); return
	 * "forward:/sec/gmt/EgovGroupList.do"; } }
	 * 
	 *//**
		 * 화면에 조회된 그룹의 기본정보를 수정하여 항목의 정합성을 체크하고 수정된 데이터를 데이터베이스에 반영
		 * 
		 * @param groupManage GroupManage
		 * @return String
		 * @exception Exception
		 */
	/*
	 * @RequestMapping(value="/sec/gmt/EgovGroupUpdate.do") public String
	 * updateGroup(@ModelAttribute("groupManage") GroupManage groupManage,
	 * BindingResult bindingResult, Model model, @RequestParam("addedUser") String
	 * addedUser) throws Exception {
	 * 
	 * beanValidator.validate(groupManage, bindingResult); //validation 수행 if
	 * (bindingResult.hasErrors()) { return
	 * "egovframework/com/sec/gmt/EgovGroupUpdate"; } else { TODO 팀원 추가 전처리 HashMap
	 * param = new HashMap(); param.put("teamId", groupManage.getGroupId());
	 * 
	 * TODO 회원 ID로 검색하여 회원의 GROUP_ID 를 NULL 로 변경시킨다.
	 * 
	 * List<HashMap> selectedTeamUsers = (List<HashMap>)
	 * egovGroupManageService.selectSelectedTeamUsers(param); for(HashMap a :
	 * selectedTeamUsers) { HashMap map = new HashMap(); map.put("groupId", "");
	 * map.put("userId", a.get("ESNTL_ID"));
	 * egovGroupManageService.updateUserGroupId(map); }
	 * 
	 * egovGroupManageService.deleteUserGroupMapping(param); String [] userArr =
	 * addedUser.split(","); HashMap map = null; for(String userId : userArr) { map
	 * = new HashMap(); map.put("groupId", groupManage.getGroupId());
	 * map.put("userId", userId); TODO 체크된 팀원을 추가한다.
	 * egovGroupManageService.updateUserGroupId(map); // update comtngnrlmber
	 * egovGroupManageService.insertUserGroupMapping(map); // insert into
	 * kepco_team_users }
	 * 
	 * egovGroupManageService.updateGroup(groupManage);
	 * model.addAttribute("message",
	 * egovMessageSource.getMessage("success.common.update")); return
	 * "forward:/sec/gmt/EgovGroupList.do"; } }
	 * 
	 *//**
		 * 불필요한 그룹정보를 화면에 조회하여 데이터베이스에서 삭제
		 * 
		 * @param groupManage GroupManage
		 * @return String
		 * @exception Exception
		 */
	/*
	 * @RequestMapping(value="/sec/gmt/EgovGroupDelete.do") public String
	 * deleteGroup(@ModelAttribute("groupManage") GroupManage groupManage, Model
	 * model) throws Exception { egovGroupManageService.deleteGroup(groupManage);
	 * model.addAttribute("message",
	 * egovMessageSource.getMessage("success.common.delete")); return
	 * "forward:/sec/gmt/EgovGroupList.do"; }
	 * 
	 *//**
		 * 불필요한 그룹정보 목록을 화면에 조회하여 데이터베이스에서 삭제
		 * 
		 * @param groupIds    String
		 * @param groupManage GroupManage
		 * @return String
		 * @exception Exception
		 */
	/*
	 * @RequestMapping(value="/sec/gmt/EgovGroupListDelete.do") public String
	 * deleteGroupList(@RequestParam("groupIds") String groupIds,
	 * 
	 * @ModelAttribute("groupManage") GroupManage groupManage, Model model) throws
	 * Exception { String [] strGroupIds = groupIds.split(";"); for(int i=0;
	 * i<strGroupIds.length;i++) { groupManage.setGroupId(strGroupIds[i]);
	 * egovGroupManageService.deleteGroup(groupManage); }
	 * 
	 * model.addAttribute("message",
	 * egovMessageSource.getMessage("success.common.delete")); return
	 * "forward:/sec/gmt/EgovGroupList.do"; }
	 * 
	 *//**
		 * 그룹팝업 화면 이동
		 * 
		 * @return String
		 * @exception Exception
		 */
	/*
	 * @RequestMapping("/sec/gmt/EgovGroupSearchView.do") public String
	 * selectGroupSearchView() throws Exception { return
	 * "egovframework/com/sec/gmt/EgovGroupSearch"; }
	 * 
	 *//**
		 * 시스템사용 목적별 그룹 목록 조회
		 * 
		 * @param groupManageVO GroupManageVO
		 * @return String
		 * @exception Exception
		 */
	/*
	 * @RequestMapping(value="/sec/gmt/EgovGroupSearchList.do") public String
	 * selectGroupSearchList(@ModelAttribute("groupManageVO") GroupManageVO
	 * groupManageVO, ModelMap model) throws Exception {
	 *//** paging */
	/*
	 * PaginationInfo paginationInfo = new PaginationInfo();
	 * paginationInfo.setCurrentPageNo(groupManageVO.getPageIndex());
	 * paginationInfo.setRecordCountPerPage(groupManageVO.getPageUnit());
	 * paginationInfo.setPageSize(groupManageVO.getPageSize());
	 * 
	 * groupManageVO.setFirstIndex(paginationInfo.getFirstRecordIndex());
	 * groupManageVO.setLastIndex(paginationInfo.getLastRecordIndex());
	 * groupManageVO.setRecordCountPerPage(paginationInfo.getRecordCountPerPage());
	 * 
	 * groupManageVO.setGroupManageList(egovGroupManageService.selectGroupList(
	 * groupManageVO)); model.addAttribute("groupList",
	 * groupManageVO.getGroupManageList());
	 * 
	 * int totCnt = egovGroupManageService.selectGroupListTotCnt(groupManageVO);
	 * paginationInfo.setTotalRecordCount(totCnt);
	 * model.addAttribute("paginationInfo", paginationInfo);
	 * model.addAttribute("message",
	 * egovMessageSource.getMessage("success.common.select"));
	 * 
	 * return "egovframework/com/sec/gmt/EgovGroupSearch"; }
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * @RequestMapping("/sec/vmt/EgovVmGroupListView.do") public String
	 * selectVmGroupListView() throws Exception { return
	 * "egovframework/com/sec/vmt/EgovVmGroupManage"; }
	 * 
	 * @IncludedInfo(name="그룹관리", listUrl="/sec/vmt/EgovVmGroupList.do", order =
	 * 80,gid = 20)
	 * 
	 * @RequestMapping(value="/sec/vmt/EgovVmGroupList.do") public String
	 * selectvmGroupList(@ModelAttribute("groupManageVO") GroupManageVO
	 * groupManageVO, ModelMap model) throws Exception {
	 *//** paging */
	/*
	 * PaginationInfo paginationInfo = new PaginationInfo();
	 * paginationInfo.setCurrentPageNo(groupManageVO.getPageIndex());
	 * paginationInfo.setRecordCountPerPage(groupManageVO.getPageUnit());
	 * paginationInfo.setPageSize(groupManageVO.getPageSize());
	 * 
	 * groupManageVO.setFirstIndex(paginationInfo.getFirstRecordIndex());
	 * groupManageVO.setLastIndex(paginationInfo.getLastRecordIndex());
	 * groupManageVO.setRecordCountPerPage(paginationInfo.getRecordCountPerPage());
	 * 
	 * groupManageVO.setGroupManageList(egovGroupManageService.selectVmGroupList(
	 * groupManageVO)); System.out.println("groupManageVO.getGroupManageList() : " +
	 * groupManageVO.getGroupManageList()); model.addAttribute("groupList",
	 * groupManageVO.getGroupManageList());
	 * 
	 * int totCnt = egovGroupManageService.selectVmGroupListTotCnt(groupManageVO);
	 * paginationInfo.setTotalRecordCount(totCnt);
	 * model.addAttribute("paginationInfo", paginationInfo);
	 * model.addAttribute("message",
	 * egovMessageSource.getMessage("success.common.select"));
	 * 
	 * return "egovframework/com/sec/vmt/EgovVmGroupManage"; }
	 * 
	 * @RequestMapping(value="/sec/vmt/EgovVmGroupInsertView.do") public String
	 * insertVmGroupView(@ModelAttribute("groupManage") GroupManage groupManage,
	 * Model model, VmTypeVO vmTypeVO) throws Exception {
	 * vmTypeVO.setVmTypeList(egovGroupManageService.selectVmTypeList(vmTypeVO));
	 * model.addAttribute("typeList", vmTypeVO.getVmTypeList()); return
	 * "egovframework/com/sec/vmt/EgovVmGroupInsert"; }
	 * 
	 * @RequestMapping(value="/sec/vmt/EgovVmGroupInsert.do") public String
	 * insertVmGroup(@ModelAttribute("groupManage") GroupManage groupManage,
	 * 
	 * @ModelAttribute("groupManageVO") GroupManageVO groupManageVO,
	 * 
	 * @RequestParam("typeUrl") String typeUrl, BindingResult bindingResult,
	 * ModelMap model) throws Exception {
	 * 
	 * beanValidator.validate(groupManage, bindingResult); //validation 수행 if
	 * (bindingResult.hasErrors()) { return
	 * "egovframework/com/sec/vmt/EgovVmGroupInsert"; } else {
	 * groupManage.setGroupId(egovGroupIdGnrService.getNextStringId());
	 * groupManageVO.setGroupId(groupManage.getGroupId());
	 * 
	 * model.addAttribute("message",
	 * egovMessageSource.getMessage("success.common.insert"));
	 * model.addAttribute("groupManage",
	 * egovGroupManageService.insertVmGroup(groupManage, groupManageVO, typeUrl));
	 * return "forward:/sec/vmt/EgovVmGroupList.do"; } }
	 * 
	 * @RequestMapping(value="/sec/vmt/EgovVmGroupListDelete.do") public String
	 * deleteVmGroupList(@RequestParam("groupIds") String groupIds,
	 * 
	 * @ModelAttribute("groupManage") GroupManage groupManage, Model model) throws
	 * Exception { String [] strGroupIds = groupIds.split(";"); for(int i=0;
	 * i<strGroupIds.length;i++) { groupManage.setGroupId(strGroupIds[i]);
	 * egovGroupManageService.deleteVmGroup(groupManage); }
	 * 
	 * model.addAttribute("message",
	 * egovMessageSource.getMessage("success.common.delete")); return
	 * "forward:/sec/vmt/EgovVmGroupList.do"; }
	 * 
	 * @RequestMapping(value="/sec/vmt/EgovVmGroup.do") public String
	 * selectVmGroup(@ModelAttribute("groupManageVO") GroupManageVO groupManageVO,
	 * 
	 * @ModelAttribute("groupManage") GroupManage groupManage, ModelMap model)
	 * throws Exception {
	 * 
	 * model.addAttribute("groupManage",
	 * egovGroupManageService.selectVmGroup(groupManageVO)); return
	 * "egovframework/com/sec/vmt/EgovVmGroupUpdate"; }
	 * 
	 * @RequestMapping(value="/sec/vmt/EgovVmGroupUpdate.do") public String
	 * updateVmGroup(@ModelAttribute("groupManage") GroupManage groupManage,
	 * BindingResult bindingResult, Model model) throws Exception {
	 * 
	 * beanValidator.validate(groupManage, bindingResult); //validation 수행
	 * 
	 * if (bindingResult.hasErrors()) { return
	 * "egovframework/com/sec/vmt/EgovVMGroupUpdate"; } else {
	 * egovGroupManageService.updateVmGroup(groupManage);
	 * model.addAttribute("message",
	 * egovMessageSource.getMessage("success.common.update")); return
	 * "forward:/sec/vmt/EgovVmGroupList.do"; } }
	 * 
	 * @RequestMapping(value="/sec/vmt/EgovVmGroupSearchList.do") public String
	 * selectVmGroupSearchList(@ModelAttribute("groupManageVO") GroupManageVO
	 * groupManageVO, ModelMap model) throws Exception {
	 *//** paging *//*
					 * PaginationInfo paginationInfo = new PaginationInfo();
					 * paginationInfo.setCurrentPageNo(groupManageVO.getPageIndex());
					 * paginationInfo.setRecordCountPerPage(groupManageVO.getPageUnit());
					 * paginationInfo.setPageSize(groupManageVO.getPageSize());
					 * 
					 * groupManageVO.setFirstIndex(paginationInfo.getFirstRecordIndex());
					 * groupManageVO.setLastIndex(paginationInfo.getLastRecordIndex());
					 * groupManageVO.setRecordCountPerPage(paginationInfo.getRecordCountPerPage());
					 * 
					 * groupManageVO.setGroupManageList(egovGroupManageService.selectVmGroupList(
					 * groupManageVO)); model.addAttribute("groupList",
					 * groupManageVO.getGroupManageList());
					 * 
					 * int totCnt = egovGroupManageService.selectVmGroupListTotCnt(groupManageVO);
					 * paginationInfo.setTotalRecordCount(totCnt);
					 * model.addAttribute("paginationInfo", paginationInfo);
					 * model.addAttribute("message",
					 * egovMessageSource.getMessage("success.common.select"));
					 * 
					 * return "egovframework/com/sec/vmt/EgovVmGroupSearch"; }
					 */

}