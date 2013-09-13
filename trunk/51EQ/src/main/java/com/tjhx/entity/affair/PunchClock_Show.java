package com.tjhx.entity.affair;

import java.text.ParseException;

import org.apache.commons.lang3.StringUtils;

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
	/** 上班时间 HH:mm */
	private String startScheduleDate;
	/** 下班时间 HH:mm */
	private String endScheduleDate;
	/** 员工编号 */
	private int empUuid;
	/** 考勤状态 99-非预期加班 0-正常 1-迟到 2-早退 */
	private int punchNormalState;

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

	/**
	 * 取得上班时间HH:mm
	 * 
	 * @return startScheduleDate 上班时间HH:mm
	 */
	public String getStartScheduleDate() {
		return startScheduleDate;
	}

	/**
	 * 设置上班时间HH:mm
	 * 
	 * @param startScheduleDate 上班时间HH:mm
	 */
	public void setStartScheduleDate(String startScheduleDate) {
		this.startScheduleDate = startScheduleDate;
	}

	/**
	 * 取得下班时间HH:mm
	 * 
	 * @return endScheduleDate 下班时间HH:mm
	 */
	public String getEndScheduleDate() {
		return endScheduleDate;
	}

	/**
	 * 设置下班时间HH:mm
	 * 
	 * @param endScheduleDate 下班时间HH:mm
	 */
	public void setEndScheduleDate(String endScheduleDate) {
		this.endScheduleDate = endScheduleDate;
	}

	/**
	 * 取得考勤状态99-非预期加班 0-正常 1-迟到 2-早退
	 * 
	 * @return punchNormalState 考勤状态99-非预期加班0-正常1-迟到2-早退
	 */
	public int getPunchNormalState() {
		return punchNormalState;
	}

	/**
	 * 设置考勤状态99-非预期加班 0-正常 1-迟到 2-早退
	 * 
	 * @param punchNormalState 考勤状态99-非预期加班 0-正常 1-迟到 2-早退
	 */
	public void setPunchNormalState(int punchNormalState) {
		this.punchNormalState = punchNormalState;
	}

	public void copy(PunchClock clock) {
		this.startClockTime = DateUtils.transDateFormat(clock.getStartClockTime(), "HH:mm:ss");
		this.endClockTime = DateUtils.transDateFormat(clock.getEndClockTime(), "HH:mm:ss");
	}

	public void copy(WorkSchedule workSchedule) {
		this.startScheduleDate = workSchedule.getStartDate();
		this.endScheduleDate = workSchedule.getEndDate();
	}

	public void timeValidate() throws ParseException {
		// 99-非预期加班 0-正常 1-迟到 2-早退

		String curDate = DateUtils.getCurrentDateShortStr();
		String clockTime = clockTimeY + clockTimeM + clockTimeD;
		if (Integer.valueOf(curDate) < Integer.valueOf(clockTime)) {// 未来日不做计算
			punchNormalState = 0;// 正常
		} else if (StringUtils.isBlank(startScheduleDate) || StringUtils.isBlank(endScheduleDate)) {
			punchNormalState = 0;// 正常

			if (StringUtils.isNotBlank(startClockTime) || StringUtils.isNotBlank(endClockTime)) {
				// 非预期加班
				punchNormalState = 99;
			}
		} else {
			// 是否迟到
			if (StringUtils.isBlank(startClockTime)) {
				punchNormalState = 1;// 迟到
			} else {
				long timeBetween1 = DateUtils.timeBetween(startClockTime, "HH:mm:ss", startScheduleDate, "HH:mm");
				if (timeBetween1 < 0) {
					punchNormalState = 1;// 迟到
				}
			}

			// 是否早退
			if (StringUtils.isBlank(endClockTime)) {
				punchNormalState = 2;// 早退
			} else {
				long timeBetween2 = DateUtils.timeBetween(endScheduleDate, "HH:mm", endClockTime, "HH:mm:ss");
				if (timeBetween2 < 0) {
					punchNormalState = 2;// 早退
				}
			}
		}
	}
}
