package com.tjhx.web.syscfg;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.tjhx.entity.shop.StoreType;
import com.tjhx.service.shop.StoreTypeManager;
import com.tjhx.web.BaseController;

@Controller
@RequestMapping(value = "/syscfg/storeType")
public class StoreTypeController extends BaseController {
	@Autowired
	private StoreTypeManager storeTypeManager;

	@RequestMapping(value = "list")
	public String storeTypeList_Action(Model model) {
		List<StoreType> storeTypeList = storeTypeManager.getAllStoreType();
		
		model.addAttribute("storeTypeList", storeTypeList);
		
		return "syscfg/storeTypeList";
	}
}
