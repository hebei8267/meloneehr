package com.tjhx.web.report;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.ServletRequestBindingException;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springside.modules.utils.SpringContextHolder;

import com.tjhx.common.utils.DateUtils;
import com.tjhx.entity.accounts.CashDaily;
import com.tjhx.entity.accounts.CashRun;
import com.tjhx.globals.SysConfig;
import com.tjhx.service.accounts.CashDailyManager;
import com.tjhx.service.struct.OrganizationManager;
import com.tjhx.web.BaseController;

@Controller
@RequestMapping(value = "/cashReport")
public class CashReportController extends BaseController {
	@Resource
	private OrganizationManager orgManager;
	@Resource
	private CashDailyManager cashDailyManager;

	@RequestMapping(value = { "list", "" })
	public String cashReportList_Action(Model model) throws ServletRequestBindingException {

		ReportUtils.initOrgList(orgManager, model);

		SysConfig sysConfig = SpringContextHolder.getBean("sysConfig");
		model.addAttribute("DEFAULT_RETAINED_AMT", sysConfig.getDefaultRetainedAmt());

		return "report/cashListReport";
	}

	@RequestMapping(value = "search")
	public String cashReportSearch_Action(Model model, HttpServletRequest request)
			throws ServletRequestBindingException {
		String orgId = ServletRequestUtils.getStringParameter(request, "orgId");
		String optDateShow = ServletRequestUtils.getStringParameter(request, "optDateShow");
		model.addAttribute("orgId", orgId);
		model.addAttribute("optDateShow", optDateShow);

		CashDaily _cashDaily = new CashDaily();
		if (StringUtils.isNotBlank(orgId)) {
			_cashDaily.setOrgId(orgId);
		}
		if (StringUtils.isNotBlank(optDateShow)) {
			String optDateY = DateUtils.transDateFormat(optDateShow, "yyyy-MM", "yyyy");
			String optDateM = DateUtils.transDateFormat(optDateShow, "yyyy-MM", "MM");

			_cashDaily.setOptDateY(optDateY);
			_cashDaily.setOptDateM(optDateM);
		}

		List<CashDaily> _cashDailyList = cashDailyManager.searchReportList(_cashDaily);
		model.addAttribute("cashDailyList", _cashDailyList);

		ReportUtils.initOrgList(orgManager, model);

		SysConfig sysConfig = SpringContextHolder.getBean("sysConfig");
		model.addAttribute("DEFAULT_RETAINED_AMT", sysConfig.getDefaultRetainedAmt());

		return "report/cashListReport";
	}

	@RequestMapping(value = "detail/{optDate}/{orgId}")
	public String cashReportView_Action(@PathVariable("optDate") String optDate, @PathVariable("orgId") String orgId,
			Model model) {
		CashRun _cashRun = new CashRun();
		_cashRun.setOptDate(optDate);
		_cashRun.setOrgId(orgId);
		List<CashRun> _list = cashDailyManager.cashDailyDetail(_cashRun);

		if (null != _list && _list.size() > 0) {
			CashRun cashRun1 = _list.get(0);
			model.addAttribute("cashRun1", cashRun1);
		}
		if (null != _list && _list.size() > 1) {
			CashRun cashRun2 = _list.get(1);
			model.addAttribute("cashRun2", cashRun2);
		}

		SysConfig sysConfig = SpringContextHolder.getBean("sysConfig");
		model.addAttribute("DEFAULT_RETAINED_AMT", sysConfig.getDefaultRetainedAmt());

		return "report/cashDetailReport";
	}
}
