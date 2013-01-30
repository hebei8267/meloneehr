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
import com.tjhx.entity.accounts.StoreRun;
import com.tjhx.entity.info.Supplier;
import com.tjhx.globals.Constants;
import com.tjhx.service.ServiceException;
import com.tjhx.service.accounts.StoreRunManager;
import com.tjhx.service.info.SupplierManager;
import com.tjhx.web.BaseController;

@Controller
@RequestMapping(value = "/storeRun")
public class StoreRunController extends BaseController {
	@Resource
	private StoreRunManager storeRunManager;
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
	public String storeRunList_Action(Model model, HttpSession session) throws ParseException {
		List<StoreRun> storeRunList = storeRunManager.getAllStoreRunByOrgId_1(getUserInfo(session).getOrganization()
				.getId(), DateUtils.getCurrentDateShortStr());

		model.addAttribute("storeRunList", storeRunList);

		StoreRun totalStoreRun = storeRunManager.calTotal(storeRunList);
		model.addAttribute("totalStoreRun", totalStoreRun);

		return "accounts/storeRunList";
	}

	/**
	 * 取得货物入库流水信息列表
	 * 
	 * @param model
	 * @return
	 * @throws ParseException
	 */
	@RequestMapping(value = "list/{date}")
	public String storeRunList_Action(@PathVariable("date") String date, Model model, HttpSession session)
			throws ParseException {
		List<StoreRun> storeRunList = storeRunManager.getAllStoreRunByOrgId_2(getUserInfo(session).getOrganization()
				.getId(), date);

		model.addAttribute("storeRunList", storeRunList);

		StoreRun totalStoreRun = storeRunManager.calTotal(storeRunList);
		model.addAttribute("totalStoreRun", totalStoreRun);

		return "accounts/storeRunList";
	}

	/**
	 * 新增货物入库流水初始化
	 * 
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "new")
	public String initStoreRun_Action(Model model) {

		StoreRun storeRun = new StoreRun();
		model.addAttribute("storeRun", storeRun);

		initSupplierList(model);
		initStoreTypeList(model);

		return "accounts/storeRunForm";
	}

	/**
	 * 初始化入库类型
	 * 
	 * @param model
	 */
	private void initStoreTypeList(Model model) {
		Map<String, String> storeTypeList = new LinkedHashMap<String, String>();
		storeTypeList.put("", "");
		storeTypeList.put("A", "挂账采购");
		storeTypeList.put("B", "现结采购");
		storeTypeList.put("C", "货商补欠");
		model.addAttribute("storeTypeList", storeTypeList);
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
			supplier.put(_supplier.getSupplierBwId(), _supplier.getName());
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
	public String editStoreRun_Action(@PathVariable("id") Integer id, Model model) {

		StoreRun storeRun = storeRunManager.getStoreRunByUuid(id);
		if (null == storeRun) {
			return "redirect:/" + Constants.PAGE_REQUEST_PREFIX + "/storeRun/list";
		} else {
			model.addAttribute("storeRun", storeRun);

			initSupplierList(model);
			initStoreTypeList(model);

			return "accounts/storeRunForm";
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
	public String delStoreRun_Action(@RequestParam("uuids") String ids, Model model) {
		String[] idArray = ids.split(",");
		for (int i = 0; i < idArray.length; i++) {
			storeRunManager.delStoreRunByUuid(Integer.parseInt(idArray[i]));
		}

		return "redirect:/" + Constants.PAGE_REQUEST_PREFIX + "/storeRun/list";
	}

	/**
	 * 新增/修改 货物入库流水信息
	 * 
	 * @param storeRun
	 * @param model
	 * @return
	 * @throws IllegalAccessException
	 * @throws InvocationTargetException
	 */
	@RequestMapping(value = "save")
	public String saveStoreRun_Action(@ModelAttribute("storeRun") StoreRun storeRun, Model model, HttpSession session)
			throws IllegalAccessException, InvocationTargetException {

		if (null == storeRun.getUuid()) {// 新增操作
			try {
				storeRunManager.addNewStoreRun(storeRun, getUserInfo(session));
			} catch (ServiceException ex) {
				// 添加错误消息
				addInfoMsg(model, ex.getMessage());

				initSupplierList(model);
				initStoreTypeList(model);

				return "accounts/storeRunForm";
			}
		} else {// 修改操作
			try {
				storeRunManager.updateStoreRun(storeRun, getUserInfo(session));
			} catch (ServiceException ex) {
				// 添加错误消息
				addInfoMsg(model, ex.getMessage());

				initSupplierList(model);
				initStoreTypeList(model);

				return "accounts/storeRunForm";
			}
		}

		return "redirect:/" + Constants.PAGE_REQUEST_PREFIX + "/storeRun/list";
	}

	// ---------------------------------------------------------------------------------------------
	// 审核
	// ---------------------------------------------------------------------------------------------
	/**
	 * 取得货物入库流水信息列表(审核)
	 * 
	 * @param model
	 * @return
	 * @throws ParseException
	 */
	@RequestMapping(value = "auditList")
	public String storeRunAuditList_Action(Model model, HttpSession session) throws ParseException {
		List<StoreRun> storeRunList = storeRunManager.getAllStoreRunByOrgId_1(getUserInfo(session).getOrganization()
				.getId(), DateUtils.getCurrentDateShortStr());

		model.addAttribute("storeRunList", storeRunList);

		StoreRun totalStoreRun = storeRunManager.calTotal(storeRunList);
		model.addAttribute("totalStoreRun", totalStoreRun);

		return "accounts/storeRunAuditList";
	}

	/**
	 * 取得货物入库流水信息列表
	 * 
	 * @param model
	 * @return
	 * @throws ParseException
	 */
	@RequestMapping(value = "auditList/{date}")
	public String storeRunAuditList_Action(@PathVariable("date") String date, Model model, HttpSession session)
			throws ParseException {
		List<StoreRun> storeRunList = storeRunManager.getAllStoreRunByOrgId_2(getUserInfo(session).getOrganization()
				.getId(), date);

		model.addAttribute("storeRunList", storeRunList);

		StoreRun totalStoreRun = storeRunManager.calTotal(storeRunList);
		model.addAttribute("totalStoreRun", totalStoreRun);

		return "accounts/storeRunAuditList";
	}

	/**
	 * 初始化审核货物入库流水信息
	 * 
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "audit/{id}")
	public String auditStoreRun_Action(@PathVariable("id") Integer id, Model model) {

		StoreRun storeRun = storeRunManager.getStoreRunByUuid(id);
		if (null == storeRun) {
			return "redirect:/" + Constants.PAGE_REQUEST_PREFIX + "/storeRun/auditList";
		} else {
			model.addAttribute("storeRun", storeRun);

			initSupplierList(model);
			initStoreTypeList(model);

			return "accounts/storeRunAuditForm";
		}

	}

	/**
	 * 货物入库流水信息-审核确认
	 * 
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "auditConfirm/{id}")
	public String auditConfirmStoreRun_Action(@PathVariable("id") Integer storeRunUuid, Model model) {

		storeRunManager.auditConfirmStoreRun(storeRunUuid);

		return "redirect:/" + Constants.PAGE_REQUEST_PREFIX + "/storeRun/auditList";
	}
}