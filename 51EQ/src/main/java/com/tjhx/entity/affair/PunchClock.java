package com.tjhx.entity.affair;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import org.hibernate.annotations.NaturalId;

import com.tjhx.common.utils.DateUtils;
import com.tjhx.entity.IdEntity;

@Entity
@Table(name = "T_PUNCH_CLOCK")
// 默认的缓存策略.
// @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class PunchClock extends IdEntity {

	private static final long serialVersionUID = 8758497876481381832L;
	/** 用户编号 */
	private Integer zkid;
	/** 打卡时间 */
	private Date clockTime;
	/** 打卡时间-年 */
	private String clockTimeY;
	/** 打卡时间-月 */
	private String clockTimeM;
	/** 打卡时间-日 */
	private String clockTimeD;
	/** 打卡机编号 */
	private String sn;

	public PunchClock() {

	}

	public PunchClock(String clockTimeYMD) {
		clockTimeY = DateUtils.transDateFormat(clockTimeYMD, "yyyy-MM-dd", "yyyy");
		clockTimeM = DateUtils.transDateFormat(clockTimeYMD, "yyyy-MM-dd", "MM");
		clockTimeD = DateUtils.transDateFormat(clockTimeYMD, "yyyy-MM-dd", "dd");
	}

	/**
	 * 取得用户编号
	 * 
	 * @return zkid 用户编号
	 */
	@NaturalId
	public Integer getZkid() {
		return zkid;
	}

	/**
	 * 设置用户编号
	 * 
	 * @param zkid 用户编号
	 */
	public void setZkid(Integer zkid) {
		this.zkid = zkid;
	}

	/**
	 * 取得打卡时间
	 * 
	 * @return clockTime 打卡时间
	 */
	@NaturalId
	public Date getClockTime() {
		return clockTime;
	}

	/**
	 * 设置打卡时间
	 * 
	 * @param clockTime 打卡时间
	 */
	public void setClockTime(Date clockTime) {
		this.clockTime = clockTime;
	}

	/**
	 * 取得打卡时间-年
	 * 
	 * @return clockTimeY 打卡时间-年
	 */
	@Column(length = 4)
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
	@Column(length = 2)
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
	@Column(length = 2)
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
	 * 取得打卡机编号
	 * 
	 * @return sn 打卡机编号
	 */
	@Column(length = 16)
	public String getSn() {
		return sn;
	}

	/**
	 * 设置打卡机编号
	 * 
	 * @param sn 打卡机编号
	 */
	public void setSn(String sn) {
		this.sn = sn;
	}
}
