package org.springside.examples.showcase.common.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springside.examples.showcase.common.service.AccountManager;

@Controller
public class GeneralController {
	@Autowired
	private AccountManager accountManager;

	@RequestMapping(value = "/index")
	public String index(HttpServletRequest request) {
		// 此方法没有任何数据库操作，调用时也会取得数据库连接
		// accountManager.tmp();

		return "index";
	}

	@RequestMapping(value = "/index/setSession")
	public String index_setSession(HttpServletRequest request) {

		request.getSession().setAttribute("AAAAA", "AAAAAAAA");
		request.getSession().setAttribute("BBBBB", "BBBBBBBB");
		request.getSession().setAttribute("CCCCC", "CCCCCCCC");

		return "index";
	}

	@RequestMapping(value = "/index/getSession")
	public String index_getSession(HttpServletRequest request) {
		HttpSession s = request.getSession();
		System.out.println(s.getAttribute("AAAAA"));
		System.out.println(s.getAttribute("BBBBB"));
		System.out.println(s.getAttribute("CCCCC"));

		return "index";
	}

	@RequestMapping(value = "/index/removeAttribute")
	public String index_removeAttribute(HttpServletRequest request) {
		HttpSession s = request.getSession();
		System.out.println(s.getAttribute("AAAAA"));
		System.out.println(s.getAttribute("BBBBB"));
		System.out.println(s.getAttribute("CCCCC"));
		s.removeAttribute("BBBBB");
		System.out.println(s.getAttribute("AAAAA"));
		System.out.println(s.getAttribute("BBBBB"));
		System.out.println(s.getAttribute("CCCCC"));

		return "index";
	}

	@RequestMapping(value = "/index/removeSession")
	public String index_removeSession(HttpServletRequest request) {
		HttpSession s = request.getSession();
		s.invalidate();
		System.out.println(s.getAttribute("AAAAA"));
		System.out.println(s.getAttribute("BBBBB"));
		System.out.println(s.getAttribute("CCCCC"));

		return "index";
	}

	@RequestMapping(value = "/story/{page}")
	public String story(@PathVariable("page") String page) {
		return "story/" + page;
	}

}
