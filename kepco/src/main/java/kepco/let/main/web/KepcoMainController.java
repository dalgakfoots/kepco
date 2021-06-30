package kepco.let.main.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class KepcoMainController {


	@RequestMapping(value ="/let/main/mainPage.do")
	public String getMainPage() throws Exception{
		return "main/KepcoMainView";
	}
}
