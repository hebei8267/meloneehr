package com.tjhx.web.syscfg;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.tjhx.entity.shop.StoreType;
import com.tjhx.service.shop.StoreTypeManager;
import com.tjhx.web.BaseController;

@Controller
@RequestMapping(value = "/syscfg/storeType")
public class StoreTypeController extends BaseController {
	@Autowired
	private StoreTypeManager storeTypeManager;

	@RequestMapping(value = { "list", "" })
	public String storeTypeList_Action(Model model) {
		List<StoreType> storeTypeList = storeTypeManager.getAllStoreType();

		model.addAttribute("storeTypeList", storeTypeList);

		return "syscfg/storeTypeList";
	}

	@RequestMapping(value = "get/{id}")
	public String getStoreType_Action(@PathVariable("id") Integer id, Model model) {
		StoreType storeType = storeTypeManager.getStoreTypeByUuid(id);

		model.addAttribute("storeType", storeType);
		// TODO hebei
		return "";
	}

	@RequestMapping(value = "del/{id}")
	public String delStoreType_Action(@PathVariable("id") Integer id, Model model) {
		storeTypeManager.delStoreTypeByUuid(id);
		// TODO hebei
		return storeTypeList_Action(model);
	}

	@RequestMapping(value = "create")
	public String createStoreType_Action() {
		return "syscfg/storeTypeForm";
	}

	@RequestMapping(value = "save")
	public String saveStoreType_Action(Model model) {
		// TODO hebei
		return "";
	}
}
