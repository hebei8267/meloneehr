package com.tjhx.dto;

import org.apache.commons.lang3.builder.ToStringBuilder;

public class ProductDTO {
	/** 商品编号/条形码 */
	private String barCode;
	/** 商品名称-汉字 */
	private String name;
	/** 批发价 */
	private double wholeSalePrice;
	/** 零售价 */
	private double retailPrice;
	/** 会员价 */
	private double memberPrice;
	/** 上传商品相片名称 */
	private String photoName;

	/**
	 * 取得商品编号/条形码
	 * 
	 * @return 商品编号/条形码
	 */
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
	 * 取得上传商品相片名称
	 * 
	 * @return 上传商品相片名称
	 */
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
	 * 重新实现toString()函数方便在日志中打印DTO信息.
	 */
	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}

}
