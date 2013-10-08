package com.tjhx.web.report;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.ServletRequestBindingException;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springside.modules.mapper.JsonMapper;

import com.tjhx.entity.info.StoreDayTotal;
import com.tjhx.entity.info.StoreDayTotal_Show;
import com.tjhx.service.affair.StoreChartReportManager;
import com.tjhx.web.BaseController;

@Controller
@RequestMapping(value = "/storeChartReport")
public class StoreChartReportController extends BaseController {
	@Resource
	private StoreChartReportManager storeChartReportManager;

	@RequestMapping(value = { "chart", "" })
	public String cashChartReportList_Action(Model model) throws ServletRequestBindingException {
		String maxOptDate = storeChartReportManager.getMaxOptDate();

		List<StoreDayTotal> _dbStoreDayTotal = storeChartReportManager.getStoreDayTotalList(maxOptDate);

		model.addAttribute("maxOptDate", maxOptDate);
		model.addAttribute("data_set", formatStoreDayTotalInfo(_dbStoreDayTotal));

		return "report/storeChartReport";
	}

	private String formatStoreDayTotalInfo(List<StoreDayTotal> _dbStoreDayTotal) {
		List<StoreDayTotal_Show> _list = new ArrayList<StoreDayTotal_Show>();

		StoreDayTotal tmpStoreDayTotal = null;
		for (StoreDayTotal storeDayTotal : _dbStoreDayTotal) {

			if (null != tmpStoreDayTotal && tmpStoreDayTotal.myEquals(storeDayTotal)) {

				StoreDayTotal_Show _showObj = _list.get(_list.size() - 1);

				_showObj.copyStoreDayTotalInfo(storeDayTotal);

			} else {
				StoreDayTotal_Show _showObj = new StoreDayTotal_Show();
				_showObj.copyStoreDayTotalInfo(storeDayTotal);

				_list.add(_showObj);
			}

			tmpStoreDayTotal = storeDayTotal;
		}

		JsonMapper mapper = new JsonMapper();
		return mapper.toJson(_list);
	}
}
