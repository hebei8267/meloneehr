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
import com.tjhx.entity.accounts.CashRun;
import com.tjhx.globals.Constants;
import com.tjhx.service.accounts.CashDailyManager;
import com.tjhx.service.info.BankManager;
import com.tjhx.web.BaseController;

@Controller
@RequestMapping(value = "/cashDaily")
public class CashDailyController extends BaseController {
	@Resource
	private CashDailyManager cashDailyManager;
	@Resource
	private BankManager bankManager;

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

	/**
	 * 日结销售流水明细查看
	 * 
	 * @return
	 */
	@RequestMapping(value = "detail/{date}")
	public String cashDailyDetail_Action(@PathVariable("date") String optDate, Model model, HttpSession session) {
		List<CashRun> _list = cashDailyManager.cashDailyDetail(optDate, getUserInfo(session).getOrganization().getId());

		if (null != _list && _list.size() > 0) {
			CashRun cashRun1 = _list.get(0);
			cashRun1.setBankName(bankManager.getBankName(cashRun1.getBankId()));
			model.addAttribute("cashRun1", cashRun1);
		}
		if (null != _list && _list.size() > 1) {
			CashRun cashRun2 = _list.get(1);
			cashRun2.setBankName(bankManager.getBankName(cashRun2.getBankId()));
			model.addAttribute("cashRun2", cashRun2);
		}
		return "accounts/cashDailyForm";
	}
}