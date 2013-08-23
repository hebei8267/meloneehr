package com.tjhx.schedule;

import java.text.ParseException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.tjhx.service.affair.PunchClockManager;

/**
 * (打卡机)
 * 
 * 被Spring的Quartz MethodInvokingJobDetailFactoryBean定时执行的普通Spring Bean.
 */
public class PunchClockJob {

	private static Logger logger = LoggerFactory.getLogger(PunchClockJob.class);

	@Autowired
	private PunchClockManager punchClockManager;

	/**
	 * 定时计算打卡记录
	 * 
	 * @throws ParseException
	 */
	public void execute() throws ParseException {
		logger.info("PunchClockJob Begin");
		punchClockManager.recalPunchClock();
		logger.info("PunchClockJob End");
	}
}