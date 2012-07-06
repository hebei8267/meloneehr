package com.tjhx.entity;

import java.io.Serializable;
import java.sql.Timestamp;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.Version;

import org.apache.commons.lang3.builder.ToStringBuilder;

/**
 * 统一定义id的entity基类.
 * 
 * 基类统一定义id的属性名称、数据类型、列名映射及生成策略. 子类可重载getId()函数重定义id的列名映射和生成策略.
 * 
 * @author
 */
// JPA Entity基类的标识
@MappedSuperclass
public abstract class IdEntity implements Serializable {

	private static final long serialVersionUID = -4108066747601937361L;

	/** 对象唯一标识 */
	private Integer uuid;
	/** 对象创建时间（Timestamp） */
	private Timestamp createDate;
	/** Create_User_ID */
	private String createUserId;
	/** 对象更新时间（Timestamp） */
	private Timestamp updateDate;
	/** Update_User_ID */
	private String updateUserId;
	/** Hibernate_Version */
	private Integer version;

	@Id
	@Column(name = "UUID", nullable = true)
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Integer getUuid() {
		return uuid;
	}

	public void setUuid(Integer uuid) {
		this.uuid = uuid;
	}

	@Basic
	@Column(name = "CREATE_DATE", nullable = false, updatable = false)
	public Timestamp getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Timestamp createDate) {
		this.createDate = createDate;
	}

	@Basic
	@Column(name = "CREATE_USER_ID", nullable = false, length = 32, updatable = false)
	public String getCreateUserId() {
		return createUserId;
	}

	public void setCreateUserId(String createUserId) {
		this.createUserId = createUserId;
	}

	@Basic
	@Column(name = "UPDATE_USER_ID", nullable = false, length = 32)
	public String getUpdateUserId() {
		return updateUserId;
	}

	public void setUpdateUserId(String updateUserId) {
		this.updateUserId = updateUserId;
	}

	@Basic
	@Column(name = "UPDATE_DATE", nullable = false)
	public Timestamp getUpdateDate() {
		return updateDate;
	}

	public void setUpdateDate(Timestamp updateDate) {
		this.updateDate = updateDate;
	}

	@Version
	@Column(name = "VERSION", nullable = false)
	public Integer getVersion() {
		return version;
	}

	public void setVersion(Integer version) {
		this.version = version;
	}

	public IdEntity() {

	}

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}
}
