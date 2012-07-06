package com.tjhx.entity.product;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import org.hibernate.annotations.NaturalId;

import com.tjhx.entity.base.IdEntity;

/**
 * 产品供应商
 */
@Entity
@Table(name = "T_PRODUCT_SUPPLIER")
// 默认的缓存策略.
// @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class ProductSupplier extends IdEntity {

	private static final long serialVersionUID = -4390673254610402705L;

	/** 供应商编号 */
	private String id;
	/** 供应商名称-汉字 */
	private String name;
	/** 供应商联系人 */
	private String contact;
	/** 供应商电话号码 */
	private String telNum;
	/** 供应商传真号码 */
	private String faxNum;
	/** 供应商Email */
	private String email;
	/** 供应商地址 */
	private String addr;
	/** 供应商邮编 */
	private String postCode;
	/** 门店详细描述 */
	private String descTxt;
	/** 税务登记号 */
	private String taxRegNum;
	/** 打款银行名称 */
	private String bankName;
	/** 打款银行账号 */
	private String bankAccount;

	/**
	 * 取得供应商编号
	 * 
	 * @return 供应商编号
	 */
	@NaturalId
	@Column(nullable = false, length = 16)
	public String getId() {
		return id;
	}

	/**
	 * 设置供应商编号
	 * 
	 * @param id 供应商编号
	 */
	public void setId(String id) {
		this.id = id;
	}

	/**
	 * 取得供应商名称-汉字
	 * 
	 * @return 供应商名称-汉字
	 */
	@Column(nullable = false, length = 32)
	public String getName() {
		return name;
	}

	/**
	 * 设置供应商名称-汉字
	 * 
	 * @param name 供应商名称-汉字
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * 取得供应商联系人
	 * 
	 * @return 供应商联系人
	 */
	@Column(length = 16)
	public String getContact() {
		return contact;
	}

	/**
	 * 设置供应商联系人
	 * 
	 * @param contact 供应商联系人
	 */
	public void setContact(String contact) {
		this.contact = contact;
	}

	/**
	 * 取得供应商电话号码
	 * 
	 * @return 供应商电话号码
	 */
	@Column(length = 32)
	public String getTelNum() {
		return telNum;
	}

	/**
	 * 设置供应商电话号码
	 * 
	 * @param telNum 供应商电话号码
	 */
	public void setTelNum(String telNum) {
		this.telNum = telNum;
	}

	/**
	 * 取得供应商传真号码
	 * 
	 * @return 供应商传真号码
	 */
	@Column(length = 32)
	public String getFaxNum() {
		return faxNum;
	}

	/**
	 * 设置供应商传真号码
	 * 
	 * @param faxNum 供应商传真号码
	 */
	public void setFaxNum(String faxNum) {
		this.faxNum = faxNum;
	}

	/**
	 * 取得供应商Email
	 * 
	 * @return 供应商Email
	 */
	@Column(length = 32)
	public String getEmail() {
		return email;
	}

	/**
	 * 设置供应商Email
	 * 
	 * @param email 供应商Email
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * 取得供应商地址
	 * 
	 * @return 供应商地址
	 */
	public String getAddr() {
		return addr;
	}

	/**
	 * 设置供应商地址
	 * 
	 * @param addr 供应商地址
	 */
	public void setAddr(String addr) {
		this.addr = addr;
	}

	/**
	 * 取得供应商邮编
	 * 
	 * @return 供应商邮编
	 */
	@Column(length = 16)
	public String getPostCode() {
		return postCode;
	}

	/**
	 * 设置供应商邮编
	 * 
	 * @param postCode 供应商邮编
	 */
	public void setPostCode(String postCode) {
		this.postCode = postCode;
	}

	/**
	 * 取得门店详细描述
	 * 
	 * @return 门店详细描述
	 */
	public String getDescTxt() {
		return descTxt;
	}

	/**
	 * 设置门店详细描述
	 * 
	 * @param descTxt 门店详细描述
	 */
	public void setDescTxt(String descTxt) {
		this.descTxt = descTxt;
	}

	/**
	 * 取得税务登记号
	 * 
	 * @return 税务登记号
	 */
	@Column(length = 32)
	public String getTaxRegNum() {
		return taxRegNum;
	}

	/**
	 * 设置税务登记号
	 * 
	 * @param taxRegNum 税务登记号
	 */
	public void setTaxRegNum(String taxRegNum) {
		this.taxRegNum = taxRegNum;
	}

	/**
	 * 取得打款银行名称
	 * 
	 * @return 打款银行名称
	 */
	@Column(length = 32)
	public String getBankName() {
		return bankName;
	}

	/**
	 * 设置打款银行名称
	 * 
	 * @param bankName 打款银行名称
	 */
	public void setBankName(String bankName) {
		this.bankName = bankName;
	}

	/**
	 * 取得打款银行账号
	 * 
	 * @return 打款银行账号
	 */
	@Column(length = 32)
	public String getBankAccount() {
		return bankAccount;
	}

	/**
	 * 设置打款银行账号
	 * 
	 * @param bankAccount 打款银行账号
	 */
	public void setBankAccount(String bankAccount) {
		this.bankAccount = bankAccount;
	}

}
