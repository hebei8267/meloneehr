package com.tjhx.entity.member;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.tjhx.entity.IdEntity;

@Entity
@Table(name = "T_EMPLOYEE")
// 默认的缓存策略.
// @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Employee extends IdEntity {

	private static final long serialVersionUID = 6188301640502063719L;

	/** 用户编号 */
	private Integer zkid;
	/** 用户编号-自定义 */
	private String code;
	/** 用户名 */
	private String name;
	/** 所属机构 */
	private Integer zkOrgId;

	/**
	 * 取得用户编号
	 * 
	 * @return zkid 用户编号
	 */
	public Integer getZkid() {
		return zkid;
	}

	/**
	 * 设置用户编号
	 * 
	 * @param zkid 用户编号
	 */
	public void setZkid(Integer zkid) {
		this.zkid = zkid;
	}

	
	
	
	
	/**
	 * 取得用户编号-自定义
	 * 
	 * @return code 用户编号-自定义
	 */
	@Column(length = 16)
	public String getCode() {
		return code;
	}

	/**
	 * 设置用户编号-自定义
	 * 
	 * @param code 用户编号-自定义
	 */
	public void setCode(String code) {
		this.code = code;
	}

	/**
	 * 取得用户名
	 * 
	 * @return name 用户名
	 */
	@Column(length = 32)
	public String getName() {
		return name;
	}

	/**
	 * 设置用户名
	 * 
	 * @param name 用户名
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * 取得所属机构
	 * 
	 * @return zkOrgId 所属机构
	 */
	public Integer getZkOrgId() {
		return zkOrgId;
	}

	/**
	 * 设置所属机构
	 * 
	 * @param zkOrgId 所属机构
	 */
	public void setZkOrgId(Integer zkOrgId) {
		this.zkOrgId = zkOrgId;
	}

}
