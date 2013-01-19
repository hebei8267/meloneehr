package com.tjhx.web.accounts;

import java.lang.reflect.InvocationTargetException;
import java.text.ParseException;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.tjhx.common.utils.DateUtils;
import com.tjhx.entity.accounts.CashRun;
import com.tjhx.globals.Constants;
import com.tjhx.service.ServiceException;
import com.tjhx.service.accounts.CashRunManager;
import com.tjhx.web.BaseController;

@Controller
@RequestMapping(value = "/cashRun")
public class CashRunController extends BaseController {
	@Resource
	private CashRunManager cashRunManager;

	/**
	 * 取得CashRun信息列表
	 * 
	 * @param model
	 * @return
	 * @throws ParseException
	 */
	@RequestMapping(value = { "list", "" })
	public String cashRunList_Action(Model model, HttpSession session) throws ParseException {
		List<CashRun> cashRunList = cashRunManager.getAllCashRunByOrgId_1(getUserInfo(session).getOrganization()
				.getId(), DateUtils.getCurrentDateShortStr());

		model.addAttribute("cashRunList", cashRunList);

		return "accounts/cashRunList";
	}

	/**
	 * 取得CashRun信息列表
	 * 
	 * @param model
	 * @return
	 * @throws ParseException
	 */
	@RequestMapping(value = "list/{date}")
	public String cashRunList_Date_Action(Model model, HttpSession session) throws ParseException {
		List<CashRun> cashRunList = cashRunManager.getAllCashRunByOrgId_2(getUserInfo(session).getOrganization()
				.getId(), DateUtils.getCurrentDateShortStr());

		model.addAttribute("cashRunList", cashRunList);

		return "accounts/cashRunList";
	}

	/**
	 * 编辑CashRun信息
	 * 
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "edit/{id}")
	public String editCashRun_Action(@PathVariable("id") Integer id, Model model, HttpSession session) {

		CashRun cashRun = cashRunManager.getCashRunByUuid(id);
		if (null == cashRun) {
			return "redirect:/" + Constants.PAGE_REQUEST_PREFIX + "/cashRun/list";
		} else {
			model.addAttribute("cashRun", cashRun);
			return "cashRunForm";
		}

	}

	/**
	 * 删除CashRun信息
	 * 
	 * @param ids
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "del")
	public String delCashRun_Action(@RequestParam("uuids") String ids, Model model, HttpSession session) {
		String[] idArray = ids.split(",");
		for (int i = 0; i < idArray.length; i++) {
			cashRunManager.delCashRunByUuid(Integer.parseInt(idArray[i]));
		}

		return "redirect:/" + Constants.PAGE_REQUEST_PREFIX + "/cashRun/list";
	}

	/**
	 * 新增CashRun初始化
	 * 
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "new")
	public String initCashRun_Action(Model model, HttpSession session) {
		// TODO 修改点
		CashRun cashRun = new CashRun();
		model.addAttribute("cashRun", cashRun);

		return "cashRunForm";
	}

	/**
	 * 新增/修改 CashRun信息
	 * 
	 * @param cashRun
	 * @param model
	 * @return
	 * @throws IllegalAccessException
	 * @throws InvocationTargetException
	 */
	@RequestMapping(value = "save")
	public String saveCashRun_Action(@ModelAttribute("cashRun") CashRun cashRun, Model model, HttpSession session)
			throws IllegalAccessException, InvocationTargetException {

		if (null == cashRun.getUuid()) {// 新增操作
			cashRunManager.addNewCashRun(cashRun);
		} else {// 修改操作
			try {
				cashRunManager.updateCashRun(cashRun);
			} catch (ServiceException ex) {
				// 添加错误消息
				addInfoMsg(model, ex.getMessage());
			}
		}

		return "redirect:/" + Constants.PAGE_REQUEST_PREFIX + "/cashRun/list";
	}
}