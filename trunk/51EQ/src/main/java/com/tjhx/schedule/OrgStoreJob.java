package com.tjhx.schedule;

import java.text.ParseException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.tjhx.service.info.StoreDetailManager;

/**
 * 各门店每日库存定时任务
 * 
 */
public class OrgStoreJob {
	private static Logger logger = LoggerFactory.getLogger(OrgStoreJob.class);
	@Autowired
	private StoreDetailManager storeDetailManager;

	/**
	 * 定时计算打卡记录
	 * 
	 * @throws ParseException
	 */
	public void execute() throws ParseException {
		logger.info("OrgStoreJob Begin");
		// 取得门店库存信息
		storeDetailManager.calOrgStoreDetail();
		// 计算门店库存合计信息
		storeDetailManager.calOrgStoreDayTotal();
		logger.info("OrgStoreJob End");
	}
}
