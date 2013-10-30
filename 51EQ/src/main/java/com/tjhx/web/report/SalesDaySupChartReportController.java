package com.tjhx.web.report;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.ServletRequestBindingException;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springside.modules.mapper.JsonMapper;

import com.tjhx.common.utils.DateUtils;
import com.tjhx.entity.info.SalesDayTotalSup;
import com.tjhx.service.info.SalesDayTotalSupManager;
import com.tjhx.service.struct.OrganizationManager;
import com.tjhx.web.BaseController;

@Controller
@RequestMapping(value = "/salesDaySupChartReport")
public class SalesDaySupChartReportController extends BaseController {
	@Resource
	private OrganizationManager orgManager;
	@Resource
	private SalesDayTotalSupManager salesDayTotalSupManager;

	@RequestMapping(value = "bar_init")
	public String salesDayChartReport1_Action(Model model) {
		ReportUtils.initOrgList_All_NonRoot(orgManager, model);

		return "report/salesDaySupChartReport_bar";
	}

	@RequestMapping(value = "bar_search")
	public String salesDayChartReportSearch1_Action(Model model, HttpServletRequest request)
			throws ServletRequestBindingException {
		String optDateStart = ServletRequestUtils.getStringParameter(request, "optDateShow_start");
		String optDateEnd = ServletRequestUtils.getStringParameter(request, "optDateShow_end");
		String orgId = ServletRequestUtils.getStringParameter(request, "orgId");
		model.addAttribute("orgId", orgId);
		model.addAttribute("optDateShow_start", optDateStart);
		model.addAttribute("optDateShow_end", optDateEnd);

		ReportUtils.initOrgList_All_NonRoot(orgManager, model);

		SalesDayTotalSup param = new SalesDayTotalSup();
		param.setOrgId(orgId);
		param.setOptDateStart(DateUtils.transDateFormat(optDateStart, "yyyy-MM-dd", "yyyyMMdd"));
		param.setOptDateEnd(DateUtils.transDateFormat(optDateEnd, "yyyy-MM-dd", "yyyyMMdd"));

		// 取得合计实销金额（指定时间区间/机构）-按供应商
		List<SalesDayTotalSup> _sumSaleRamtList = salesDayTotalSupManager.getSumSaleRamtList(param);
		// 取得合计实销数量（指定时间区间/机构）-按供应商
		List<SalesDayTotalSup> _sumSaleRqtyList = salesDayTotalSupManager.getSumSaleRqtyList(param);

		JsonMapper mapper = new JsonMapper();
		model.addAttribute("sumSaleRamtList", mapper.toJson(_sumSaleRamtList));
		model.addAttribute("sumSaleRqtyList", mapper.toJson(_sumSaleRqtyList));

		model.addAttribute("showFlg", true);

		return "report/salesDaySupChartReport_bar";
	}
}
