package com.tjhx.schedule;

import java.text.ParseException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.tjhx.service.accounts.CashDailyManager;

/**
 * (百威系统销售额)
 * 
 * 被Spring的Quartz MethodInvokingJobDetailFactoryBean定时执行的普通Spring Bean.
 */
public class BwSaleAmtJob {
	private static Logger logger = LoggerFactory.getLogger(BwSaleAmtJob.class);
	@Autowired
	private CashDailyManager cashDailyManager;

	public void execute() throws ParseException {
		logger.info("BwSaleAmtJob Begin");
		cashDailyManager.synBwSaleAmt();
		logger.info("BwSaleAmtJob End");
	}

}
