package com.tjhx.globals;

/**
 * 系统配置信息Bean
 * 
 * @author
 */
public class SysConfig {
	/** 照片存储ROOT路径 */
	private String photoRootPath;

	/**
	 * 取得照片存储ROOT路径
	 * 
	 * @return 照片存储ROOT路径
	 */
	public String getPhotoRootPath() {
		return photoRootPath;
	}

	/**
	 * 设置照片存储ROOT路径
	 * 
	 * @param photoRootPath 照片存储ROOT路径
	 */
	public void setPhotoRootPath(String photoRootPath) {
		this.photoRootPath = photoRootPath;
	}

	/**
	 * 取得用户照片存储路径
	 * 
	 * @return 用户照片存储路径
	 */
	public String getUserPhotoPath() {
		return getPhotoRootPath() + "user/";
	}

	/**
	 * 取得商品照片存储路径
	 * 
	 * @return 商品照片存储路径
	 */
	public String getProductPhotoPath() {
		return getPhotoRootPath() + "product/";
	}

	// --------------------------------------------------------------------------
	//
	// --------------------------------------------------------------------------
	/** 商品Json Js文件存储路径 */
	private String productJsonJsFilePath;

	/**
	 * 取得商品Json Js文件存储路径
	 * 
	 * @return 商品Json Js文件存储路径
	 */
	public String getProductJsonJsFilePath() {
		return productJsonJsFilePath;
	}

	/**
	 * 设置商品Json Js文件存储路径
	 * 
	 * @param productJsonJsFilePath 商品Json Js文件存储路径
	 */
	public void setProductJsonJsFilePath(String productJsonJsFilePath) {
		this.productJsonJsFilePath = productJsonJsFilePath;
	}

}
