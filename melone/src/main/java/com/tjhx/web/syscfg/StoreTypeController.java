package com.tjhx.web.syscfg;

import java.lang.reflect.InvocationTargetException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

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

	/**
	 * 仓库类型列表展现
	 * 
	 * @param model
	 * @return
	 */
	@RequestMapping(value = { "list", "" })
	public String storeTypeList_Action(Model model) {
		List<StoreType> storeTypeList = storeTypeManager.getAllStoreType();

		model.addAttribute("storeTypeList", storeTypeList);

		return "syscfg/storeTypeList";
	}

	/**
	 * 仓库类型编辑
	 * 
	 * @param model
	 * @return
	 */
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

	/**
	 * 删除仓库类型
	 * 
	 * @param ids
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "del")
	public String delStoreType_Action(@RequestParam("uuids") String ids, Model model) {
		String[] idArray = ids.split(",");
		for (int i = 0; i < idArray.length; i++) {
			storeTypeManager.delStoreTypeByUuid(idArray[i]);
		}

		return "redirect:/" + Constants.PAGE_REQUEST_PREFIX + "/syscfg/storeType/list";
	}

	/**
	 * 新增仓库类型初始化
	 * 
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "new")
	public String newStoreType_Action(Model model) {
		blankStoreType(model);

		return "syscfg/storeTypeForm";
	}

	private void blankStoreType(Model model) {
		StoreType storeType = new StoreType();
		model.addAttribute("storeType", storeType);
	}

	/**
	 * 新增/修改 仓库类型
	 * 
	 * @param storeType
	 * @param model
	 * @return
	 * @throws IllegalAccessException
	 * @throws InvocationTargetException
	 */
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
