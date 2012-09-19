package com.tjhx.web.sell;

import java.lang.reflect.InvocationTargetException;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.beanutils.BeanUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springside.modules.mapper.JsonMapper;

import com.tjhx.dto.PaymentDTO;
import com.tjhx.web.BaseController;

@Controller
@RequestMapping(value = "/sell")
public class SellController extends BaseController {

	/**
	 * 销售页面初始化(含输入键盘)
	 * 
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "sell1")
	public String initSell_Action(Model model, HttpServletRequest request) {

		return "sell/sell_1";
	}

	/**
	 * @param model
	 * @param request
	 * @return
	 * @throws InvocationTargetException
	 * @throws IllegalAccessException
	 */
	@RequestMapping(value = "payment")
	// ajax请求,json数据返回
	@ResponseBody
	public String payment(Model model, HttpServletRequest request) throws IllegalAccessException,
			InvocationTargetException {
		PaymentDTO payment = new PaymentDTO();
		// 取得Request中的查询参数
		BeanUtils.populate(payment, request.getParameterMap());
		
		

		JsonMapper mapper = new JsonMapper();
		return mapper.toJson(null);
	}

}
