package com.tjhx.entity.affair;

import com.tjhx.common.utils.DateUtils;

public class PunchClock_Show {
	/** 打卡时间-年 */
	private String clockTimeY;
	/** 打卡时间-月 */
	private String clockTimeM;
	/** 打卡时间-日 */
	private String clockTimeD;
	/** 打卡时间-开始 */
	private String startClockTime;
	/** 打卡时间-结束 */
	private String endClockTime;
	/** 员工编号 */
	private int empUuid;

	public PunchClock_Show() {

	}

	public PunchClock_Show(String clockTimeY, String clockTimeM, String clockTimeD, int empUuid) {
		this.clockTimeY = clockTimeY;
		this.clockTimeM = clockTimeM;
		this.clockTimeD = clockTimeD;
		this.empUuid = empUuid;
	}

	/**
	 * 取得打卡时间-年
	 * 
	 * @return clockTimeY 打卡时间-年
	 */
	public String getClockTimeY() {
		return clockTimeY;
	}

	/**
	 * 设置打卡时间-年
	 * 
	 * @param clockTimeY 打卡时间-年
	 */
	public void setClockTimeY(String clockTimeY) {
		this.clockTimeY = clockTimeY;
	}

	/**
	 * 取得打卡时间-月
	 * 
	 * @return clockTimeM 打卡时间-月
	 */
	public String getClockTimeM() {
		return clockTimeM;
	}

	/**
	 * 设置打卡时间-月
	 * 
	 * @param clockTimeM 打卡时间-月
	 */
	public void setClockTimeM(String clockTimeM) {
		this.clockTimeM = clockTimeM;
	}

	/**
	 * 取得打卡时间-日
	 * 
	 * @return clockTimeD 打卡时间-日
	 */
	public String getClockTimeD() {
		return clockTimeD;
	}

	/**
	 * 设置打卡时间-日
	 * 
	 * @param clockTimeD 打卡时间-日
	 */
	public void setClockTimeD(String clockTimeD) {
		this.clockTimeD = clockTimeD;
	}

	/**
	 * 取得打卡时间-开始
	 * 
	 * @return startClockTime 打卡时间-开始
	 */
	public String getStartClockTime() {
		return startClockTime;
	}

	/**
	 * 设置打卡时间-开始
	 * 
	 * @param startClockTime 打卡时间-开始
	 */
	public void setStartClockTime(String startClockTime) {
		this.startClockTime = startClockTime;
	}

	/**
	 * 取得打卡时间-结束
	 * 
	 * @return endClockTime 打卡时间-结束
	 */
	public String getEndClockTime() {
		return endClockTime;
	}

	/**
	 * 设置打卡时间-结束
	 * 
	 * @param endClockTime 打卡时间-结束
	 */
	public void setEndClockTime(String endClockTime) {
		this.endClockTime = endClockTime;
	}

	/**
	 * 取得员工编号
	 * 
	 * @return empUuid 员工编号
	 */
	public int getEmpUuid() {
		return empUuid;
	}

	/**
	 * 设置员工编号
	 * 
	 * @param empUuid 员工编号
	 */
	public void setEmpUuid(int empUuid) {
		this.empUuid = empUuid;
	}

	public void copy(PunchClock clock) {
		this.startClockTime = DateUtils.transDateFormat(clock.getStartClockTime(), "HH:mm:ss");
		this.endClockTime = DateUtils.transDateFormat(clock.getEndClockTime(), "HH:mm:ss");
	}
}
