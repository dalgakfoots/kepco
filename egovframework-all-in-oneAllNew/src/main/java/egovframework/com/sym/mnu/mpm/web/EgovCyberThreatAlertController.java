package egovframework.com.sym.mnu.mpm.web;

import java.util.HashMap;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import egovframework.com.sym.mnu.mpm.service.EgovMenuManageService;
import egovframework.rte.fdl.property.EgovPropertyService;

@Controller
public class EgovCyberThreatAlertController {
	
	/** EgovPropertyService */
    @Resource(name = "propertiesService")
    protected EgovPropertyService propertiesService;
    
    /** EgovMenuManageService */
	@Resource(name = "meunManageService")
    private EgovMenuManageService menuManageService;

	
	private static final Logger LOGGER = LoggerFactory.getLogger(EgovCyberThreatAlertController.class);

	
	@RequestMapping(value="/sym/mnu/mpm/SelectCyberThreatAlert.do")
	public String SelectCyberThreatAlert(ModelMap model) throws Exception {
		HashMap cyberThreatAlertLevel = menuManageService.selectCyberThreatAlertLevel();
		model.addAttribute("searchResult", cyberThreatAlertLevel);
		return "egovframework/com/sym/mnu/mpm/EgovSelectCyberThreatAlert";
	}
	
}
