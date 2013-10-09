package com.tjhx.schedule;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.tjhx.service.info.SalesDayTotalManager;

/**
 * 每日各店销售汇总Job
 */
public class OrgSalesDayTotalJob implements IJob {
	private static Logger logger = LoggerFactory.getLogger(OrgSalesDayTotalJob.class);
	@Autowired
	private SalesDayTotalManager salesDayTotalManager;

	@Override
	public void execute() throws Exception {
		logger.info("OrgSalesDayTotalJob Begin");
		// 取得百威系统各门店日销售信息
		salesDayTotalManager.getOrgSalesDayTotal();
		logger.info("OrgSalesDayTotalJob End");

	}
}
