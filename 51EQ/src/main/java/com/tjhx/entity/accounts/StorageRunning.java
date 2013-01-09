package com.tjhx.entity.accounts;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.NaturalId;

import com.tjhx.entity.IdEntity;
import com.tjhx.entity.syscfg.GoodsSupplier;

/**
 * 入库情况
 */
@Entity
@Table(name = "T_STORAGE_RUNNING")
// 默认的缓存策略.
// @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class StorageRunning extends IdEntity {

	private static final long serialVersionUID = -5665981916597548958L;
	/** 机构编号 */
	private String orgId;
	/** 开单日期 */
	private String recordDate;
	/** 供应商 */
	private GoodsSupplier supplier;
	/** 供应商编号-百威 */
	private String supplierBwId;
	/** 开单日期-年 */
	private String recordDateY;
	/** 开单日期-月 */
	private String recordDateM;
	/** 入货日期 */
	private String intoDate;
	/** 入货单号 */
	private String recordNo;
	/** 开单金额 */
	private Double recordAmt;
	/** 入库金额 */
	private Double optAmt;
	/** 入库人名称 */
	private String optPerName;
	/** 店长审核 */
	private boolean auditFlg;
	/** 备注 */
	private String descTxt;

	/**
	 * 取得机构编号
	 * 
	 * @return orgId 机构编号
	 */
	@NaturalId
	@Column(name = "ORG_ID", length = 32)
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
	 * 取得开单日期
	 * 
	 * @return recordDate 开单日期
	 */
	@NaturalId
	@Column(name = "RECORD_DATE", length = 8)
	public String getRecordDate() {
		return recordDate;
	}

	/**
	 * 设置开单日期
	 * 
	 * @param recordDate 开单日期
	 */
	public void setRecordDate(String recordDate) {
		this.recordDate = recordDate;
	}

	/**
	 * 取得供应商
	 * 
	 * @return supplier 供应商
	 */
	@ManyToOne
	@JoinColumn(name = "GOODS_SUPPLIER_H_ID")
	public GoodsSupplier getSupplier() {
		return supplier;
	}

	/**
	 * 设置供应商
	 * 
	 * @param supplier 供应商
	 */
	public void setSupplier(GoodsSupplier supplier) {
		this.supplier = supplier;
	}

	/**
	 * 取得供应商编号-百威
	 * 
	 * @return supplierBwId 供应商编号-百威
	 */
	@Column(name = "GOODS_SUPPLIER_BW_ID", length = 32)
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
	 * 取得开单日期-年
	 * 
	 * @return recordDateY 开单日期-年
	 */
	@Column(name = "RECORD_DATE_Y", length = 4)
	public String getRecordDateY() {
		return recordDateY;
	}

	/**
	 * 设置开单日期-年
	 * 
	 * @param recordDateY 开单日期-年
	 */
	public void setRecordDateY(String recordDateY) {
		this.recordDateY = recordDateY;
	}

	/**
	 * 取得开单日期-月
	 * 
	 * @return recordDateM 开单日期-月
	 */
	@Column(name = "RECORD_DATE_M", length = 2)
	public String getRecordDateM() {
		return recordDateM;
	}

	/**
	 * 设置开单日期-月
	 * 
	 * @param recordDateM 开单日期-月
	 */
	public void setRecordDateM(String recordDateM) {
		this.recordDateM = recordDateM;
	}

	/**
	 * 取得入货日期
	 * 
	 * @return intoDate 入货日期
	 */
	@Column(name = "INTO_DATE", length = 8)
	public String getIntoDate() {
		return intoDate;
	}

	/**
	 * 设置入货日期
	 * 
	 * @param intoDate 入货日期
	 */
	public void setIntoDate(String intoDate) {
		this.intoDate = intoDate;
	}

	/**
	 * 取得入货单号
	 * 
	 * @return recordNo 入货单号
	 */
	@Column(name = "RECORD_NO", length = 32)
	public String getRecordNo() {
		return recordNo;
	}

	/**
	 * 设置入货单号
	 * 
	 * @param recordNo 入货单号
	 */
	public void setRecordNo(String recordNo) {
		this.recordNo = recordNo;
	}

	/**
	 * 取得开单金额
	 * 
	 * @return recordAmt 开单金额
	 */
	@Column(name = "RECORD_AMT")
	public Double getRecordAmt() {
		return recordAmt;
	}

	/**
	 * 设置开单金额
	 * 
	 * @param recordAmt 开单金额
	 */
	public void setRecordAmt(Double recordAmt) {
		this.recordAmt = recordAmt;
	}

	/**
	 * 取得入库金额
	 * 
	 * @return optAmt 入库金额
	 */
	@Column(name = "OPT_AMT")
	public Double getOptAmt() {
		return optAmt;
	}

	/**
	 * 设置入库金额
	 * 
	 * @param optAmt 入库金额
	 */
	public void setOptAmt(Double optAmt) {
		this.optAmt = optAmt;
	}

	/**
	 * 取得入库人名称
	 * 
	 * @return optPerName 入库人名称
	 */
	@Column(name = "OPT_PER_NAME", length = 32)
	public String getOptPerName() {
		return optPerName;
	}

	/**
	 * 设置入库人名称
	 * 
	 * @param optPerName 入库人名称
	 */
	public void setOptPerName(String optPerName) {
		this.optPerName = optPerName;
	}

	/**
	 * 取得店长审核
	 * 
	 * @return auditFlg 店长审核
	 */
	@Column(name = "AUDIT_FLG")
	public boolean isAuditFlg() {
		return auditFlg;
	}

	/**
	 * 设置店长审核
	 * 
	 * @param auditFlg 店长审核
	 */
	public void setAuditFlg(boolean auditFlg) {
		this.auditFlg = auditFlg;
	}

	/**
	 * 取得备注
	 * 
	 * @return descTxt 备注
	 */
	@Column(name = "DESC_TXT")
	public String getDescTxt() {
		return descTxt;
	}

	/**
	 * 设置备注
	 * 
	 * @param descTxt 备注
	 */
	public void setDescTxt(String descTxt) {
		this.descTxt = descTxt;
	}

}
