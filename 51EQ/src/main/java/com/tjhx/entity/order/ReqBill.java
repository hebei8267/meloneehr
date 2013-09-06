package com.tjhx.entity.order;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import org.hibernate.annotations.NaturalId;

import com.tjhx.entity.IdEntity;

/**
 * 门店要货单
 */
@Entity
@Table(name = "T_REQ_BILL_TMP")
// 默认的缓存策略.
// @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class ReqBill extends IdEntity {

	private static final long serialVersionUID = 5023594671871878136L;
	/** 处理批次号 */
	private String batchId;
	/** 行号 */
	private Integer index;
	/** 机构编号 */
	private String orgId;
	/** 货号 */
	private String productNo;
	/** 条码 */
	private String barcode;
	/** 商品名称 */
	private String productName;
	/** 库存数量 */
	private Integer inventoryNum;
	/** 申请数量 */
	private Integer appNum;
	/** 参考进价 */
	private BigDecimal refPrice;
	/** 主供应商 */
	private String supplierName;
	/** 备注 */
	private String remarks;

	/**
	 * 取得处理批次号
	 * 
	 * @return batchId 处理批次号
	 */
	@NaturalId
	@Column(nullable = false, length = 16)
	public String getBatchId() {
		return batchId;
	}

	/**
	 * 设置处理批次号
	 * 
	 * @param batchId 处理批次号
	 */
	public void setBatchId(String batchId) {
		this.batchId = batchId;
	}

	/**
	 * 取得行号
	 * 
	 * @return index 行号
	 */
	@NaturalId
	@Column(nullable = false, name = "_INDEX")
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

	/**
	 * 取得机构编号
	 * 
	 * @return orgId 机构编号
	 */
	@NaturalId
	@Column(nullable = false, length = 32)
	public String getOrgId() {
		return orgId;
	}

	/**
	 * 设置机构编号
	 * 
	 * @param orgId 机构编号
	 */
	public void setOrgId(String orgId) {
		this.orgId = orgId;
	}

	/**
	 * 取得货号
	 * 
	 * @return productNo 货号
	 */
	@Column(length = 16)
	public String getProductNo() {
		return productNo;
	}

	/**
	 * 设置货号
	 * 
	 * @param productNo 货号
	 */
	public void setProductNo(String productNo) {
		this.productNo = productNo;
	}

	/**
	 * 取得条码
	 * 
	 * @return barcode 条码
	 */
	@Column(length = 16)
	public String getBarcode() {
		return barcode;
	}

	/**
	 * 设置条码
	 * 
	 * @param barcode 条码
	 */
	public void setBarcode(String barcode) {
		this.barcode = barcode;
	}

	/**
	 * 取得商品名称
	 * 
	 * @return productName 商品名称
	 */
	@Column(length = 64)
	public String getProductName() {
		return productName;
	}

	/**
	 * 设置商品名称
	 * 
	 * @param productName 商品名称
	 */
	public void setProductName(String productName) {
		this.productName = productName;
	}

	/**
	 * 取得库存数量
	 * 
	 * @return inventoryNum 库存数量
	 */
	public Integer getInventoryNum() {
		return inventoryNum;
	}

	/**
	 * 设置库存数量
	 * 
	 * @param inventoryNum 库存数量
	 */
	public void setInventoryNum(Integer inventoryNum) {
		this.inventoryNum = inventoryNum;
	}

	/**
	 * 取得申请数量
	 * 
	 * @return appNum 申请数量
	 */
	public Integer getAppNum() {
		return appNum;
	}

	/**
	 * 设置申请数量
	 * 
	 * @param appNum 申请数量
	 */
	public void setAppNum(Integer appNum) {
		this.appNum = appNum;
	}

	/**
	 * 取得参考进价
	 * 
	 * @return refPrice 参考进价
	 */
	public BigDecimal getRefPrice() {
		return refPrice;
	}

	/**
	 * 设置参考进价
	 * 
	 * @param refPrice 参考进价
	 */
	public void setRefPrice(BigDecimal refPrice) {
		this.refPrice = refPrice;
	}

	/**
	 * 取得主供应商
	 * 
	 * @return supplierName 主供应商
	 */
	@Column(length = 32)
	public String getSupplierName() {
		return supplierName;
	}

	/**
	 * 设置主供应商
	 * 
	 * @param supplierName 主供应商
	 */
	public void setSupplierName(String supplierName) {
		this.supplierName = supplierName;
	}

	/**
	 * 取得备注
	 * 
	 * @return remarks 备注
	 */
	public String getRemarks() {
		return remarks;
	}

	/**
	 * 设置备注
	 * 
	 * @param remarks 备注
	 */
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

}