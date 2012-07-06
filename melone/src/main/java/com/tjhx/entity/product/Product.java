package com.tjhx.entity.product;

/**
 * 产品
 */
public class Product {
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
	/** 产品品牌 */
	private ProductBrand ProductBrand;
	/** 产品类型 */
	private ProductType ProductType;
	/** 产品标签 */
	private ProductTag ProductTag;
	/** 产品供应商 */
	private ProductSupplier ProductSupplier;
}
