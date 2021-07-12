package egovframework.com.sec.gmt.web;

import egovframework.com.cmm.EgovMessageSource;
import egovframework.com.cmm.SessionVO;
import egovframework.com.cmm.annotation.IncludedInfo;
import egovframework.com.sec.gmt.service.EgovGroupManageService;
import egovframework.com.sec.gmt.service.GroupManage;
import egovframework.com.sec.gmt.service.GroupManageVO;
import egovframework.com.sec.gmt.service.VmGroupTypeVO;
import egovframework.com.sec.gmt.service.VmTypeVO;
import egovframework.rte.fdl.idgnr.EgovIdGnrService;
import egovframework.rte.fdl.property.EgovPropertyService;
import egovframework.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;

import java.util.HashMap;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springmodules.validation.commons.DefaultBeanValidator;

import egovframework.com.cmm.EgovMessageSource;
import egovframework.com.cmm.SessionVO;
import egovframework.com.cmm.annotation.IncludedInfo;
import egovframework.com.sec.gmt.service.EgovGroupManageService;
import egovframework.com.sec.gmt.service.GroupManage;
import egovframework.com.sec.gmt.service.GroupManageVO;
import egovframework.rte.fdl.idgnr.EgovIdGnrService;
import egovframework.rte.fdl.property.EgovPropertyService;
import egovframework.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;

/**
 * 그룹관리에 관한 controller 클래스를 정의한다.
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
 *   2011.8.26	정진오			IncludedInfo annotation 추가
 *
 * </pre>
 */


@Controller
@SessionAttributes(types=SessionVO.class)
public class EgovGroupManageController {
	
    @Resource(name="egovMessageSource")
    EgovMessageSource egovMessageSource;

    @Resource(name = "egovGroupManageService")
    private EgovGroupManageService egovGroupManageService;

    /** EgovPropertyService */
    @Resource(name = "propertiesService")
    protected EgovPropertyService propertiesService;
	
    /** Message ID Generation */
    @Resource(name="egovGroupIdGnrService")    
    private EgovIdGnrService egovGroupIdGnrService;
    
    @Autowired
	private DefaultBeanValidator beanValidator;
    
    /**
	 * 그룹 목록화면 이동
	 * @return String
	 * @exception Exception
	 */
    @RequestMapping("/sec/gmt/EgovGroupListView.do")
    public String selectGroupListView()
            throws Exception {
        return "egovframework/com/sec/gmt/EgovGroupManage";
    }   
    
    
    @RequestMapping("/sec/gmt/EgovMberListPopup.do")
    public String egovMberListPopup() {
    	
    	return "egovframework/com/sec/gmt/EgovMberListPopup";
    }
    
	/**
	 * 시스템사용 목적별 그룹 목록 조회
	 * @param groupManageVO GroupManageVO
	 * @return String
	 * @exception Exception
	 */
    @IncludedInfo(name="그룹관리", listUrl="/sec/gmt/EgovGroupList.do", order = 80,gid = 20)
    @RequestMapping(value="/sec/gmt/EgovGroupList.do")
	public String selectGroupList(@ModelAttribute("groupManageVO") GroupManageVO groupManageVO, 
                                   ModelMap model) throws Exception {
    	/** paging */
    	PaginationInfo paginationInfo = new PaginationInfo();
		paginationInfo.setCurrentPageNo(groupManageVO.getPageIndex());
		paginationInfo.setRecordCountPerPage(groupManageVO.getPageUnit());
		paginationInfo.setPageSize(groupManageVO.getPageSize());
		
		groupManageVO.setFirstIndex(paginationInfo.getFirstRecordIndex());
		groupManageVO.setLastIndex(paginationInfo.getLastRecordIndex());
		groupManageVO.setRecordCountPerPage(paginationInfo.getRecordCountPerPage());
		
		groupManageVO.setGroupManageList(egovGroupManageService.selectGroupList(groupManageVO));
        model.addAttribute("groupList", groupManageVO.getGroupManageList());
        
        int totCnt = egovGroupManageService.selectGroupListTotCnt(groupManageVO);
		paginationInfo.setTotalRecordCount(totCnt);
        model.addAttribute("paginationInfo", paginationInfo);
        model.addAttribute("message", egovMessageSource.getMessage("success.common.select"));

        return "egovframework/com/sec/gmt/EgovGroupManage";
	}

