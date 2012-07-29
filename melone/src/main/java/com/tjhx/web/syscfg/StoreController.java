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

import com.tjhx.entity.shop.Store;
import com.tjhx.entity.shop.StoreType;
import com.tjhx.globals.Constants;
import com.tjhx.service.ServiceException;
import com.tjhx.service.shop.StoreManager;
import com.tjhx.service.shop.StoreTypeManager;
import com.tjhx.web.BaseController;

@Controller
@RequestMapping(value = "/syscfg/store")
public class StoreController extends BaseController {
	@Autowired
	private StoreManager storeManager;
	@Autowired
	private StoreTypeManager storeTypeManager;

	/**
	 * 取得仓库列表
	 * 
	 * @param model
	 * @return
	 */
	@RequestMapping(value = { "list", "" })
	public String storeList_Action(Model model) {
		List<Store> storeList = storeManager.getAllStore();

		model.addAttribute("storeList", storeList);

		return "syscfg/storeList";
	}

	/**
	 * 编辑仓库信息
	 * 
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "edit/{id}")
	public String editStore_Action(@PathVariable("id") Integer id, Model model) {

		Store store = storeManager.getStoreByUuid(id);
		if (null == store) {
			return "redirect:/" + Constants.PAGE_REQUEST_PREFIX + "/syscfg/store/list";
		} else {
			model.addAttribute("store", store);

			// 初始化仓库类型信息下拉菜单
			initStoreTypeSelect(model);

			return "syscfg/storeForm";
		}

	}

	/**
	 * 删除仓库信息
	 * 
	 * @param ids
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "del")
	public String delStore_Action(@RequestParam("uuids") String ids, Model model) {
		String[] idArray = ids.split(",");
		for (int i = 0; i < idArray.length; i++) {
			storeManager.delStoreByUuid(Integer.parseInt(idArray[i]));
		}

		return "redirect:/" + Constants.PAGE_REQUEST_PREFIX + "/syscfg/store/list";
	}

	/**
	 * 新增仓库初始化
	 * 
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "new")
	public String initStore_Action(Model model) {
		Store store = new Store();
		model.addAttribute("store", store);

		// 初始化仓库类型信息下拉菜单
		initStoreTypeSelect(model);

		return "syscfg/storeForm";
	}

	/**
	 * 初始化仓库类型信息下拉菜单
	 * 
	 * @param model
	 */
	private void initStoreTypeSelect(Model model) {
		List<StoreType> storeTypeList = storeTypeManager.getAllStoreType();

		model.addAttribute("storeTypeList", storeTypeList);
	}

	/**
	 * 新增/修改 仓库信息
	 * 
	 * @param store 仓库
	 * @param model
	 * @return
	 * @throws IllegalAccessException
	 * @throws InvocationTargetException
	 */
	@RequestMapping(value = "save")
	public String saveStore_Action(@ModelAttribute("store") Store store, Model model) throws IllegalAccessException,
			InvocationTargetException {

		if (null == store.getUuid()) {// 新增操作
			try {
				storeManager.addNewStore(store);
			} catch (ServiceException ex) {
				// 添加错误消息
				addInfoMsg(model, ex.getMessage());

				// 新增仓库初始化-清空重复仓库编号
				store.setId(null);
				model.addAttribute("store", store);

				// 初始化仓库类型信息下拉菜单
				initStoreTypeSelect(model);

				return "syscfg/storeForm";
			}
		} else {// 修改操作
			try {
				storeManager.updateStore(store);
			} catch (ServiceException ex) {
				// 添加错误消息
				addInfoMsg(model, ex.getMessage());
			}
		}

		return "redirect:/" + Constants.PAGE_REQUEST_PREFIX + "/syscfg/store/list";
	}
}