package com.tjhx.entity.affair;

import javax.persistence.Entity;
import javax.persistence.Table;

import com.tjhx.entity.IdEntity;

/**
 * 交接班/总部命令下发 记录
 */
@Entity
@Table(name = "T_DUTY_RECORD")
public class DutyRecord extends IdEntity {

	/** 日期-显示 */
	private String optDateShow;
	/** 日期-年 */
	private String optDateY;
	/** 日期-月 */
	private String optDateM;
	/** 星期 */
	private String week;

	/**
	 * 取得日期-显示
	 * 
	 * @return optDateShow 日期-显示
	 */
	public String getOptDateShow() {
		return optDateShow;
	}

	/**
	 * 设置日期-显示
	 * 
	 * @param optDateShow 日期-显示
	 */
	public void setOptDateShow(String optDateShow) {
		this.optDateShow = optDateShow;
	}

	/**
	 * 取得日期-年
	 * 
	 * @return optDateY 日期-年
	 */
	public String getOptDateY() {
		return optDateY;
	}

	/**
	 * 设置日期-年
	 * 
	 * @param optDateY 日期-年
	 */
	public void setOptDateY(String optDateY) {
		this.optDateY = optDateY;
	}

	/**
	 * 取得日期-月
	 * 
	 * @return optDateM 日期-月
	 */
	public String getOptDateM() {
		return optDateM;
	}

	/**
	 * 设置日期-月
	 * 
	 * @param optDateM 日期-月
	 */
	public void setOptDateM(String optDateM) {
		this.optDateM = optDateM;
	}

	/**
	 * 取得星期
	 * 
	 * @return week 星期
	 */
	public String getWeek() {
		return week;
	}

	/**
	 * 设置星期
	 * 
	 * @param week 星期
	 */
	public void setWeek(String week) {
		this.week = week;
	}

}
