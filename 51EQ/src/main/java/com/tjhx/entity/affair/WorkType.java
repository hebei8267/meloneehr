package com.tjhx.entity.affair;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.tjhx.entity.IdEntity;
import com.tjhx.entity.struct.Organization;

/**
 * 上班类型
 * 
 * @author he_bei
 * 
 */
@Entity
@Table(name = "T_WORK_TYPE")
// 默认的缓存策略.
// @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class WorkType extends IdEntity {

	private static final long serialVersionUID = 8717393085158252366L;
	/** 上班类型名称 */
	private String name;
	/** 启用标记 1-启用 0-停用 */
	private String useFlg;
	/** 可编辑标记 */
	private boolean editFlg = false;
	/** 上班时间 */
	private String startDate;
	/** 下班时间 */
	private String endDate;
	/** 用户关联机构 */
	private Organization organization;
	// ---------------------------------------------
	/** 用户关联机构编号 */
	private Integer orgUuid;

	/**
	 * 取得上班类型名称
	 * 
	 * @return name 上班类型名称
	 */
	@Column(length = 32)
	public String getName() {
		return name;
	}

	/**
	 * 设置上班类型名称
	 * 
	 * @param name 上班类型名称
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * 取得启用标记1-启用0-停用
	 * 
	 * @return useFlg 启用标记1-启用0-停用
	 */
	@Column(length = 1)
	public String getUseFlg() {
		return useFlg;
	}

	/**
	 * 设置启用标记1-启用0-停用
	 * 
	 * @param useFlg 启用标记1-启用0-停用
	 */
	public void setUseFlg(String useFlg) {
		this.useFlg = useFlg;
	}

	/**
	 * 取得可编辑标记
	 * 
	 * @return editFlg 可编辑标记
	 */
	public boolean isEditFlg() {
		return editFlg;
	}

	/**
	 * 设置可编辑标记
	 * 
	 * @param editFlg 可编辑标记
	 */
	public void setEditFlg(boolean editFlg) {
		this.editFlg = editFlg;
	}

	/**
	 * 取得上班时间
	 * 
	 * @return startDate 上班时间
	 */
	@Column(length = 5)
	public String getStartDate() {
		return startDate;
	}

	/**
	 * 设置上班时间
	 * 
	 * @param startDate 上班时间
	 */
	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}

	/**
	 * 取得下班时间
	 * 
	 * @return endDate 下班时间
	 */
	@Column(length = 5)
	public String getEndDate() {
		return endDate;
	}

	/**
	 * 设置下班时间
	 * 
	 * @param endDate 下班时间
	 */
	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}

	/**
	 * 取得用户关联机构
	 * 
	 * @return organization 用户关联机构
	 */
	@ManyToOne(cascade = CascadeType.PERSIST, fetch = FetchType.LAZY)
	@JoinColumn(name = "ORG_UUID")
	public Organization getOrganization() {
		return organization;
	}

	/**
	 * 设置用户关联机构
	 * 
	 * @param organization 用户关联机构
	 */
	public void setOrganization(Organization organization) {
		this.organization = organization;
	}

	/**
	 * 取得用户关联机构编号
	 * 
	 * @return orgUuid 用户关联机构编号
	 */
	@Transient
	public Integer getOrgUuid() {
		return orgUuid;
	}

	/**
	 * 设置用户关联机构编号
	 * 
	 * @param orgUuid 用户关联机构编号
	 */
	public void setOrgUuid(Integer orgUuid) {
		this.orgUuid = orgUuid;
	}

}
