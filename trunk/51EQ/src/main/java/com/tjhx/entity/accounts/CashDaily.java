package com.tjhx.entity.accounts;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import org.hibernate.annotations.NaturalId;

import com.tjhx.entity.IdEntity;

/**
 * 现金流水日结
 */
@Entity
@Table(name = "T_CASH_DAILY")
// 默认的缓存策略.
// @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class CashDaily extends IdEntity {

	private static final long serialVersionUID = 6877509722983193093L;
	/** 机构编号 */
	private String orgId;
	/** 日期 */
	private String optDate;
	/** 日期-显示 */
	private String optDateShow;
	/** 日期-年 */
	private String optDateY;
	/** 日期-月 */
	private String optDateM;
	/** 昨日余额 */
	private BigDecimal initAmt = new BigDecimal("0");
	/** 当日销售（合计） */
	private BigDecimal saleAmt = new BigDecimal("0");
	/** 实际现金-当日（合计） */
	private BigDecimal cashAmt = new BigDecimal("0");
	/** 刷卡金额-单据统计-当日（合计） */
	private BigDecimal cardAmt = new BigDecimal("0");
	/** 刷卡金额-电脑统计-当日（合计） */
	private BigDecimal cardAmtBw = new BigDecimal("0");
	/** 刷卡笔数-当日（合计） */
	private Integer cardNum = 0;
	/** 存款金额-当日（合计） */
	private BigDecimal depositAmt = new BigDecimal("0");
	/** 留存金额-当日 */
	private BigDecimal retainedAmt = new BigDecimal("0");

	/**
	 * 取得机构编号
	 * 
	 * @return orgId 机构编号
	 */
	@NaturalId
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

	/**
	 * 取得日期
	 * 
	 * @return optDate 日期
	 */
	@NaturalId
	@Column(length = 8)
	public String getOptDate() {
		return optDate;
	}

	/**
	 * 设置日期
	 * 
	 * @param optDate 日期
	 */
	public void setOptDate(String optDate) {
		this.optDate = optDate;
	}

	/**
	 * 取得日期-显示
	 * 
	 * @return optDateShow 日期-显示
	 */
	@Column(length = 10)
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
	@Column(name = "OPT_DATE_Y", length = 4)
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
	@Column(name = "OPT_DATE_M", length = 2)
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
	 * 取得昨日余额
	 * 
	 * @return initAmt 昨日余额
	 */
	public BigDecimal getInitAmt() {
		return initAmt;
	}

	/**
	 * 设置昨日余额
	 * 
	 * @param initAmt 昨日余额
	 */
	public void setInitAmt(BigDecimal initAmt) {
		this.initAmt = initAmt;
	}

	/**
	 * 取得当日销售（合计）
	 * 
	 * @return saleAmt 当日销售（合计）
	 */
	public BigDecimal getSaleAmt() {
		return saleAmt;
	}

	/**
	 * 设置当日销售（合计）
	 * 
	 * @param saleAmt 当日销售（合计）
	 */
	public void setSaleAmt(BigDecimal saleAmt) {
		this.saleAmt = saleAmt;
	}

	/**
	 * 取得实际现金-当日（合计）
	 * 
	 * @return cashAmt 实际现金-当日（合计）
	 */
	public BigDecimal getCashAmt() {
		return cashAmt;
	}

	/**
	 * 设置实际现金-当日（合计）
	 * 
	 * @param cashAmt 实际现金-当日（合计）
	 */
	public void setCashAmt(BigDecimal cashAmt) {
		this.cashAmt = cashAmt;
	}

	/**
	 * 取得刷卡金额-单据统计-当日（合计）
	 * 
	 * @return cardAmt 刷卡金额-单据统计-当日（合计）
	 */
	public BigDecimal getCardAmt() {
		return cardAmt;
	}

	/**
	 * 设置刷卡金额-单据统计-当日（合计）
	 * 
	 * @param cardAmt 刷卡金额-单据统计-当日（合计）
	 */
	public void setCardAmt(BigDecimal cardAmt) {
		this.cardAmt = cardAmt;
	}

	/**
	 * 取得刷卡金额-电脑统计-当日（合计）
	 * 
	 * @return cardAmtBw 刷卡金额-电脑统计-当日（合计）
	 */
	public BigDecimal getCardAmtBw() {
		return cardAmtBw;
	}

	/**
	 * 设置刷卡金额-电脑统计-当日（合计）
	 * 
	 * @param cardAmtBw 刷卡金额-电脑统计-当日（合计）
	 */
	public void setCardAmtBw(BigDecimal cardAmtBw) {
		this.cardAmtBw = cardAmtBw;
	}

	/**
	 * 取得刷卡笔数-当日（合计）
	 * 
	 * @return cardNum 刷卡笔数-当日（合计）
	 */
	public Integer getCardNum() {
		return cardNum;
	}

	/**
	 * 设置刷卡笔数-当日（合计）
	 * 
	 * @param cardNum 刷卡笔数-当日（合计）
	 */
	public void setCardNum(Integer cardNum) {
		this.cardNum = cardNum;
	}

	/**
	 * 取得存款金额-当日（合计）
	 * 
	 * @return depositAmt 存款金额-当日（合计）
	 */
	public BigDecimal getDepositAmt() {
		return depositAmt;
	}

	/**
	 * 设置存款金额-当日（合计）
	 * 
	 * @param depositAmt 存款金额-当日（合计）
	 */
	public void setDepositAmt(BigDecimal depositAmt) {
		this.depositAmt = depositAmt;
	}

	/**
	 * 取得留存金额-当日
	 * 
	 * @return retainedAmt 留存金额-当日
	 */
	public BigDecimal getRetainedAmt() {
		return retainedAmt;
	}

	/**
	 * 设置留存金额-当日
	 * 
	 * @param retainedAmt 留存金额-当日
	 */
	public void setRetainedAmt(BigDecimal retainedAmt) {
		this.retainedAmt = retainedAmt;
	}

}