	/**
	 * 검색조건에 따른 그룹정보를 조회
	 * @param groupManageVO GroupManageVO
	 * @return String
	 * @exception Exception
	 */
    @RequestMapping(value="/sec/gmt/EgovGroup.do")
	public String selectGroup(@ModelAttribute("groupManageVO") GroupManageVO groupManageVO, 
								@ModelAttribute("groupManage") GroupManage groupManage,
	    		               ModelMap model) throws Exception {

	    model.addAttribute("groupManage", egovGroupManageService.selectGroup(groupManageVO));
	    model.addAttribute("selectUsers", egovGroupManageService.selectUsers(groupManageVO));
	    
	    return "egovframework/com/sec/gmt/EgovGroupUpdate";
	}

    /**
	 * 그룹 등록화면 이동
	 * @return String
	 * @exception Exception
	 */     
    @RequestMapping(value="/sec/gmt/EgovGroupInsertView.do")
    public String insertGroupView(@ModelAttribute("groupManage") GroupManage groupManage)
            throws Exception {
        return "egovframework/com/sec/gmt/EgovGroupInsert";
    }

	/**
	 * 그룹 기본정보를 화면에서 입력하여 항목의 정합성을 체크하고 데이터베이스에 저장
	 * @param groupManage GroupManage
	 * @param groupManageVO GroupManageVO
	 * @return String
	 * @exception Exception
	 */ 
    @RequestMapping(value="/sec/gmt/EgovGroupInsert.do")
	public String insertGroup(@ModelAttribute("groupManage") GroupManage groupManage, 
			                  @ModelAttribute("groupManageVO") GroupManageVO groupManageVO, 
			                   BindingResult bindingResult,
			                   ModelMap model) throws Exception {
    	
    	beanValidator.validate(groupManage, bindingResult); //validation 수행
    	
    	if (bindingResult.hasErrors()) { 
			return "egovframework/com/sec/gmt/EgovGroupInsert";
		} else {
	    	groupManage.setGroupId(egovGroupIdGnrService.getNextStringId());
	        groupManageVO.setGroupId(groupManage.getGroupId());
	        
	        model.addAttribute("message", egovMessageSource.getMessage("success.common.insert"));
	        model.addAttribute("groupManage", egovGroupManageService.insertGroup(groupManage, groupManageVO));
	        return "forward:/sec/gmt/EgovGroupList.do";
		}
	}
    
