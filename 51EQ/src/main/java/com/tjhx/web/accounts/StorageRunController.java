package com.tjhx.web.accounts;

import java.lang.reflect.InvocationTargetException;
import java.text.ParseException;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.tjhx.common.utils.DateUtils;
import com.tjhx.entity.accounts.StorageRun;
import com.tjhx.entity.info.Supplier;
import com.tjhx.globals.Constants;
import com.tjhx.service.ServiceException;
import com.tjhx.service.accounts.StorageRunManager;
import com.tjhx.service.info.SupplierManager;
import com.tjhx.web.BaseController;

@Controller
@RequestMapping(value = "/storageRun")
public class StorageRunController extends BaseController {
	@Resource
	private StorageRunManager storageRunManager;
	@Resource
	private SupplierManager supplierManager;

	/**
	 * 取得货物入库流水信息列表
	 * 
	 * @param model
	 * @return
	 * @throws ParseException
	 */
	@RequestMapping(value = { "list", "" })
	public String storageRunList_Action(Model model, HttpSession session) throws ParseException {
		List<StorageRun> storageRunList = storageRunManager.getAllStorageRunByOrgId_1(getUserInfo(session)
				.getOrganization().getId(), DateUtils.getCurrentDateShortStr());

		model.addAttribute("storageRunList", storageRunList);

		return "accounts/storageRunList";
	}

	/**
	 * 取得货物入库流水信息列表
	 * 
	 * @param model
	 * @return
	 * @throws ParseException
	 */
	@RequestMapping(value = { "list/{date}" })
	public String storageRunList_Action(@PathVariable("date") String date, Model model, HttpSession session)
			throws ParseException {
		List<StorageRun> storageRunList = storageRunManager.getAllStorageRunByOrgId_2(getUserInfo(session)
				.getOrganization().getId(), date);

		model.addAttribute("storageRunList", storageRunList);

		return "accounts/storageRunList";
	}

	/**
	 * 新增货物入库流水初始化
	 * 
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "new")
	public String initStorageRun_Action(Model model) {

		StorageRun storageRun = new StorageRun();
		model.addAttribute("storageRun", storageRun);

		initSupplierList(model);

		return "accounts/storageRunForm";
	}

	/**
	 * 初始化供应商列表
	 * 
	 * @param model
	 */
	private void initSupplierList(Model model) {

		List<Supplier> _list = supplierManager.getAllGoodsSupplier();

		Map<String, String> supplier = new LinkedHashMap<String, String>();
		supplier.put("", "");

		for (Supplier _supplier : _list) {
			supplier.put(_supplier.getSupplierBwId(), _supplier.getSupplierBwId());
		}
		model.addAttribute("supplier", supplier);
	}

	/**
	 * 编辑货物入库流水信息
	 * 
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "edit/{id}")
	public String editStorageRun_Action(@PathVariable("id") Integer id, Model model) {

		StorageRun storageRun = storageRunManager.getStorageRunByUuid(id);
		if (null == storageRun) {
			return "redirect:/" + Constants.PAGE_REQUEST_PREFIX + "/storageRun/list";
		} else {
			model.addAttribute("storageRun", storageRun);
			initSupplierList(model);
			return "accounts/storageRunForm";
		}

	}

	/**
	 * 删除货物入库流水信息
	 * 
	 * @param ids
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "del")
	public String delStorageRun_Action(@RequestParam("uuids") String ids, Model model) {
		String[] idArray = ids.split(",");
		for (int i = 0; i < idArray.length; i++) {
			storageRunManager.delStorageRunByUuid(Integer.parseInt(idArray[i]));
		}

		return "redirect:/" + Constants.PAGE_REQUEST_PREFIX + "/storageRun/list";
	}

	/**
	 * 新增/修改 货物入库流水信息
	 * 
	 * @param storageRun
	 * @param model
	 * @return
	 * @throws IllegalAccessException
	 * @throws InvocationTargetException
	 */
	@RequestMapping(value = "save")
	public String saveStorageRun_Action(@ModelAttribute("storageRun") StorageRun storageRun, Model model,
			HttpSession session) throws IllegalAccessException, InvocationTargetException {

		if (null == storageRun.getUuid()) {// 新增操作
			try {
				storageRunManager.addNewStorageRun(storageRun, getUserInfo(session));
			} catch (ServiceException ex) {
				// 添加错误消息
				addInfoMsg(model, ex.getMessage());

				initSupplierList(model);

				return "accounts/storageRunForm";
			}
		} else {// 修改操作
			try {
				storageRunManager.updateStorageRun(storageRun, getUserInfo(session));
			} catch (ServiceException ex) {
				// 添加错误消息
				addInfoMsg(model, ex.getMessage());

				initSupplierList(model);

				return "accounts/storageRunForm";
			}
		}

		return "redirect:/" + Constants.PAGE_REQUEST_PREFIX + "/storageRun/list";
	}
}