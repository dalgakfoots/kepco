/*
 * eGovFrame LDAP조직도관리
 * Copyright The eGovFrame Open Community (http://open.egovframe.go.kr)).
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 * @author 전우성(슈퍼개발자K3)
 */
package egovframework.com.dash.web;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import egovframework.com.cmm.EgovMessageSource;
import egovframework.com.cmm.annotation.IncludedInfo;
import egovframework.com.dash.service.EgovDashManageService;
import egovframework.com.ext.ldapumt.service.EgovOrgManageLdapService;
import egovframework.com.ext.ldapumt.service.UcorgVO;
import egovframework.com.ext.ldapumt.service.UserVO;
import egovframework.com.uss.ion.ecc.service.EgovEventCmpgnService;
import egovframework.com.uss.ion.ecc.service.EventCmpgnVO;
import egovframework.rte.fdl.property.EgovPropertyService;
import egovframework.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class EgovDashManageController {

	
	@Resource(name = "EgovEventCmpgnService")
	private EgovEventCmpgnService egovEventCmpgnService;
	
	@Resource(name = "EgovDashManageService")
	private EgovDashManageService egovDashManageService;

    @Resource(name = "propertiesService")
    protected EgovPropertyService propertiesService;

	
	@RequestMapping(value="/dash/DashboardTraining.do")
	public String selectEventCmpgnList(ModelMap model) throws Exception {
		
       List<?> sampleList = egovEventCmpgnService.selectEventCmpgnListForMonitor();
        model.addAttribute("resultList", sampleList);

		return "egovframework/com/dash/DashboardTraining";

	}

	@RequestMapping(value = "/dash/DashboardView.do")
	public String selectDashManageView(@RequestParam(value = "trainingId") String trainingId, ModelMap model) throws Exception {
		List<?> trainingTeams = egovDashManageService.selectTrainingTeams(trainingId);
		
		model.addAttribute("training_name", egovDashManageService.selectTrainingName(trainingId));
		model.addAttribute("training_id", trainingId);
		model.addAttribute("rankList", egovDashManageService.selectDashTable(trainingId));
		
		
		return "egovframework/com/dash/Dashboard";
	}
	

	@RequestMapping(value = "/dash/Dashboard.do")
	public ModelAndView selectDashManage(@RequestParam(value = "trainingId") String trainingId) throws Exception {
		ModelAndView modelAndView = new ModelAndView();
    	modelAndView.setViewName("jsonView");
    	
    	modelAndView = egovDashManageService.selectDashGragh(trainingId, modelAndView);
    	modelAndView.addObject("rankList", egovDashManageService.selectDashTable(trainingId));
		return modelAndView;
	}
	

	
	
	@RequestMapping(value = "/dash/EgovDashbordTrainingView.do")
	public String selectEgocDashManageView(@ModelAttribute("searchVO") EventCmpgnVO searchVO, ModelMap model) throws Exception {

    	/** EgovPropertyService.sample */
    	searchVO.setPageUnit(propertiesService.getInt("pageUnit"));
    	searchVO.setPageSize(propertiesService.getInt("pageSize"));

    	/** pageing */
    	PaginationInfo paginationInfo = new PaginationInfo();
		paginationInfo.setCurrentPageNo(searchVO.getPageIndex());
		paginationInfo.setRecordCountPerPage(searchVO.getPageUnit());
		paginationInfo.setPageSize(searchVO.getPageSize());

		searchVO.setFirstIndex(paginationInfo.getFirstRecordIndex());
		searchVO.setLastIndex(paginationInfo.getLastRecordIndex());
		searchVO.setRecordCountPerPage(paginationInfo.getRecordCountPerPage());

        List<?> sampleList = egovEventCmpgnService.selectEventCmpgnList(searchVO);
        model.addAttribute("resultList", sampleList);

        int totCnt = egovEventCmpgnService.selectEventCmpgnListCnt(searchVO);
		paginationInfo.setTotalRecordCount(totCnt);
        model.addAttribute("paginationInfo", paginationInfo);

		
		return "egovframework/com/dash/EgovDashbordTraining";
	}
	
	@RequestMapping(value = "/dash/EgovDashboardView.do")
	public String selectEgovDashManageView(@RequestParam(value = "trainingId") String trainingId, ModelMap model) throws Exception {
		List<?> trainingTeams = egovDashManageService.selectTrainingTeams(trainingId);
		
		model.addAttribute("training_name", egovDashManageService.selectTrainingName(trainingId));
		model.addAttribute("training_id", trainingId);
		model.addAttribute("rankList", egovDashManageService.selectDashTable(trainingId));
		
		return "egovframework/com/dash/EgovDashboard";
	}
	
	
	@RequestMapping(value = "/dash/test.do")
	public void test(HashMap model) throws Exception {
		
		String trainingId = "EVENT_00000000000061";
		egovDashManageService.insertDashScore(trainingId, model);
	}



}
