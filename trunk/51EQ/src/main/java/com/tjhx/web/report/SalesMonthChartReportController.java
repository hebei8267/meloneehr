package com.tjhx.web.report;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.tjhx.web.BaseController;

@Controller
@RequestMapping(value = "/salesMonthChartReport")
public class SalesMonthChartReportController extends BaseController {

	@RequestMapping(value = "bar_init")
	public String salesDayChartReport1_Action(Model model) {

		return "report/salesMonthChartReport_bar";
	}

}
