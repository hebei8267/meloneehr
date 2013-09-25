package com.tjhx.entity.affair;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.tjhx.entity.IdEntity;

/**
 * 门店备用金
 */
@Entity
@Table(name = "T_PETTY_CASH")
public class PettyCash extends IdEntity {

	private static final long serialVersionUID = -7793146587669072960L;
	/** 业务编号 */
	private String optUid;
	/** 业务日期 */
	private String optDate;
	/** 业务日期-显示 */
	private String optDateShow;
	/** 业务日期-年 */
	private String optDateY;
	/** 业务日期-月 */
	private String optDateM;
	/** 业务日期-对应的星期 */
	private String week;
	/** 操作金额-支出为负数，拨入为正数 */
	private BigDecimal optAmt = new BigDecimal("0");
	/** 操作金额-全部为正数 */
	private BigDecimal optAmtShow = new BigDecimal("0");
	/** 备用金余额（可能为负） */
	private BigDecimal balanceAmt = new BigDecimal("0");
	/** 操作类型 0-支出 1-拨入 */
	private int optType;
	/** 拨入来源 1-正常拨入 2-非常拨入 4-会计调帐 */
	private String incomeSource;
	/** 支出事项 */
	private String expReason;
	/** 备注 */
	private String descTxt;
	/** 机构编号 */
	private String orgId;
	// ############################################################################################
	/** 用户关联机构名称 */
	private String orgName;
	/** 门店备用金可编辑日期 */
	private String editDate;
	/** 可编辑标记 */
	private boolean editFlg;

	/**
	 * 取得业务编号
	 * 
	 * @return optUid 业务编号
	 */
	@Column(length = 16)
	public String getOptUid() {
		return optUid;
	}

	/**
	 * 设置业务编号
	 * 
	 * @param optUid 业务编号
	 */
	public void setOptUid(String optUid) {
		this.optUid = optUid;
	}

	/**
	 * 取得业务日期
	 * 
	 * @return optDate 业务日期
	 */
	@Column(length = 8)
	public String getOptDate() {
		return optDate;
	}

	/**
	 * 设置业务日期
	 * 
	 * @param optDate 业务日期
	 */
	public void setOptDate(String optDate) {
		this.optDate = optDate;
	}

	/**
	 * 取得业务日期-显示
	 * 
	 * @return optDateShow 业务日期-显示
	 */
	@Column(length = 10)
	public String getOptDateShow() {
		return optDateShow;
	}

	/**
	 * 设置业务日期-显示
	 * 
	 * @param optDateShow 业务日期-显示
	 */
	public void setOptDateShow(String optDateShow) {
		this.optDateShow = optDateShow;
	}

	/**
	 * 取得业务日期-年
	 * 
	 * @return optDateY 业务日期-年
	 */
	@Column(name = "OPT_DATE_Y", length = 4)
	public String getOptDateY() {
		return optDateY;
	}

	/**
	 * 设置业务日期-年
	 * 
	 * @param optDateY 业务日期-年
	 */
	public void setOptDateY(String optDateY) {
		this.optDateY = optDateY;
	}

	/**
	 * 取得业务日期-月
	 * 
	 * @return optDateM 业务日期-月
	 */
	@Column(name = "OPT_DATE_M", length = 2)
	public String getOptDateM() {
		return optDateM;
	}

	/**
	 * 设置业务日期-月
	 * 
	 * @param optDateM 业务日期-月
	 */
	public void setOptDateM(String optDateM) {
		this.optDateM = optDateM;
	}

	/**
	 * 取得业务日期-对应的星期
	 * 
	 * @return week 业务日期-对应的星期
	 */
	@Column(length = 1)
	public String getWeek() {
		return week;
	}

	/**
	 * 设置业务日期-对应的星期
	 * 
	 * @param week 业务日期-对应的星期
	 */
	public void setWeek(String week) {
		this.week = week;
	}

	/**
	 * 取得操作金额-支出为负数，拨入为正数
	 * 
	 * @return optAmt 操作金额-支出为负数，拨入为正数
	 */
	public BigDecimal getOptAmt() {
		return optAmt;
	}

	/**
	 * 设置操作金额-支出为负数，拨入为正数
	 * 
	 * @param optAmt 操作金额-支出为负数，拨入为正数
	 */
	public void setOptAmt(BigDecimal optAmt) {
		this.optAmt = optAmt;
	}