	/**
	 * 화면에 조회된 그룹의 기본정보를 수정하여 항목의 정합성을 체크하고 수정된 데이터를 데이터베이스에 반영
	 * @param groupManage GroupManage
	 * @return String
	 * @exception Exception
	 */     
    @RequestMapping(value="/sec/gmt/EgovGroupUpdate.do")
	public String updateGroup(@ModelAttribute("groupManage") GroupManage groupManage, 
			                   BindingResult bindingResult,
                               Model model, @RequestParam("addedUser") String addedUser) throws Exception {
    	
    	beanValidator.validate(groupManage, bindingResult); //validation 수행
    	if (bindingResult.hasErrors()) { 
			return "egovframework/com/sec/gmt/EgovGroupUpdate";
		} else {
			/*TODO 팀원 추가 전처리*/
			HashMap param = new HashMap();
			param.put("teamId", groupManage.getGroupId());
			
			/*TODO 회원 ID로 검색하여 회원의 GROUP_ID 를 NULL 로 변경시킨다.*/
			
			List<HashMap> selectedTeamUsers = (List<HashMap>) egovGroupManageService.selectSelectedTeamUsers(param);
			for(HashMap a : selectedTeamUsers) {
				HashMap map = new HashMap();
				map.put("groupId", "");
				map.put("userId", a.get("ESNTL_ID"));
				egovGroupManageService.updateUserGroupId(map);
			}
			
			egovGroupManageService.deleteUserGroupMapping(param);
			String [] userArr = addedUser.split(",");
	        HashMap map = null;
	        for(String userId : userArr) {
	        	 map = new HashMap();
	        	 map.put("groupId", groupManage.getGroupId());
	        	 map.put("userId", userId);
				 /* TODO 체크된 팀원을 추가한다.*/
	        	 egovGroupManageService.updateUserGroupId(map); // update comtngnrlmber
	        	 egovGroupManageService.insertUserGroupMapping(map); // insert into kepco_team_users
	        }
			
    	    egovGroupManageService.updateGroup(groupManage);
            model.addAttribute("message", egovMessageSource.getMessage("success.common.update"));
    	    return "forward:/sec/gmt/EgovGroupList.do";
		}
	}	
	
	/**
	 * 불필요한 그룹정보를 화면에 조회하여 데이터베이스에서 삭제
	 * @param groupManage GroupManage
	 * @return String
	 * @exception Exception
	 */
	@RequestMapping(value="/sec/gmt/EgovGroupDelete.do")
	public String deleteGroup(@ModelAttribute("groupManage") GroupManage groupManage, 
                             Model model) throws Exception {
		egovGroupManageService.deleteGroup(groupManage);
		model.addAttribute("message", egovMessageSource.getMessage("success.common.delete"));
		return "forward:/sec/gmt/EgovGroupList.do";
	}

	/**
	 * 불필요한 그룹정보 목록을 화면에 조회하여 데이터베이스에서 삭제
	 * @param groupIds String
	 * @param groupManage GroupManage
	 * @return String
	 * @exception Exception
	 */   
	@RequestMapping(value="/sec/gmt/EgovGroupListDelete.do")
	public String deleteGroupList(@RequestParam("groupIds") String groupIds,
			                      @ModelAttribute("groupManage") GroupManage groupManage, 
	                               Model model) throws Exception {
    	String [] strGroupIds = groupIds.split(";");
    	for(int i=0; i<strGroupIds.length;i++) {
    		groupManage.setGroupId(strGroupIds[i]);
    		egovGroupManageService.deleteGroup(groupManage);
    	}

		model.addAttribute("message", egovMessageSource.getMessage("success.common.delete"));
		return "forward:/sec/gmt/EgovGroupList.do";
	}
	
    /**
	 * 그룹팝업 화면 이동
	 * @return String
	 * @exception Exception
	 */
    @RequestMapping("/sec/gmt/EgovGroupSearchView.do")
    public String selectGroupSearchView()
            throws Exception {
        return "egovframework/com/sec/gmt/EgovGroupSearch";
    }   
	    
	/**
	 * 시스템사용 목적별 그룹 목록 조회
	 * @param groupManageVO GroupManageVO
	 * @return String
	 * @exception Exception
	 */
    @RequestMapping(value="/sec/gmt/EgovGroupSearchList.do")
	public String selectGroupSearchList(@ModelAttribute("groupManageVO") GroupManageVO groupManageVO, 
                                   ModelMap model) throws Exception {
    	/** paging */
    	PaginationInfo paginationInfo = new PaginationInfo();
		paginationInfo.setCurrentPageNo(groupManageVO.getPageIndex());
		paginationInfo.setRecordCountPerPage(groupManageVO.getPageUnit());
		paginationInfo.setPageSize(groupManageVO.getPageSize());
		
		groupManageVO.setFirstIndex(paginationInfo.getFirstRecordIndex());
		groupManageVO.setLastIndex(paginationInfo.getLastRecordIndex());
		groupManageVO.setRecordCountPerPage(paginationInfo.getRecordCountPerPage());
		
		groupManageVO.setGroupManageList(egovGroupManageService.selectGroupList(groupManageVO));
        model.addAttribute("groupList", groupManageVO.getGroupManageList());
        
        int totCnt = egovGroupManageService.selectGroupListTotCnt(groupManageVO);
		paginationInfo.setTotalRecordCount(totCnt);
        model.addAttribute("paginationInfo", paginationInfo);
        model.addAttribute("message", egovMessageSource.getMessage("success.common.select"));

        return "egovframework/com/sec/gmt/EgovGroupSearch";
	}
    
    
 
    
    
    
    @RequestMapping("/sec/vmt/EgovVmGroupListView.do")
    public String selectVmGroupListView()
            throws Exception {
        return "egovframework/com/sec/vmt/EgovVmGroupManage";
    }   
 
