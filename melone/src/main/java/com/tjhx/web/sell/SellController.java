package com.tjhx.web.sell;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.tjhx.web.BaseController;

@Controller
@RequestMapping(value = "/sell")
public class SellController extends BaseController {

	/**
	 * 销售页面初始化
	 * 
	 * @param model
	 * @return
	 */
	@RequestMapping
	public String initSell_Action(Model model, HttpServletRequest request) {

		return "sell/sell";
	}

}
