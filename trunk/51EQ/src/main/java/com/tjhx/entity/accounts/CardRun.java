package com.tjhx.entity.accounts;

import java.math.BigDecimal;
import java.text.ParseException;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.NaturalId;

import com.tjhx.common.utils.DateUtils;
import com.tjhx.entity.IdEntity;
import com.tjhx.globals.Constants;

/**
 * 刷卡流水
 */
@Entity
@Table(name = "T_CARD_RUN")
// 默认的缓存策略.
// @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class CardRun extends IdEntity {

	private static final long serialVersionUID = 2921539352332800236L;

	/** 机构编号 */
	private String orgId;
	/** 刷卡汇总日期 */
	private String optDate;
	/** 刷卡汇总日期-显示 */
	private String optDateShow;
	/** 刷卡汇总日期-年 */
	private String optDateY;
	/** 刷卡汇总日期-月 */
	private String optDateM;
	/** 单据统计 */
	private BigDecimal recordStatisAmt = new BigDecimal("0");
	/** 电脑统计（百威） */
	private BigDecimal bwStatisAmt = new BigDecimal("0");
	/** 刷卡笔数 */
	private Integer optNum = 0;
	/** 盈亏金额 */
	private BigDecimal profitAmt = new BigDecimal("0");
	/** 凭证号 */
	private String certNo;
	/** 备注（盈亏原因） */
	private String descTxt;
	// ############################################################################################
	/** 可编辑标记 */
	private Boolean editFlg = false;

	/**
	 * 取得机构编号
	 * 
	 * @return orgId 机构编号
	 */
	@NaturalId
	@Column(name = "ORG_ID", length = 32)
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

	/**
	 * 取得刷卡汇总日期
	 * 
	 * @return optDate 刷卡汇总日期
	 */
	@NaturalId
	@Column(name = "OPT_DATE", length = 8)
	public String getOptDate() {
		return optDate;
	}

	/**
	 * 设置刷卡汇总日期
	 * 
	 * @param optDate 刷卡汇总日期
	 */
	public void setOptDate(String optDate) {
		this.optDate = optDate;
	}

	/**
	 * 取得刷卡汇总日期-显示
	 * 
	 * @return optDateShow 刷卡汇总日期-显示
	 */
	@Column(name = "OPT_DATE_SHOW", length = 10)
	public String getOptDateShow() {
		return optDateShow;
	}

	/**
	 * 设置刷卡汇总日期-显示
	 * 
	 * @param optDateShow 刷卡汇总日期-显示
	 */
	public void setOptDateShow(String optDateShow) {
		this.optDateShow = optDateShow;
	}

	/**
	 * 取得刷卡汇总日期-年
	 * 
	 * @return optDateY 刷卡汇总日期-年
	 */
	@Column(name = "OPT_DATE_Y", length = 4)
	public String getOptDateY() {
		return optDateY;
	}

	/**
	 * 设置刷卡汇总日期-年
	 * 
	 * @param optDateY 刷卡汇总日期-年
	 */
	public void setOptDateY(String optDateY) {
		this.optDateY = optDateY;
	}

	/**
	 * 取得刷卡汇总日期-月
	 * 
	 * @return optDateM 刷卡汇总日期-月
	 */
	@Column(name = "OPT_DATE_M", length = 2)
	public String getOptDateM() {
		return optDateM;
	}

	/**
	 * 设置刷卡汇总日期-月
	 * 
	 * @param optDateM 刷卡汇总日期-月
	 */
	public void setOptDateM(String optDateM) {
		this.optDateM = optDateM;
	}

	/**
	 * 取得单据统计
	 * 
	 * @return recordStatisAmt 单据统计
	 */
	@Column(name = "RECORD_STATIS_AMT")
	public BigDecimal getRecordStatisAmt() {
		return recordStatisAmt;
	}

	/**
	 * 设置单据统计
	 * 
	 * @param recordStatisAmt 单据统计
	 */
	public void setRecordStatisAmt(BigDecimal recordStatisAmt) {
		this.recordStatisAmt = recordStatisAmt;
	}

	/**
	 * 取得电脑统计（百威）
	 * 
	 * @return bwStatisAmt 电脑统计（百威）
	 */
	@Column(name = "BW_STATIS_AMT")
	public BigDecimal getBwStatisAmt() {
		return bwStatisAmt;
	}

	/**
	 * 设置电脑统计（百威）
	 * 
	 * @param bwStatisAmt 电脑统计（百威）
	 */
	public void setBwStatisAmt(BigDecimal bwStatisAmt) {
		this.bwStatisAmt = bwStatisAmt;
	}

	/**
	 * 取得刷卡笔数
	 * 
	 * @return optNum 刷卡笔数
	 */
	@Column(name = "OPT_NUM")
	public Integer getOptNum() {
		return optNum;
	}

	/**
	 * 设置刷卡笔数
	 * 
	 * @param optNum 刷卡笔数
	 */
	public void setOptNum(Integer optNum) {
		this.optNum = optNum;
	}

	/**
	 * 取得盈亏金额
	 * 
	 * @return profitAmt 盈亏金额
	 */
	@Column(name = "PROFIT_AMT")
	public BigDecimal getProfitAmt() {
		return profitAmt;
	}

	/**
	 * 设置盈亏金额
	 * 
	 * @param profitAmt 盈亏金额
	 */
	public void setProfitAmt(BigDecimal profitAmt) {
		this.profitAmt = profitAmt;
	}

	/**
	 * 取得凭证号
	 * 
	 * @return certNo 凭证号
	 */
	@Column(name = "CERT_NO", length = 32)
	public String getCertNo() {
		return certNo;
	}

	/**
	 * 设置凭证号
	 * 
	 * @param certNo 凭证号
	 */
	public void setCertNo(String certNo) {
		this.certNo = certNo;
	}

	/**
	 * 取得备注（盈亏原因）
	 * 
	 * @return descTxt 备注（盈亏原因）
	 */
	@Column(name = "DESC_TXT")
	public String getDescTxt() {
		return descTxt;
	}

	/**
	 * 设置备注（盈亏原因）
	 * 
	 * @param descTxt 备注（盈亏原因）
	 */
	public void setDescTxt(String descTxt) {
		this.descTxt = descTxt;
	}

	// ############################################################################################

	/**
	 * 取得可编辑标记
	 * 
	 * @return editFlg 可编辑标记
	 */
	@Transient
	public Boolean getEditFlg() {
		return editFlg;
	}

	/**
	 * 设置可编辑标记
	 * 
	 * @param editFlg 可编辑标记
	 */
	public void setEditFlg(Boolean editFlg) {
		this.editFlg = editFlg;
	}

	public void autoSetEditFlg() throws ParseException {
		String nowDate = DateUtils.getCurrentDateShortStr();
		if (null == optDate) {
			return;
		}
		Long _day = DateUtils.getDateSpanDay(optDate, nowDate);
		_day = Math.abs(_day);
		if (_day <= Constants.EDITABLE_DAY) {
			editFlg = true;
		}
	}
}
