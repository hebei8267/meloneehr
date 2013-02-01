package com.tjhx.entity.accounts;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import org.hibernate.annotations.NaturalId;

import com.tjhx.entity.IdEntity;

/**
 * 销售流水
 */
@Entity
@Table(name = "T_CASH_RUN")
// 默认的缓存策略.
// @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class CashRun extends IdEntity {

	private static final long serialVersionUID = -2492530785174886747L;

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
	/** 上班类型(1早班、2晚班、4全天班) */
	private Integer jobType;
	/** 班前余额 */
	private BigDecimal initAmt = new BigDecimal("0");
	/** 当前销售 */
	private BigDecimal saleAmt = new BigDecimal("0");
	/** 实际现金-交班时 */
	private BigDecimal cashAmt = new BigDecimal("0");
	/** 刷卡金额-单据统计 */
	private BigDecimal cardAmt = new BigDecimal("0");
	/** 刷卡金额-电脑统计 */
	private BigDecimal cardAmtBw = new BigDecimal("0");
	/** 刷卡-凭证号 */
	private String cardCertNo;
	/** 刷卡笔数 */
	private Integer cardNum = 0;
	/** 存款金额 */
	private BigDecimal depositAmt = new BigDecimal("0");
	/** 存款人 */
	private String depositor;
	/** 存款银行（选择） */
	private String bankId;
	/** 卡号（选择） */
	private String bankCardNo;
	/** 留存金额-交班时 */
	private BigDecimal retainedAmt = new BigDecimal("0");
	/** 备注 */
	private String descTxt;
	/** 日结标记 */
	private Boolean dailyFlg = false;

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
	 * 取得上班类型(1早班、2晚班、4全天班)
	 * 
	 * @return jobType 上班类型(1早班、2晚班、4全天班)
	 */
	@NaturalId
	public Integer getJobType() {
		return jobType;
	}

	/**
	 * 设置上班类型(1早班、2晚班、4全天班)
	 * 
	 * @param jobType 上班类型(1早班、2晚班、4全天班)
	 */
	public void setJobType(Integer jobType) {
		this.jobType = jobType;
	}

	/**
	 * 取得班前余额
	 * 
	 * @return initAmt 班前余额
	 */
	public BigDecimal getInitAmt() {
		return initAmt;
	}

	/**
	 * 设置班前余额
	 * 
	 * @param initAmt 班前余额
	 */
	public void setInitAmt(BigDecimal initAmt) {
		this.initAmt = initAmt;
	}

	/**
	 * 取得当前销售
	 * 
	 * @return saleAmt 当前销售
	 */
	public BigDecimal getSaleAmt() {
		return saleAmt;
	}

	/**
	 * 设置当前销售
	 * 
	 * @param saleAmt 当前销售
	 */
	public void setSaleAmt(BigDecimal saleAmt) {
		this.saleAmt = saleAmt;
	}

	/**
	 * 取得实际现金-交班时
	 * 
	 * @return cashAmt 实际现金-交班时
	 */
	public BigDecimal getCashAmt() {
		return cashAmt;
	}

	/**
	 * 设置实际现金-交班时
	 * 
	 * @param cashAmt 实际现金-交班时
	 */
	public void setCashAmt(BigDecimal cashAmt) {
		this.cashAmt = cashAmt;
	}

	/**
	 * 取得刷卡金额-单据统计
	 * 
	 * @return cardAmt 刷卡金额-单据统计
	 */
	public BigDecimal getCardAmt() {
		return cardAmt;
	}

	/**
	 * 设置刷卡金额-单据统计
	 * 
	 * @param cardAmt 刷卡金额-单据统计
	 */
	public void setCardAmt(BigDecimal cardAmt) {
		this.cardAmt = cardAmt;
	}

	/**
	 * 取得刷卡金额-电脑统计
	 * 
	 * @return cardAmtBw 刷卡金额-电脑统计
	 */
	public BigDecimal getCardAmtBw() {
		return cardAmtBw;
	}

	/**
	 * 设置刷卡金额-电脑统计
	 * 
	 * @param cardAmtBw 刷卡金额-电脑统计
	 */
	public void setCardAmtBw(BigDecimal cardAmtBw) {
		this.cardAmtBw = cardAmtBw;
	}

	/**
	 * 取得刷卡笔数
	 * 
	 * @return cardNum 刷卡笔数
	 */
	public Integer getCardNum() {
		return cardNum;
	}

	/**
	 * 设置刷卡笔数
	 * 
	 * @param cardNum 刷卡笔数
	 */
	public void setCardNum(Integer cardNum) {
		this.cardNum = cardNum;
	}

	/**
	 * 取得存款金额
	 * 
	 * @return depositAmt 存款金额
	 */
	public BigDecimal getDepositAmt() {
		return depositAmt;
	}

	/**
	 * 设置存款金额
	 * 
	 * @param depositAmt 存款金额
	 */
	public void setDepositAmt(BigDecimal depositAmt) {
		this.depositAmt = depositAmt;
	}

	/**
	 * 取得存款人
	 * 
	 * @return depositor 存款人
	 */
	@Column(length = 16)
	public String getDepositor() {
		return depositor;
	}

	/**
	 * 设置存款人
	 * 
	 * @param depositor 存款人
	 */
	public void setDepositor(String depositor) {
		this.depositor = depositor;
	}

	/**
	 * 取得存款银行（选择）
	 * 
	 * @return bankId 存款银行（选择）
	 */
	@Column(length = 16)
	public String getBankId() {
		return bankId;
	}

	/**
	 * 设置存款银行（选择）
	 * 
	 * @param bankId 存款银行（选择）
	 */
	public void setBankId(String bankId) {
		this.bankId = bankId;
	}

	/**
	 * 取得卡号（选择）
	 * 
	 * @return bankCardNo 卡号（选择）
	 */
	@Column(length = 32)
	public String getBankCardNo() {
		return bankCardNo;
	}

	/**
	 * 设置卡号（选择）
	 * 
	 * @param bankCardNo 卡号（选择）
	 */
	public void setBankCardNo(String bankCardNo) {
		this.bankCardNo = bankCardNo;
	}

	/**
	 * 取得留存金额-交班时
	 * 
	 * @return retainedAmt 留存金额-交班时
	 */
	public BigDecimal getRetainedAmt() {
		return retainedAmt;
	}

	/**
	 * 设置留存金额-交班时
	 * 
	 * @param retainedAmt 留存金额-交班时
	 */
	public void setRetainedAmt(BigDecimal retainedAmt) {
		this.retainedAmt = retainedAmt;
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
	 * 取得日结标记
	 * 
	 * @return dailyFlg 日结标记
	 */
	public Boolean getDailyFlg() {
		return dailyFlg;
	}

	/**
	 * 设置日结标记
	 * 
	 * @param dailyFlg 日结标记
	 */
	public void setDailyFlg(Boolean dailyFlg) {
		this.dailyFlg = dailyFlg;
	}

	/**
	 * 取得刷卡-凭证号
	 * 
	 * @return cardCertNo 刷卡-凭证号
	 */
	@Column(length = 32)
	public String getCardCertNo() {
		return cardCertNo;
	}

	/**
	 * 设置刷卡-凭证号
	 * 
	 * @param cardCertNo 刷卡-凭证号
	 */
	public void setCardCertNo(String cardCertNo) {
		this.cardCertNo = cardCertNo;
	}

}
