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
				updateMap.put("id" , elements[0]);
				updateMap.put("training_id", elements[1]);
				updateMap.put("team_id", elements[2]);
				updateMap.put("pstVmGroupId", elements[3]);
				updateMap.put("mdtVmGroupId", elements[4]);
				updateMap.put("watVmGroupId", elements[5]);
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
				deleteMap.put("pstVmGroupId", elements[3]);
				deleteMap.put("mdtVmGroupId", elements[4]);
				deleteMap.put("watVmGroupId", elements[5]);
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
				insertMap.put("pstVmGroupId", elements[3]);
				insertMap.put("mdtVmGroupId", elements[4]);
				insertMap.put("watVmGroupId", elements[5]);
				egovGroupManageService.insertTrainGroupVmManage(insertMap);
			}
			
		}
		return "redirect:/sec/tmt/EgovTrainGroupVmManage.do";
	}
}