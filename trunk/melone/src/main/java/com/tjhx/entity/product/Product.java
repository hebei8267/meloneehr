package com.tjhx.entity.product;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.NaturalId;

import com.tjhx.entity.IdEntity;

/**
 * 商品
 */
@Entity
@Table(name = "T_PRODUCT")
// 默认的缓存策略.
// @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Product extends IdEntity {

	private static final long serialVersionUID = 7536024990256617583L;

	/** 商品编号/条形码 */
	private String barCode;
	/** 商品名称-汉字 */
	private String name;
	/** 参考进价 */
	private double refPrice;
	/** 批发价 */
	private double wholeSalePrice;
	/** 零售价 */
	private double retailPrice;
	/** 会员价 */
	private double memberPrice;
	/** 商品详细描述 */
	private String descTxt;
	/** 商品品牌 */
	private ProductBrand productBrand;
	/** 商品类型 */
	private ProductType productType;
	/** 商品供应商 */
	private ProductSupplier productSupplier;
	/** 上传商品相片名称 */
	private String photoName;
	// -------------------------------------------------
	/** 商品品牌uuid */
	private Integer productBrandUuid;
	/** 商品类型uuid */
	private Integer productTypeUuid;
	/** 商品供应商ID */
	private String productSupplierId;
	/** 商品品牌名称-汉字 */
	private String productBrandName;
	/** 商品供应商名称-汉字 */
	private String productSupplierName;

	/**
	 * 取得商品编号/条形码
	 * 
	 * @return 商品编号/条形码
	 */
	@NaturalId
	@Column(length = 16)
	public String getBarCode() {
		return barCode;
	}

	/**
	 * 设置商品编号/条形码
	 * 
	 * @param barCode 商品编号/条形码
	 */
	public void setBarCode(String barCode) {
		this.barCode = barCode;
	}

	/**
	 * 取得商品名称-汉字
	 * 
	 * @return 商品名称-汉字
	 */
	@Column(nullable = false, length = 32)
	public String getName() {
		return name;
	}

	/**
	 * 设置商品名称-汉字
	 * 
	 * @param name 商品名称-汉字
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
	 * 取得商品详细描述
	 * 
	 * @return 商品详细描述
	 */
	public String getDescTxt() {
		return descTxt;
	}

	/**
	 * 设置商品详细描述
	 * 
	 * @param descTxt 商品详细描述
	 */
	public void setDescTxt(String descTxt) {
		this.descTxt = descTxt;
	}

	/**
	 * 取得商品品牌
	 * 
	 * @return 商品品牌
	 */
	@OneToOne(cascade = CascadeType.PERSIST, fetch = FetchType.LAZY)
	@JoinColumn(name = "PRODUCT_BRAND_UUID")
	public ProductBrand getProductBrand() {
		return productBrand;
	}

	/**
	 * 设置商品品牌
	 * 
	 * @param productBrand 商品品牌
	 */
	public void setProductBrand(ProductBrand productBrand) {
		this.productBrand = productBrand;
	}

	/**
	 * 取得商品类型
	 * 
	 * @return 商品类型
	 */
	@OneToOne(cascade = CascadeType.PERSIST, fetch = FetchType.LAZY)
	@JoinColumn(name = "PRODUCT_TYPE_UUID")
	public ProductType getProductType() {
		return productType;
	}

	/**
	 * 设置商品类型
	 * 
	 * @param productType 商品类型
	 */
	public void setProductType(ProductType productType) {
		this.productType = productType;
	}

	/**
	 * 取得商品供应商
	 * 
	 * @return 商品供应商
	 */
	@OneToOne(cascade = CascadeType.PERSIST, fetch = FetchType.LAZY)
	@JoinColumn(name = "PRODUCT_SUPPLIER_UUID")
	public ProductSupplier getProductSupplier() {
		return productSupplier;
	}

	/**
	 * 取得上传商品相片名称
	 * 
	 * @return 上传商品相片名称
	 */
	@Column(length = 32)
	public String getPhotoName() {
		return photoName;
	}

	/**
	 * 设置上传商品相片名称
	 * 
	 * @param photoName 上传商品相片名称
	 */
	public void setPhotoName(String photoName) {
		this.photoName = photoName;
	}

	/**
	 * 设置商品供应商
	 * 
	 * @param productSupplier 商品供应商
	 */
	public void setProductSupplier(ProductSupplier productSupplier) {
		this.productSupplier = productSupplier;
	}

	/**
	 * 取得商品品牌uuid
	 * 
	 * @return 商品品牌uuid
	 */
	@Transient
	public Integer getProductBrandUuid() {
		if (null != productBrand) {
			productBrandUuid = productBrand.getUuid();
		}
		return productBrandUuid;
	}

	/**
	 * 设置商品品牌uuid
	 * 
	 * @param productBrandUuid 商品品牌uuid
	 */
	public void setProductBrandUuid(Integer productBrandUuid) {
		this.productBrandUuid = productBrandUuid;
	}

	/**
	 * 取得商品类型uuid
	 * 
	 * @return 商品类型uuid
	 */
	@Transient
	public Integer getProductTypeUuid() {
		if (null != productType) {
			productTypeUuid = productType.getUuid();
		}
		return productTypeUuid;
	}

	/**
	 * 设置商品类型uuid
	 * 
	 * @param productTypeUuid 商品类型uuid
	 */
	public void setProductTypeUuid(Integer productTypeUuid) {
		this.productTypeUuid = productTypeUuid;
	}

	/**
	 * 取得商品供应商ID
	 * 
	 * @return 商品供应商ID
	 */
	@Transient
	public String getProductSupplierId() {
		if (null != productSupplier) {
			productSupplierId = productSupplier.getId();
		}
		return productSupplierId;
	}

	/**
	 * 设置商品供应商ID
	 * 
	 * @param productSupplierId 商品供应商ID
	 */
	public void setProductSupplierId(String productSupplierId) {
		this.productSupplierId = productSupplierId;
	}

	/**
	 * 取得商品品牌名称-汉字
	 * 
	 * @return 商品品牌名称-汉字
	 */
	@Transient
	public String getProductBrandName() {
		return productBrandName;
	}

	/**
	 * 设置商品品牌名称-汉字
	 * 
	 * @param productBrandName 商品品牌名称-汉字
	 */
	public void setProductBrandName(String productBrandName) {
		this.productBrandName = productBrandName;
	}

	/**
	 * 取得商品供应商名称-汉字
	 * 
	 * @return 商品供应商名称-汉字
	 */
	@Transient
	public String getProductSupplierName() {
		return productSupplierName;
	}

	/**
	 * 设置商品供应商名称-汉字
	 * 
	 * @param productSupplierName 商品供应商名称-汉字
	 */
	public void setProductSupplierName(String productSupplierName) {
		this.productSupplierName = productSupplierName;
	}
}
