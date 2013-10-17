package com.tjhx.schedule;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.tjhx.service.info.SalesDayTotalManager;
import com.tjhx.service.info.SalesMonthTotalManager;

/**
 * 每日/每月各店销售汇总Job
 */
public class OrgSalesTotalJob implements IJob {
	private static Logger logger = LoggerFactory.getLogger(OrgSalesTotalJob.class);
	@Autowired
	private SalesDayTotalManager salesDayTotalManager;
	@Autowired
	private SalesMonthTotalManager salesMonthTotalManager;

	@Override
	public void execute() throws Exception {
		logger.info("取得百威系统各门店日销售信息 Begin");
		// 取得百威系统各门店日销售信息
		salesDayTotalManager.getOrgSalesDayTotal();
		logger.info("取得百威系统各门店日销售信息 End");

		// #################################################
		logger.info("门店月销售计算 Begin");
		// 门店月销售计算
		salesMonthTotalManager.calOrgSalesMonthTotal();
		logger.info("门店月销售计算 End");
	}
}
