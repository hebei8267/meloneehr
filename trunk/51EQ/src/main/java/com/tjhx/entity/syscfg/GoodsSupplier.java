package com.tjhx.entity.syscfg;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import org.hibernate.annotations.NaturalId;

import com.tjhx.entity.IdEntity;

/**
 * 货品供应商
 */
@Entity
@Table(name = "T_GOODS_SUPPLIER")
// 默认的缓存策略.
// @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class GoodsSupplier extends IdEntity {

	private static final long serialVersionUID = 4023319199643086119L;
	/** 供应商编号-百威 */
	private String supplierBwId;
	/** 供应商名称 */
	private String name;
	/** 详细描述 */
	private String descTxt;

	/**
	 * 取得供应商编号-百威
	 * 
	 * @return supplierBwId 供应商编号-百威
	 */
	@NaturalId
	@Column(nullable = false, length = 32)
	public String getSupplierBwId() {
		return supplierBwId;
	}

	/**
	 * 设置供应商编号-百威
	 * 
	 * @param supplierBwId 供应商编号-百威
	 */
	public void setSupplierBwId(String supplierBwId) {
		this.supplierBwId = supplierBwId;
	}

	/**
	 * 取得供应商名称
	 * 
	 * @return name 供应商名称
	 */
	@Column(nullable = false, length = 32)
	public String getName() {
		return name;
	}

	/**
	 * 设置供应商名称
	 * 
	 * @param name 供应商名称
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * 取得用户详细描述
	 * 
	 * @return 用户详细描述
	 */
	public String getDescTxt() {
		return descTxt;
	}

	/**
	 * 设置详细描述
	 * 
	 * @param descTxt 详细描述
	 */
	public void setDescTxt(String descTxt) {
		this.descTxt = descTxt;
	}
}
