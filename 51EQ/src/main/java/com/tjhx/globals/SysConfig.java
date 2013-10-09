package com.tjhx.globals;

import java.math.BigDecimal;

public class SysConfig {
	private boolean cashDailyModel = true;

	private BigDecimal defaultRetainedAmt;

	private String excelTemplatePath;
	private String reqBillSupplierOutputPath;
	private String reqBillSupplierInputPath;
	private String productImgPath;
	private String reportTmpPath;

	/** 考勤计算-开始有效时间(当日时间) */
	private String punchClockStart;
	/** 考勤计算-结束有效时间(次日时间) */
	private String punchClockEnd;
	/** 考勤计算-重计算天数 */
	private int punchClockRecalDays;
	/** 考勤计算-重计算天数 */
	private int synBwSaleDays;
	/** 与百威销售额-差额-额度 */
	private float bwSaleDifAmount;

	/** 可排班天数 */
	private int workScheduleDays;
	/** 可查看排班天数 */
	private int workScheduleOverDays;

	/** 门店备用金可编辑天数 */
	private int pettyCashEditDays;
	/** 门店备用金可查看天数 */
	private int pettyCashViewDays;
	/** 门店备用金重计算天数 */
	private int pettyCashCalculateDays;
	/** 门店日销售信息重计算天数 */
	private int salesDayTotalDays;

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
	 * 取得excelTemplatePath
	 * 
	 * @return excelTemplatePath excelTemplatePath
	 */
	public String getExcelTemplatePath() {
		return excelTemplatePath;
	}

