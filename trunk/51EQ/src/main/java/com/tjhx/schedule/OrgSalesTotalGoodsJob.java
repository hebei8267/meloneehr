package com.tjhx.schedule;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.tjhx.service.info.SalesDayTotalGoodsManager;

/**
 * 每日/每月各店销售汇总Job(按商品)
 */
public class OrgSalesTotalGoodsJob implements IJob {
	private static Logger logger = LoggerFactory.getLogger(OrgSalesTotalGoodsJob.class);
	@Autowired
	private SalesDayTotalGoodsManager salesDayTotalGoodsManager;

	@Override
	public void execute() throws Exception {
		logger.info("取得百威系统各门店日销售信息(按商品) Begin");
		// 取得百威系统各门店日销售信息
		salesDayTotalGoodsManager.getOrgSalesDayTotal();
		logger.info("取得百威系统各门店日销售信息(按商品) End");

	}

}
