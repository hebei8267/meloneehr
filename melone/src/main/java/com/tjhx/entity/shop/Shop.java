package com.tjhx.entity.shop;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import org.hibernate.annotations.NaturalId;

import com.tjhx.entity.base.IdEntity;

/**
 * 门店
 */
@Entity
@Table(name = "T_SHOP")
// 默认的缓存策略.
// @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Shop extends IdEntity {

	private static final long serialVersionUID = 5365460546248353444L;

	/** 门店编号 */
	private String id;
	/** 门店名称-汉字 */
	private String name;
	/** 门店电话号码 */
	private String telNum;
	/** 门店地址 */
	private String addr;
	/** 门店传真号码 */
	private String faxNum;
	/** 门店详细描述 */
	private String descTxt;

	/**
	 * 取得门店编号
	 * 
	 * @return 门店编号
	 */
	@NaturalId
	@Column(nullable = false, length = 16)
	public String getId() {
		return id;
	}

	/**
	 * 设置门店编号
	 * 
	 * @param id 门店编号
	 */
	public void setId(String id) {
		this.id = id;
	}

	/**
	 * 取得门店名称-汉字
	 * 
	 * @return 门店名称-汉字
	 */
	@Column(nullable = false, length = 32)
	public String getName() {
		return name;
	}

	/**
	 * 设置门店名称-汉字
	 * 
	 * @param name 门店名称-汉字
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * 取得门店电话号码
	 * 
	 * @return 门店电话号码
	 */
	@Column(length = 32)
	public String getTelNum() {
		return telNum;
	}

	/**
	 * 设置门店电话号码
	 * 
	 * @param telNum 门店电话号码
	 */
	public void setTelNum(String telNum) {
		this.telNum = telNum;
	}

	/**
	 * 取得门店地址
	 * 
	 * @return 门店地址
	 */
	public String getAddr() {
		return addr;
	}

	/**
	 * 设置门店地址
	 * 
	 * @param addr 门店地址
	 */
	public void setAddr(String addr) {
		this.addr = addr;
	}

	/**
	 * 取得门店传真号码
	 * 
	 * @return 门店传真号码
	 */
	@Column(length = 32)
	public String getFaxNum() {
		return faxNum;
	}

	/**
	 * 设置门店传真号码
	 * 
	 * @param faxNum 门店传真号码
	 */
	public void setFaxNum(String faxNum) {
		this.faxNum = faxNum;
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
	 * @param desc 门店详细描述
	 */
	public void setDescTxt(String descTxt) {
		this.descTxt = descTxt;
	}

}
