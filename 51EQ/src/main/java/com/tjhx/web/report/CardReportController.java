package com.tjhx.web.report;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.ServletRequestBindingException;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.bind.annotation.RequestMapping;

import com.tjhx.common.utils.DateUtils;
import com.tjhx.entity.accounts.CashDaily;
import com.tjhx.service.accounts.CashDailyManager;
import com.tjhx.service.struct.OrganizationManager;
import com.tjhx.web.BaseController;

@Controller
@RequestMapping(value = "/cardReport")
public class CardReportController extends BaseController {
	@Resource
	private OrganizationManager orgManager;
	@Resource
	private CashDailyManager cashDailyManager;

	@RequestMapping(value = { "list", "" })
	public String cashReportList_Action(Model model) throws ServletRequestBindingException {

		ReportUtils.initOrgList(orgManager, model);

		return "report/cardListReport";
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

		return "report/cardListReport";
	}
}
