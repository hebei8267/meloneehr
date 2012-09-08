package com.tjhx.globals;

/**
 * 系统配置信息Bean
 * 
 * @author
 */
public class SysConfig {
	/** 用户照片存储路径 */
	private String userPhotoPath;

	/**
	 * 取得用户照片存储路径
	 * 
	 * @return 用户照片存储路径
	 */
	public String getUserPhotoPath() {
		return userPhotoPath;
	}

	/**
	 * 设置用户照片存储路径
	 * 
	 * @param userPhotoPath 用户照片存储路径
	 */
	public void setUserPhotoPath(String userPhotoPath) {
		this.userPhotoPath = userPhotoPath;
	}

	// --------------------------------------------------------------------------
	/** 商品照片存储路径 */
	private String productPhotoPath;

	/**
	 * 取得商品照片存储路径
	 * 
	 * @return 商品照片存储路径
	 */
	public String getProductPhotoPath() {
		return productPhotoPath;
	}

	/**
	 * 设置商品照片存储路径
	 * 
	 * @param productPhotoPath 商品照片存储路径
	 */
	public void setProductPhotoPath(String productPhotoPath) {
		this.productPhotoPath = productPhotoPath;
	}
}
