package com.tjhx.web.report;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.ServletRequestBindingException;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springside.modules.mapper.JsonMapper;
import org.springside.modules.utils.SpringContextHolder;

import com.tjhx.common.utils.DateUtils;
import com.tjhx.entity.info.SalesDayTotalItem;
import com.tjhx.entity.struct.Organization;
import com.tjhx.globals.Constants;
import com.tjhx.globals.SysConfig;
import com.tjhx.service.info.SalesDayTotalManager;
import com.tjhx.service.struct.OrganizationManager;
import com.tjhx.web.BaseController;

@Controller
@RequestMapping(value = "/salesDayChartReport")
public class SalesDayChartReportController extends BaseController {
	@Resource
	private OrganizationManager orgManager;
	@Resource
	private SalesDayTotalManager salesDayTotalManager;

	@RequestMapping(value = "bar_init")
	public String salesDayChartReport1_Action(Model model) {
		ReportUtils.initOrgList_All_NonRoot(orgManager, model);

		return "report/salesDayChartReport_bar";
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

		SalesDayTotalItem param = new SalesDayTotalItem();
		param.setOrgId(orgId);
		param.setOptDateStart(DateUtils.transDateFormat(optDateStart, "yyyy-MM-dd", "yyyyMMdd"));
		param.setOptDateEnd(DateUtils.transDateFormat(optDateEnd, "yyyy-MM-dd", "yyyyMMdd"));

		// 取得合计实销金额（指定时间区间/机构）
		List<SalesDayTotalItem> _sumSaleRamtList = salesDayTotalManager.getSumSaleRamtList(param);
		// 取得合计实销数量（指定时间区间/机构）
		List<SalesDayTotalItem> _sumSaleRqtyList = salesDayTotalManager.getSumSaleRqtyList(param);
		// 取得合计信息（金额/数量/均价）（指定时间区间/机构）
		List<SalesDayTotalItem> _sumSaleList = salesDayTotalManager.getSumSaleInfoList(param);
		
		JsonMapper mapper = new JsonMapper();
		model.addAttribute("sumSaleRamtList", mapper.toJson(_sumSaleRamtList));
		model.addAttribute("sumSaleRqtyList", mapper.toJson(_sumSaleRqtyList));
		model.addAttribute("sumSaleList", _sumSaleList);
		
		model.addAttribute("showFlg", true);

		return "report/salesDayChartReport_bar";
	}

	// -------------------------------------------------------------------
	@RequestMapping(value = "pie_init")
	public String salesDayChartReport2_Action(Model model) {
		return "report/salesDayChartReport_pie";
	}

	/**
	 * 取得门店机构（不包含总部机构）
	 * 
	 * @return
	 */
	public List<Organization> getSubOrganization() {
		List<Organization> _orgList = orgManager.getAllOrganization();

		_orgList.remove(new Organization(Constants.ROOT_ORG_ID));

		return _orgList;
	}

	@RequestMapping(value = "pie_search")
	public String salesDayChartReportSearch2_Action(Model model, HttpServletRequest request)
			throws ServletRequestBindingException {
		String optDateStart = ServletRequestUtils.getStringParameter(request, "optDateShow_start");
		String optDateEnd = ServletRequestUtils.getStringParameter(request, "optDateShow_end");
		model.addAttribute("optDateShow_start", optDateStart);
		model.addAttribute("optDateShow_end", optDateEnd);

		String _startDate = DateUtils.transDateFormat(optDateStart, "yyyy-MM-dd", "yyyyMMdd");
		String _endDate = DateUtils.transDateFormat(optDateEnd, "yyyy-MM-dd", "yyyyMMdd");

		List<String> _jsonStrList = new ArrayList<String>();
		List<String> _orgNameList = new ArrayList<String>();
		// 合计
		_jsonStrList.add(getSumSaleRamtJsonStr(_startDate, _endDate));
		_orgNameList.add("合计");

		List<Organization> _orgList = getSubOrganization();
		for (Organization org : _orgList) {
			// 各门店
			_jsonStrList.add(getSumSaleRamtJsonStr(_startDate, _endDate, org.getId()));
			_orgNameList.add(org.getName());
		}

		model.addAttribute("saleRamtJsonList", _jsonStrList);
		model.addAttribute("orgNameList", _orgNameList);

		return "report/salesDayChartReport_pie";
	}

	private String getSumSaleRamtJsonStr(String startDate, String endDate) {
		SysConfig sysConfig = SpringContextHolder.getBean("sysConfig");
		int num = sysConfig.getSalesDayTotalShowNum();

		SalesDayTotalItem param = new SalesDayTotalItem();
		param.setOptDateStart(startDate);
		param.setOptDateEnd(endDate);
		// 取得合计实销金额（指定时间区间/机构）
		List<SalesDayTotalItem> _sumSaleRamtList = salesDayTotalManager.getSumSaleRamtList(param);
		if (null != _sumSaleRamtList && _sumSaleRamtList.size() > num) {
			_sumSaleRamtList = _sumSaleRamtList.subList(0, num);
		}
		JsonMapper mapper = new JsonMapper();
		return mapper.toJson(_sumSaleRamtList);
	}

	private String getSumSaleRamtJsonStr(String startDate, String endDate, String orgId) {
		SysConfig sysConfig = SpringContextHolder.getBean("sysConfig");
		int num = sysConfig.getSalesDayTotalShowNum();

		SalesDayTotalItem param = new SalesDayTotalItem();
		param.setOptDateStart(startDate);
		param.setOptDateEnd(endDate);
		param.setOrgId(orgId);
		// 取得合计实销金额（指定时间区间/机构）
		List<SalesDayTotalItem> _sumSaleRamtList = salesDayTotalManager.getSumSaleRamtList(param);
		if (null != _sumSaleRamtList && _sumSaleRamtList.size() > num) {
			_sumSaleRamtList = _sumSaleRamtList.subList(0, num);
		}
		JsonMapper mapper = new JsonMapper();
		return mapper.toJson(_sumSaleRamtList);
	}
}
