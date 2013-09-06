package com.tjhx.entity.affair;

public class WorkSchedule_List_Show {

	/** 排班日期 */
	private String scheduleDate;
	/** 排班日期-对应的星期 */
	private String week;

	/**
	 * 取得排班日期
	 * 
	 * @return scheduleDate 排班日期
	 */
	public String getScheduleDate() {
		return scheduleDate;
	}

	/**
	 * 设置排班日期
	 * 
	 * @param scheduleDate 排班日期
	 */
	public void setScheduleDate(String scheduleDate) {
		this.scheduleDate = scheduleDate;
	}

	/**
	 * 取得排班日期-对应的星期
	 * 
	 * @return week 排班日期-对应的星期
	 */
	public String getWeek() {
		return week;
	}

	/**
	 * 设置排班日期-对应的星期
	 * 
	 * @param week 排班日期-对应的星期
	 */
	public void setWeek(String week) {
		this.week = week;
	}

}