    @IncludedInfo(name="그룹관리", listUrl="/sec/vmt/EgovVmGroupList.do", order = 80,gid = 20)
    @RequestMapping(value="/sec/vmt/EgovVmGroupList.do")
	public String selectvmGroupList(@ModelAttribute("groupManageVO") GroupManageVO groupManageVO, 
                                   ModelMap model) throws Exception {
    	/** paging */
    	PaginationInfo paginationInfo = new PaginationInfo();
		paginationInfo.setCurrentPageNo(groupManageVO.getPageIndex());
		paginationInfo.setRecordCountPerPage(groupManageVO.getPageUnit());
		paginationInfo.setPageSize(groupManageVO.getPageSize());
		
		groupManageVO.setFirstIndex(paginationInfo.getFirstRecordIndex());
		groupManageVO.setLastIndex(paginationInfo.getLastRecordIndex());
		groupManageVO.setRecordCountPerPage(paginationInfo.getRecordCountPerPage());
		
		groupManageVO.setGroupManageList(egovGroupManageService.selectVmGroupList(groupManageVO));
        model.addAttribute("groupList", groupManageVO.getGroupManageList());
        
        int totCnt = egovGroupManageService.selectVmGroupListTotCnt(groupManageVO);
		paginationInfo.setTotalRecordCount(totCnt);
        model.addAttribute("paginationInfo", paginationInfo);
        model.addAttribute("message", egovMessageSource.getMessage("success.common.select"));

        return "egovframework/com/sec/vmt/EgovVmGroupManage";
	}
    
    @RequestMapping(value="/sec/vmt/EgovVmGroupInsertView.do")
    public String insertVmGroupView(@ModelAttribute("groupManage") GroupManage groupManage, Model model, VmTypeVO vmTypeVO)
            throws Exception {
    	vmTypeVO.setVmTypeList(egovGroupManageService.selectVmTypeList(vmTypeVO));
    	model.addAttribute("typeList", vmTypeVO.getVmTypeList());
        return "egovframework/com/sec/vmt/EgovVmGroupInsert";
    }
    
    @RequestMapping(value="/sec/vmt/EgovVmGroupInsert.do")
	public String insertVmGroup(@ModelAttribute("groupManage") GroupManage groupManage, 
			                  	@ModelAttribute("groupManageVO") GroupManageVO groupManageVO, 
			                  	@RequestParam(value = "typeUrl", required = false, defaultValue = "") String typeUrl,
			                  	BindingResult bindingResult,
			                  	ModelMap model) throws Exception {
    	
    	beanValidator.validate(groupManage, bindingResult); //validation 수행
    	if (bindingResult.hasErrors()) { 
			return "egovframework/com/sec/vmt/EgovVmGroupInsert";
		} else {
	    	groupManage.setGroupId(egovGroupIdGnrService.getNextStringId());
	        groupManageVO.setGroupId(groupManage.getGroupId());
	        
	        model.addAttribute("message", egovMessageSource.getMessage("success.common.insert"));
	        model.addAttribute("groupManage", egovGroupManageService.insertVmGroup(groupManage, groupManageVO, typeUrl));
	        return "forward:/sec/vmt/EgovVmGroupList.do";
		}
	}
    
