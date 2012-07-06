package com.tjhx.entity.product;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.NaturalId;

import com.tjhx.entity.base.IdEntity;

/**
 * 产品
 */
@Entity
@Table(name = "T_PRODUCT")
// 默认的缓存策略.
// @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Product extends IdEntity {

	private static final long serialVersionUID = 7536024990256617583L;

	/** 产品编号 */
	private String id;
	/** 产品条形码 */
	private String barCode;
	/** 产品名称-汉字 */
	private String name;
	/** 参考进价 */
	private double refPrice;
	/** 批发价 */
	private double wholeSalePrice;
	/** 零售价 */
	private double retailPrice;
	/** 会员价 */
	private double memberPrice;
	/** 产品详细描述 */
	private String descTxt;
	/** 产品品牌 */
	private ProductBrand ProductBrand;
	/** 产品类型 */
	private ProductType ProductType;
	/** 产品标签 */
	private ProductTag ProductTag;
	/** 产品供应商 */
	private ProductSupplier ProductSupplier;

	/**
	 * 取得产品编号
	 * 
	 * @return 产品编号
	 */
	@NaturalId
	@Column(nullable = false, length = 16)
	public String getId() {
		return id;
	}

	/**
	 * 设置产品编号
	 * 
	 * @param id 产品编号
	 */
	public void setId(String id) {
		this.id = id;
	}

	/**
	 * 取得产品条形码
	 * 
	 * @return 产品条形码
	 */
	@Column(length = 32)
	public String getBarCode() {
		return barCode;
	}

	/**
	 * 设置产品条形码
	 * 
	 * @param barCode 产品条形码
	 */
	public void setBarCode(String barCode) {
		this.barCode = barCode;
	}

	/**
	 * 取得产品名称-汉字
	 * 
	 * @return 产品名称-汉字
	 */
	@Column(nullable = false, length = 32)
	public String getName() {
		return name;
	}

	/**
	 * 设置产品名称-汉字
	 * 
	 * @param name 产品名称-汉字
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * 取得参考进价
	 * 
	 * @return 参考进价
	 */
	public double getRefPrice() {
		return refPrice;
	}

	/**
	 * 设置参考进价
	 * 
	 * @param refPrice 参考进价
	 */
	public void setRefPrice(double refPrice) {
		this.refPrice = refPrice;
	}

	/**
	 * 取得批发价
	 * 
	 * @return 批发价
	 */
	public double getWholeSalePrice() {
		return wholeSalePrice;
	}

	/**
	 * 设置批发价
	 * 
	 * @param wholeSalePrice 批发价
	 */
	public void setWholeSalePrice(double wholeSalePrice) {
		this.wholeSalePrice = wholeSalePrice;
	}

	/**
	 * 取得零售价
	 * 
	 * @return 零售价
	 */
	public double getRetailPrice() {
		return retailPrice;
	}

	/**
	 * 设置零售价
	 * 
	 * @param retailPrice 零售价
	 */
	public void setRetailPrice(double retailPrice) {
		this.retailPrice = retailPrice;
	}

	/**
	 * 取得会员价
	 * 
	 * @return 会员价
	 */
	public double getMemberPrice() {
		return memberPrice;
	}

	/**
	 * 设置会员价
	 * 
	 * @param memberPrice 会员价
	 */
	public void setMemberPrice(double memberPrice) {
		this.memberPrice = memberPrice;
	}

	/**
	 * 取得产品详细描述
	 * 
	 * @return 产品详细描述
	 */
	public String getDescTxt() {
		return descTxt;
	}

	/**
	 * 设置产品详细描述
	 * 
	 * @param descTxt 产品详细描述
	 */
	public void setDescTxt(String descTxt) {
		this.descTxt = descTxt;
	}

	/**
	 * 取得产品品牌
	 * 
	 * @return 产品品牌
	 */
	@OneToOne(cascade = CascadeType.REFRESH)
	@JoinColumn(name = "PRODUCT_BRAND_UUID")
	public ProductBrand getProductBrand() {
		return ProductBrand;
	}

	/**
	 * 设置产品品牌
	 * 
	 * @param ProductBrand 产品品牌
	 */
	public void setProductBrand(ProductBrand ProductBrand) {
		this.ProductBrand = ProductBrand;
	}

	/**
	 * 取得产品类型
	 * 
	 * @return 产品类型
	 */
	@OneToOne(cascade = CascadeType.REFRESH)
	@JoinColumn(name = "PRODUCT_TYPE_UUID")
	public ProductType getProductType() {
		return ProductType;
	}

	/**
	 * 设置产品类型
	 * 
	 * @param ProductType 产品类型
	 */
	public void setProductType(ProductType ProductType) {
		this.ProductType = ProductType;
	}

	/**
	 * 取得产品标签
	 * 
	 * @return 产品标签
	 */
	@OneToOne(cascade = CascadeType.REFRESH)
	@JoinColumn(name = "PRODUCT_TAG_UUID")
	public ProductTag getProductTag() {
		return ProductTag;
	}

	/**
	 * 设置产品标签
	 * 
	 * @param ProductTag 产品标签
	 */
	public void setProductTag(ProductTag ProductTag) {
		this.ProductTag = ProductTag;
	}

	/**
	 * 取得产品供应商
	 * 
	 * @return 产品供应商
	 */
	@OneToOne(cascade = CascadeType.REFRESH)
	@JoinColumn(name = "PRODUCT_SUPPLIER_UUID")
	public ProductSupplier getProductSupplier() {
		return ProductSupplier;
	}

	/**
	 * 设置产品供应商
	 * 
	 * @param ProductSupplier 产品供应商
	 */
	public void setProductSupplier(ProductSupplier ProductSupplier) {
		this.ProductSupplier = ProductSupplier;
	}

}