	/**
	 * 取得操作金额-全部为正数
	 * 
	 * @return optAmtShow 操作金额-全部为正数
	 */
	public BigDecimal getOptAmtShow() {
		return optAmtShow;
	}

	/**
	 * 设置操作金额-全部为正数
	 * 
	 * @param optAmtShow 操作金额-全部为正数
	 */
	public void setOptAmtShow(BigDecimal optAmtShow) {
		this.optAmtShow = optAmtShow;
	}

	/**
	 * 取得备用金余额（可能为负）
	 * 
	 * @return balanceAmt 备用金余额（可能为负）
	 */
	public BigDecimal getBalanceAmt() {
		return balanceAmt;
	}

	/**
	 * 设置备用金余额（可能为负）
	 * 
	 * @param balanceAmt 备用金余额（可能为负）
	 */
	public void setBalanceAmt(BigDecimal balanceAmt) {
		this.balanceAmt = balanceAmt;
	}

	/**
	 * 取得操作类型0-支出1-拨入
	 * 
	 * @return optType 操作类型0-支出1-拨入
	 */
	public int getOptType() {
		return optType;
	}

	/**
	 * 设置操作类型0-支出1-拨入
	 * 
	 * @param optType 操作类型0-支出1-拨入
	 */
	public void setOptType(int optType) {
		this.optType = optType;
	}

	/**
	 * 取得拨入来源1-正常拨入2-非常拨入4-会计调帐
	 * 
	 * @return incomeSource 拨入来源1-正常拨入2-非常拨入4-会计调帐
	 */
	@Column(length = 1)
	public String getIncomeSource() {
		return incomeSource;
	}

	/**
	 * 设置拨入来源1-正常拨入2-非常拨入4-会计调帐
	 * 
	 * @param incomeSource 拨入来源1-正常拨入2-非常拨入4-会计调帐
	 */
	public void setIncomeSource(String incomeSource) {
		this.incomeSource = incomeSource;
	}

	/**
	 * 取得支出事项
	 * 
	 * @return expReason 支出事项
	 */
	public String getExpReason() {
		return expReason;
	}

	/**
	 * 设置支出事项
	 * 
	 * @param expReason 支出事项
	 */
	public void setExpReason(String expReason) {
		this.expReason = expReason;
	}

	/**
	 * 取得备注
	 * 
	 * @return descTxt 备注
	 */
	public String getDescTxt() {
		return descTxt;
	}

	/**
	 * 设置备注
	 * 
	 * @param descTxt 备注
	 */
	public void setDescTxt(String descTxt) {
		this.descTxt = descTxt;
	}

	/**
	 * 取得机构编号
	 * 
	 * @return orgId 机构编号
	 */
	@Column(length = 32)
	public String getOrgId() {
		return orgId;
	}

	/**
	 * 设置机构编号
	 * 
	 * @param orgId 机构编号
	 */
	public void setOrgId(String orgId) {
		this.orgId = orgId;
	}

	// ############################################################################################
	/**
	 * 取得用户关联机构名称
	 * 
	 * @return orgName 用户关联机构名称
	 */
	@Transient
	public String getOrgName() {
		if (null == orgName && null != orgId) {
			orgName = orgId.substring(3, 6);
		}
		return orgName;
	}

	/**
	 * 设置用户关联机构名称
	 * 
	 * @param orgName 用户关联机构名称
	 */
	public void setOrgName(String orgName) {
		this.orgName = orgName;
	}

	/**
	 * 取得门店备用金可编辑日期
	 * 
	 * @return editDate 门店备用金可编辑日期
	 */
	@Transient
	public String getEditDate() {
		return editDate;
	}

	/**
	 * 设置门店备用金可编辑日期
	 * 
	 * @param editDate 门店备用金可编辑日期
	 */
	public void setEditDate(String editDate) {
		this.editDate = editDate;
	}

	/**
	 * 取得可编辑标记
	 * 
	 * @return editFlg 可编辑标记
	 */
	@Transient
	public boolean isEditFlg() {
		return editFlg;
	}

	/**
	 * 设置可编辑标记
	 * 
	 * @param editFlg 可编辑标记
	 */
	public void setEditFlg(boolean editFlg) {
		this.editFlg = editFlg;
	}
}