	/**
	 * 设置excelTemplatePath
	 * 
	 * @param excelTemplatePath excelTemplatePath
	 */
	public void setExcelTemplatePath(String excelTemplatePath) {
		this.excelTemplatePath = excelTemplatePath;
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

	/**
	 * 取得reportTmpPath
	 * 
	 * @return reportTmpPath reportTmpPath
	 */
	public String getReportTmpPath() {
		return reportTmpPath;
	}

	/**
	 * 设置reportTmpPath
	 * 
	 * @param reportTmpPath reportTmpPath
	 */
	public void setReportTmpPath(String reportTmpPath) {
		this.reportTmpPath = reportTmpPath;
	}

	/**
	 * 取得考勤计算-开始有效时间(当日时间)
	 * 
	 * @return punchClockStart 考勤计算-开始有效时间(当日时间)
	 */
	public String getPunchClockStart() {
		return punchClockStart;
	}

	/**
	 * 设置考勤计算-开始有效时间(当日时间)
	 * 
	 * @param punchClockStart 考勤计算-开始有效时间(当日时间)
	 */
	public void setPunchClockStart(String punchClockStart) {
		this.punchClockStart = punchClockStart;
	}

	/**
	 * 取得考勤计算-结束有效时间(次日时间)
	 * 
	 * @return punchClockEnd 考勤计算-结束有效时间(次日时间)
	 */
	public String getPunchClockEnd() {
		return punchClockEnd;
	}

	/**
	 * 设置考勤计算-结束有效时间(次日时间)
	 * 
	 * @param punchClockEnd 考勤计算-结束有效时间(次日时间)
	 */
	public void setPunchClockEnd(String punchClockEnd) {
		this.punchClockEnd = punchClockEnd;
	}

	/**
	 * 取得考勤计算-重计算天数
	 * 
	 * @return punchClockRecalDays 考勤计算-重计算天数
	 */
	public int getPunchClockRecalDays() {
		return punchClockRecalDays;
	}

	/**
	 * 设置考勤计算-重计算天数
	 * 
	 * @param punchClockRecalDays 考勤计算-重计算天数
	 */
	public void setPunchClockRecalDays(int punchClockRecalDays) {
		this.punchClockRecalDays = punchClockRecalDays;
	}

	/**
	 * 取得考勤计算-重计算天数
	 * 
	 * @return synBwSaleDays 考勤计算-重计算天数
	 */
	public int getSynBwSaleDays() {
		return synBwSaleDays;
	}

	/**
	 * 设置考勤计算-重计算天数
	 * 
	 * @param synBwSaleDays 考勤计算-重计算天数
	 */
	public void setSynBwSaleDays(int synBwSaleDays) {
		this.synBwSaleDays = synBwSaleDays;
	}

	/**
	 * 取得与百威销售额-差额-额度
	 * 
	 * @return bwSaleDifAmount 与百威销售额-差额-额度
	 */
	public float getBwSaleDifAmount() {
		return bwSaleDifAmount;
	}

	/**
	 * 设置与百威销售额-差额-额度
	 * 
	 * @param bwSaleDifAmount 与百威销售额-差额-额度
	 */
	public void setBwSaleDifAmount(float bwSaleDifAmount) {
		this.bwSaleDifAmount = bwSaleDifAmount;
	}

	/**
	 * 取得可排班天数
	 * 
	 * @return workScheduleDays 可排班天数
	 */
	public int getWorkScheduleDays() {
		return workScheduleDays;
	}

	/**
	 * 设置可排班天数
	 * 
	 * @param workScheduleDays 可排班天数
	 */
	public void setWorkScheduleDays(int workScheduleDays) {
		this.workScheduleDays = workScheduleDays;
	}

	/**
	 * 取得可查看排班天数
	 * 
	 * @return workScheduleOverDays 可查看排班天数
	 */
	public int getWorkScheduleOverDays() {
		return workScheduleOverDays;
	}

	/**
	 * 设置可查看排班天数
	 * 
	 * @param workScheduleOverDays 可查看排班天数
	 */
	public void setWorkScheduleOverDays(int workScheduleOverDays) {
		this.workScheduleOverDays = workScheduleOverDays;
	}

	/**
	 * 取得门店备用金可编辑天数
	 * 
	 * @return pettyCashEditDays 门店备用金可编辑天数
	 */
	public int getPettyCashEditDays() {
		return pettyCashEditDays;
	}

	/**
	 * 设置门店备用金可编辑天数
	 * 
	 * @param pettyCashEditDays 门店备用金可编辑天数
	 */
	public void setPettyCashEditDays(int pettyCashEditDays) {
		this.pettyCashEditDays = pettyCashEditDays;
	}

	/**
	 * 取得门店备用金可查看天数
	 * 
	 * @return pettyCashViewDays 门店备用金可查看天数
	 */
	public int getPettyCashViewDays() {
		return pettyCashViewDays;
	}

	/**
	 * 设置门店备用金可查看天数
	 * 
	 * @param pettyCashViewDays 门店备用金可查看天数
	 */
	public void setPettyCashViewDays(int pettyCashViewDays) {
		this.pettyCashViewDays = pettyCashViewDays;
	}

	/**
	 * 取得门店备用金重计算天数
	 * 
	 * @return pettyCashCalculateDays 门店备用金重计算天数
	 */
	public int getPettyCashCalculateDays() {
		return pettyCashCalculateDays;
	}

	/**
	 * 设置门店备用金重计算天数
	 * 
	 * @param pettyCashCalculateDays 门店备用金重计算天数
	 */
	public void setPettyCashCalculateDays(int pettyCashCalculateDays) {
		this.pettyCashCalculateDays = pettyCashCalculateDays;
	}

	/**
	 * 取得门店日销售信息重计算天数
	 * 
	 * @return salesDayTotalDays 门店日销售信息重计算天数
	 */
	public int getSalesDayTotalDays() {
		return salesDayTotalDays;
	}

	/**
	 * 设置门店日销售信息重计算天数
	 * 
	 * @param salesDayTotalDays 门店日销售信息重计算天数
	 */
	public void setSalesDayTotalDays(int salesDayTotalDays) {
		this.salesDayTotalDays = salesDayTotalDays;
	}

}
