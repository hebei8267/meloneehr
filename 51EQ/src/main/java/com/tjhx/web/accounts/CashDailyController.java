package com.tjhx.web.accounts;

import java.text.ParseException;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.tjhx.common.utils.DateUtils;
import com.tjhx.entity.accounts.CashDaily;
import com.tjhx.globals.Constants;
import com.tjhx.service.accounts.CashDailyManager;
import com.tjhx.web.BaseController;

@Controller
@RequestMapping(value = "/cashDaily")
public class CashDailyController extends BaseController {
	@Resource
	private CashDailyManager cashDailyManager;

	/**
	 * 取得销售流水日结信息列表
	 * 
	 * @param model
	 * @return
	 * @throws ParseException
	 */
	@RequestMapping(value = { "list", "" })
	public String cashDailyList_Action(Model model, HttpSession session) throws ParseException {
		List<CashDaily> cashDailyList = cashDailyManager.getAllCashDailyByOrgId_1(getUserInfo(session)
				.getOrganization().getId(), DateUtils.getCurrentDateShortStr());
		model.addAttribute("cashDailyList", cashDailyList);

		List<CashDaily> noCashDailyList = cashDailyManager.getAllNotCashDailyByOrgId(getUserInfo(session)
				.getOrganization().getId());
		model.addAttribute("noCashDailyList", noCashDailyList);

		return "accounts/cashDailyList";
	}

	/**
	 * 取得销售流水日结信息列表
	 * 
	 * @param model
	 * @return
	 * @throws ParseException
	 */
	@RequestMapping(value = "list/{date}")
	public String cashDailyList_Date_Action(@PathVariable("date") String date, Model model, HttpSession session)
			throws ParseException {
		List<CashDaily> cashDailyList = cashDailyManager.getAllCashDailyByOrgId_2(getUserInfo(session)
				.getOrganization().getId(), date);
		model.addAttribute("cashDailyList", cashDailyList);

		List<CashDaily> noCashDailyList = cashDailyManager.getAllNotCashDailyByOrgId(getUserInfo(session)
				.getOrganization().getId());
		model.addAttribute("noCashDailyList", noCashDailyList);

		return "accounts/cashDailyList";
	}

	/**
	 * 销售流水日结
	 * 
	 * @param date
	 * @return
	 */
	@RequestMapping(value = "confirm/{date}")
	public String cashDailyConfirm_Action(@PathVariable("date") String optDate, HttpSession session) {

		cashDailyManager.cashDailyConfirm(optDate, getUserInfo(session).getOrganization().getId());

		return "redirect:/" + Constants.PAGE_REQUEST_PREFIX + "/cashDaily";
	}
}
