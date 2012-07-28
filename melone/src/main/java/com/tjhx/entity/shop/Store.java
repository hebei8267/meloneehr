package com.tjhx.entity.shop;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.NaturalId;

import com.tjhx.entity.IdEntity;

/**
 * 仓库
 */
@Entity
@Table(name = "T_STORE")
// 默认的缓存策略.
// @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Store extends IdEntity {

	private static final long serialVersionUID = 9023490941810549970L;

	/** 仓库编号 */
	private String id;
	/** 仓库名称-汉字 */
	private String name;
	/** 仓库电话号码 */
	private String telNum;
	/** 仓库地址 */
	private String addr;
	/** 仓库详细描述 */
	private String descTxt;
	/** 仓库所属类型 */
	private StoreType storeType;
	/** 仓库所属类型对象唯一标识 */
	private Integer storeTypeUuid;

	/**
	 * 取得仓库编号
	 * 
	 * @return 仓库编号
	 */
	@NaturalId
	@Column(nullable = false, length = 16)
	public String getId() {
		return id;
	}

	/**
	 * 设置仓库编号
	 * 
	 * @param id 仓库编号
	 */
	public void setId(String id) {
		this.id = id;
	}

	/**
	 * 取得仓库名称-汉字
	 * 
	 * @return 仓库名称-汉字
	 */
	@Column(nullable = false, length = 32)
	public String getName() {
		return name;
	}

	/**
	 * 设置仓库名称-汉字
	 * 
	 * @param name 仓库名称-汉字
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * 取得仓库电话号码
	 * 
	 * @return 仓库电话号码
	 */
	@Column(length = 32)
	public String getTelNum() {
		return telNum;
	}

	/**
	 * 设置仓库电话号码
	 * 
	 * @param telNum 仓库电话号码
	 */
	public void setTelNum(String telNum) {
		this.telNum = telNum;
	}

	/**
	 * 取得仓库地址
	 * 
	 * @return 仓库地址
	 */
	public String getAddr() {
		return addr;
	}

	/**
	 * 设置仓库地址
	 * 
	 * @param addr 仓库地址
	 */
	public void setAddr(String addr) {
		this.addr = addr;
	}

	/**
	 * 取得仓库详细描述
	 * 
	 * @return 仓库详细描述
	 */
	public String getDescTxt() {
		return descTxt;
	}

	/**
	 * 设置仓库详细描述
	 * 
	 * @param descTxt 仓库详细描述
	 */
	public void setDescTxt(String descTxt) {
		this.descTxt = descTxt;
	}

	/**
	 * 取得仓库所属类型
	 * 
	 * @return 仓库所属类型
	 */
	@OneToOne(cascade = CascadeType.REFRESH)
	@JoinColumn(name = "STORE_TYPE_UUID")
	public StoreType getStoreType() {
		return storeType;
	}

	/**
	 * 设置仓库所属类型
	 * 
	 * @param storeType 仓库所属类型
	 */
	public void setStoreType(StoreType storeType) {
		this.storeType = storeType;
	}

	/**
	 * 取得仓库所属类型对象唯一标识
	 * 
	 * @return 仓库所属类型对象唯一标识
	 */
	@Transient
	public Integer getStoreTypeUuid() {
		return storeTypeUuid;
	}

	/**
	 * 设置仓库所属类型对象唯一标识
	 * 
	 * @param storeTypeUuid 仓库所属类型对象唯一标识
	 */
	public void setStoreTypeUuid(Integer storeTypeUuid) {
		this.storeTypeUuid = storeTypeUuid;
	}

}
