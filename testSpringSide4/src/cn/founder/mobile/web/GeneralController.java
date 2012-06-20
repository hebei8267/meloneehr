package cn.founder.mobile.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class GeneralController {

	@RequestMapping(value = "/mobile/{page}")
	public String story(@PathVariable("page") String page) {
		return page;
	}

}
