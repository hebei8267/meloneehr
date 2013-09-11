package com.tjhx.entity.affair;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.NaturalId;

import com.tjhx.entity.IdEntity;
import com.tjhx.entity.struct.Organization;

/**
 * 排班表
 */
@Entity
@Table(name = "T_WORK_SCHEDULE")
// 默认的缓存策略.
// @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class WorkSchedule extends IdEntity {

	static final long serialVersionUID = -4223149489944716546L;

	/** 员工编号-自定义 */
	private String empCode;
	/** 排班日期 */
	private String scheduleDate;
	/** 排班日期YYYY */
	private String scheduleDateY;
	/** 排班日期MM */
	private String scheduleDateM;
	/** 排班日期YYYYMM */
	private String scheduleDateYM;
	/** 排班日期 */
	private String scheduleShow;
	/** 上班时间 HH:mm */
	private String startDate;
	/** 下班时间 HH:mm */
	private String endDate;
	/** 工作时间 HH:mm - HH:mm */
	private String workDate;
	/** 用户关联机构 */
	private Organization organization;
	/** 上班类型Uuid */
	private Integer workTypeUuid;
	// ---------------------------------------------
	/** 用户关联机构编号 */
	private Integer orgUuid;

	/**
	 * 取得员工编号-自定义
	 * 
	 * @return empCode 员工编号-自定义
	 */
	@NaturalId
	@Column(length = 16)
	public String getEmpCode() {
		return empCode;
	}

	/**
	 * 设置员工编号-自定义
	 * 
	 * @param empCode 员工编号-自定义
	 */
	public void setEmpCode(String empCode) {
		this.empCode = empCode;
	}

	/**
	 * 取得排班日期
	 * 
	 * @return scheduleDate 排班日期
	 */
	@NaturalId
	@Column(length = 8)
	public String getScheduleDate() {
		return scheduleDate;
	}

	/**
	 * 设置排班日期
	 * 
	 * @param scheduleDate 排班日期
	 */
	public void setScheduleDate(String scheduleDate) {
		this.scheduleDate = scheduleDate;
	}

	/**
	 * 取得排班日期YYYY
	 * 
	 * @return scheduleDateY 排班日期YYYY
	 */
	@Column(name = "SCHEDULE_DATE_Y", length = 4)
	public String getScheduleDateY() {
		return scheduleDateY;
	}

	/**
	 * 设置排班日期YYYY
	 * 
	 * @param scheduleDateY 排班日期YYYY
	 */
	public void setScheduleDateY(String scheduleDateY) {
		this.scheduleDateY = scheduleDateY;
	}

	/**
	 * 取得排班日期MM
	 * 
	 * @return scheduleDateM 排班日期MM
	 */
	@Column(name = "SCHEDULE_DATE_M", length = 2)
	public String getScheduleDateM() {
		return scheduleDateM;
	}

	/**
	 * 设置排班日期MM
	 * 
	 * @param scheduleDateM 排班日期MM
	 */
	public void setScheduleDateM(String scheduleDateM) {
		this.scheduleDateM = scheduleDateM;
	}

	/**
	 * 取得排班日期YYYYMM
	 * 
	 * @return scheduleDateYM 排班日期YYYYMM
	 */
	@Column(name = "SCHEDULE_DATE_Y_M", length = 6)
	public String getScheduleDateYM() {
		return scheduleDateYM;
	}

	/**
	 * 设置排班日期YYYYMM
	 * 
	 * @param scheduleDateYM 排班日期YYYYMM
	 */
	public void setScheduleDateYM(String scheduleDateYM) {
		this.scheduleDateYM = scheduleDateYM;
	}

	/**
	 * 取得排班日期
	 * 
	 * @return scheduleShow 排班日期
	 */
	@Column(length = 10)
	public String getScheduleShow() {
		return scheduleShow;
	}

	/**
	 * 设置排班日期
	 * 
	 * @param scheduleShow 排班日期
	 */
	public void setScheduleShow(String scheduleShow) {
		this.scheduleShow = scheduleShow;
	}

	/**
	 * 取得上班时间HH:mm
	 * 
	 * @return startDate 上班时间HH:mm
	 */
	@Column(length = 5)
	public String getStartDate() {
		return startDate;
	}

	/**
	 * 设置上班时间HH:mm
	 * 
	 * @param startDate 上班时间HH:mm
	 */
	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}

	/**
	 * 取得下班时间HH:mm
	 * 
	 * @return endDate 下班时间HH:mm
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
	 * 取得工作时间HH:mm-HH:mm
	 * 
	 * @return workDate 工作时间HH:mm-HH:mm
	 */
	@Column(length = 16)
	public String getWorkDate() {
		return workDate;
	}

	/**
	 * 设置工作时间HH:mm-HH:mm
	 * 
	 * @param workDate 工作时间HH:mm-HH:mm
	 */
	public void setWorkDate(String workDate) {
		this.workDate = workDate;
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
	 * 取得上班类型Uuid
	 * 
	 * @return workTypeUuid 上班类型Uuid
	 */
	public Integer getWorkTypeUuid() {
		return workTypeUuid;
	}

	/**
	 * 设置上班类型Uuid
	 * 
	 * @param workTypeUuid 上班类型Uuid
	 */
	public void setWorkTypeUuid(Integer workTypeUuid) {
		this.workTypeUuid = workTypeUuid;
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
