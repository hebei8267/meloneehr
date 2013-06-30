package com.tjhx.entity.info;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.NaturalId;

import com.tjhx.entity.IdEntity;

/**
 * 货品供应商
 */
@Entity
@Table(name = "T_SUPPLIER")
// 默认的缓存策略.
// @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Supplier extends IdEntity {

	private static final long serialVersionUID = 4023319199643086119L;
	/** 供应商编号-百威 */
	private String supplierBwId;
	/** 供应商名称 */
	private String name;
	/** 付款方式 1-现款商户 2-月结商户 4-不定 */
	private String payType;
	/** 所在区域 */
	private Region region;
	/** 所在区域编码 */
	private String regionCode;
	/** 拼音码 */
	private String pyCode;
	/** 行号 */
	private Integer index;

	/**
	 * 取得供应商编号-百威
	 * 
	 * @return supplierBwId 供应商编号-百威
	 */
	@NaturalId
	@Column(nullable = false, length = 16)
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
	@Column(nullable = false, length = 32, unique = true)
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
	 * 取得付款方式
	 * 
	 * @return payType 付款方式
	 */
	@Column(length = 1)
	public String getPayType() {
		return payType;
	}

	/**
	 * 设置付款方式
	 * 
	 * @param payType 付款方式
	 */
	public void setPayType(String payType) {
		this.payType = payType;
	}

	/**
	 * 取得所在区域
	 * 
	 * @return region 所在区域
	 */
	@ManyToOne(cascade = CascadeType.PERSIST, fetch = FetchType.LAZY)
	@JoinColumn(name = "REGION_UUID")
	public Region getRegion() {
		return region;
	}

	/**
	 * 设置所在区域
	 * 
	 * @param region 所在区域
	 */
	public void setRegion(Region region) {
		this.region = region;
	}

	/**
	 * 取得拼音码
	 * 
	 * @return pyCode 拼音码
	 */
	@Column(length = 16)
	public String getPyCode() {
		return pyCode;
	}

	/**
	 * 设置拼音码
	 * 
	 * @param pyCode 拼音码
	 */
	public void setPyCode(String pyCode) {
		this.pyCode = pyCode;
	}

	/**
	 * 取得所在区域编码
	 * 
	 * @return regionCode 所在区域编码
	 */
	@Transient
	public String getRegionCode() {
		if (null != region) {
			return region.getCode();
		}
		return regionCode;
	}

	/**
	 * 设置所在区域编码
	 * 
	 * @param regionCode 所在区域编码
	 */
	public void setRegionCode(String regionCode) {
		this.regionCode = regionCode;
	}

	/**
	 * 取得行号
	 * 
	 * @return index 行号
	 */
	@NaturalId
	@Column(name = "_INDEX")
	public Integer getIndex() {
		return index;
	}

	/**
	 * 设置行号
	 * 
	 * @param index 行号
	 */
	public void setIndex(Integer index) {
		this.index = index;
	}
}
