package com.tjhx.web.syscfg;

import java.lang.reflect.InvocationTargetException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.tjhx.entity.shop.StoreType;
import com.tjhx.globals.Constants;
import com.tjhx.service.ServiceException;
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

	@RequestMapping(value = "edit/{id}")
	public String editStoreType_Action(@PathVariable("id") Integer id, Model model) {

		StoreType storeType = storeTypeManager.getStoreTypeByUuid(id);
		if (null == storeType) {
			return "redirect:/" + Constants.PAGE_REQUEST_PREFIX + "/syscfg/storeType/list";
		} else {
			model.addAttribute("storeType", storeType);
			return "syscfg/storeTypeForm";
		}

	}

	@RequestMapping(value = "del/{id}")
	public String delStoreType_Action(@PathVariable("id") Integer id, Model model) {
		storeTypeManager.delStoreTypeByUuid(id);
		return "redirect:/" + Constants.PAGE_REQUEST_PREFIX + "/syscfg/storeType/list";
	}

	@RequestMapping(value = "new")
	public String newStoreType_Action(Model model) {
		blankStoreType(model);

		return "syscfg/storeTypeForm";
	}

	private void blankStoreType(Model model) {
		StoreType storeType = new StoreType();
		model.addAttribute("storeType", storeType);
	}

	@RequestMapping(value = "save")
	public String saveStoreType_Action(@ModelAttribute("storeType") StoreType storeType, Model model)
			throws IllegalAccessException, InvocationTargetException {

		if (null == storeType.getUuid()) {// 新增操作

			try {
				storeTypeManager.saveNewStoreType(storeType);
			} catch (ServiceException ex) {
				blankStoreType(model);
				// 添加错误消息
				addInfoMsg(model, ex.getMessage());
				return "syscfg/storeTypeForm";
			}
		} else {// 修改操作
			try {
				storeTypeManager.updateStoreType(storeType);
			} catch (ServiceException ex) {
				blankStoreType(model);
				// 添加错误消息
				addInfoMsg(model, ex.getMessage());
				return "syscfg/storeTypeForm";
			}
		}

		return "redirect:/" + Constants.PAGE_REQUEST_PREFIX + "/syscfg/storeType/list";
	}

}
