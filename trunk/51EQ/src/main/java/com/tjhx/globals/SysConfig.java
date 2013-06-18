package com.tjhx.globals;

import java.math.BigDecimal;

public class SysConfig {
	private boolean cashDailyModel = true;

	private BigDecimal defaultRetainedAmt;

	private String reqBillSupplierTemplatePath;
	private String reqBillSupplierOutputPath;
	private String reqBillSupplierInputPath;
	private String productImgPath;

	/**
	 * 取得cashDailyModel
	 * 
	 * @return cashDailyModel cashDailyModel
	 */
	public boolean getCashDailyModel() {
		return cashDailyModel;
	}

	/**
	 * 设置cashDailyModel
	 * 
	 * @param cashDailyModel cashDailyModel
	 */
	public void setCashDailyModel(boolean cashDailyModel) {
		this.cashDailyModel = cashDailyModel;
	}

	/**
	 * 取得defaultRetainedAmt
	 * 
	 * @return defaultRetainedAmt defaultRetainedAmt
	 */
	public BigDecimal getDefaultRetainedAmt() {
		return defaultRetainedAmt;
	}

	/**
	 * 设置defaultRetainedAmt
	 * 
	 * @param defaultRetainedAmt defaultRetainedAmt
	 */
	public void setDefaultRetainedAmt(BigDecimal defaultRetainedAmt) {
		this.defaultRetainedAmt = defaultRetainedAmt;
	}

	/**
	 * 取得reqBillSupplierTemplatePath
	 * 
	 * @return reqBillSupplierTemplatePath reqBillSupplierTemplatePath
	 */
	public String getReqBillSupplierTemplatePath() {
		return reqBillSupplierTemplatePath;
	}

	/**
	 * 设置reqBillSupplierTemplatePath
	 * 
	 * @param reqBillSupplierTemplatePath reqBillSupplierTemplatePath
	 */
	public void setReqBillSupplierTemplatePath(String reqBillSupplierTemplatePath) {
		this.reqBillSupplierTemplatePath = reqBillSupplierTemplatePath;
	}

	/**
	 * 取得reqBillSupplierOutputPath
	 * 
	 * @return reqBillSupplierOutputPath reqBillSupplierOutputPath
	 */
	public String getReqBillSupplierOutputPath() {
		return reqBillSupplierOutputPath;
	}

	/**
	 * 设置reqBillSupplierOutputPath
	 * 
	 * @param reqBillSupplierOutputPath reqBillSupplierOutputPath
	 */
	public void setReqBillSupplierOutputPath(String reqBillSupplierOutputPath) {
		this.reqBillSupplierOutputPath = reqBillSupplierOutputPath;
	}

	/**
	 * 取得reqBillSupplierInputPath
	 * 
	 * @return reqBillSupplierInputPath reqBillSupplierInputPath
	 */
	public String getReqBillSupplierInputPath() {
		return reqBillSupplierInputPath;
	}

	/**
	 * 设置reqBillSupplierInputPath
	 * 
	 * @param reqBillSupplierInputPath reqBillSupplierInputPath
	 */
	public void setReqBillSupplierInputPath(String reqBillSupplierInputPath) {
		this.reqBillSupplierInputPath = reqBillSupplierInputPath;
	}

	/**
	 * 取得productImgPath
	 * 
	 * @return productImgPath productImgPath
	 */
	public String getProductImgPath() {
		return productImgPath;
	}

	/**
	 * 设置productImgPath
	 * 
	 * @param productImgPath productImgPath
	 */
	public void setProductImgPath(String productImgPath) {
		this.productImgPath = productImgPath;
	}

}