    @RequestMapping(value="/sec/vmt/EgovVmGroupListDelete.do")
	public String deleteVmGroupList(@RequestParam("groupIds") String groupIds,
			                      @ModelAttribute("groupManage") GroupManage groupManage, 
	                               Model model) throws Exception {
    	String [] strGroupIds = groupIds.split(";");
    	for(int i=0; i<strGroupIds.length;i++) {
    		groupManage.setGroupId(strGroupIds[i]);
    		egovGroupManageService.deleteVmGroup(groupManage);
    	}

		model.addAttribute("message", egovMessageSource.getMessage("success.common.delete"));
		return "forward:/sec/vmt/EgovVmGroupList.do";
	}
    
    @RequestMapping(value="/sec/vmt/EgovVmGroup.do")
	public String selectVmGroup(@ModelAttribute("groupManageVO") GroupManageVO groupManageVO, 
								@ModelAttribute("groupManage") GroupManage groupManage,
								ModelMap model, 
								VmTypeVO vmTypeVO,
								VmGroupTypeVO vmGroupTypeVO
			) throws Exception {
    
    	model.addAttribute("typeList", egovGroupManageService.selectVmTypeList2());	
    	model.addAttribute("groupTypeList", egovGroupManageService.selectVmGroupTypeList(groupManageVO));
	    model.addAttribute("groupManage", egovGroupManageService.selectVmGroup(groupManageVO));
	    return "egovframework/com/sec/vmt/EgovVmGroupUpdate";
	}
    
    @RequestMapping(value="/sec/vmt/EgovVmGroupUpdate.do")
	public String updateVmGroup(@ModelAttribute("groupManage") GroupManage groupManage, 
								@RequestParam(value = "typeUrl", required = false, defaultValue = "") String typeUrl,
			                   BindingResult bindingResult,
                               Model model) throws Exception {
    	
    	beanValidator.validate(groupManage, bindingResult); //validation 수행
    	
    	if (bindingResult.hasErrors()) { 
			return "egovframework/com/sec/vmt/EgovVMGroupUpdate";
		} else {
    	    egovGroupManageService.updateVmGroup(groupManage, typeUrl);
            model.addAttribute("message", egovMessageSource.getMessage("success.common.update"));
    	    return "forward:/sec/vmt/EgovVmGroupList.do";
		}
	}	
    @RequestMapping(value="/sec/vmt/EgovVmGroupSearchList.do")
	public String selectVmGroupSearchList(@ModelAttribute("groupManageVO") GroupManageVO groupManageVO, 
                                   ModelMap model) throws Exception {
    	/** paging */
    	PaginationInfo paginationInfo = new PaginationInfo();
		paginationInfo.setCurrentPageNo(groupManageVO.getPageIndex());
		paginationInfo.setRecordCountPerPage(groupManageVO.getPageUnit());
		paginationInfo.setPageSize(groupManageVO.getPageSize());
		
		groupManageVO.setFirstIndex(paginationInfo.getFirstRecordIndex());
		groupManageVO.setLastIndex(paginationInfo.getLastRecordIndex());
		groupManageVO.setRecordCountPerPage(paginationInfo.getRecordCountPerPage());
		
		groupManageVO.setGroupManageList(egovGroupManageService.selectVmGroupList(groupManageVO));
        model.addAttribute("groupList", groupManageVO.getGroupManageList());
        
        int totCnt = egovGroupManageService.selectVmGroupListTotCnt(groupManageVO);
		paginationInfo.setTotalRecordCount(totCnt);
        model.addAttribute("paginationInfo", paginationInfo);
        model.addAttribute("message", egovMessageSource.getMessage("success.common.select"));

        return "egovframework/com/sec/vmt/EgovVmGroupSearch";
	}
    
    
}