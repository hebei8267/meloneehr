package com.tjhx.entity.info;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import org.hibernate.annotations.NaturalId;

import com.tjhx.entity.IdEntity;

/**
 * 银行卡信息
 */
@Entity
@Table(name = "T_BANK_CARD")
public class BankCard extends IdEntity {

	private static final long serialVersionUID = 1270130153158385933L;
	/** 银行编号 */
	private String bankId;
	/**
	 * 银行卡号码
	 */
	private String bankCardNo;

	/**
	 * 取得银行编号
	 * 
	 * @return bankId 银行编号
	 */
	@NaturalId
	@Column(nullable = false, length = 16)
	public String getBankId() {
		return bankId;
	}

	/**
	 * 设置银行编号
	 * 
	 * @param bankId 银行编号
	 */
	public void setBankId(String bankId) {
		this.bankId = bankId;
	}

	/**
	 * 取得银行卡号码
	 * 
	 * @return bankCardNo 银行卡号码
	 */
	@NaturalId
	@Column(nullable = false, length = 32)
	public String getBankCardNo() {
		return bankCardNo;
	}

	/**
	 * 设置银行卡号码
	 * 
	 * @param bankCardNo 银行卡号码
	 */
	public void setBankCardNo(String bankCardNo) {
		this.bankCardNo = bankCardNo;
	}
}
