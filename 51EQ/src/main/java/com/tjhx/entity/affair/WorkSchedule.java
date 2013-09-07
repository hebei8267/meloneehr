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
	/** 排班日期 */
	private String scheduleShow;
	/** 上班时间 */
	private String startDate;
	/** 上班时间 HH:mm */
	private String _startDate;
	/** 下班时间 */
	private String endDate;
	/** 下班时间 HH:mm */
	private String _endDate;
	/** 工作时间 HH:mm - HH:mm */
	private String workDate;
	/** 用户关联机构 */
	private Organization organization;
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
	 * 取得上班时间
	 * 
	 * @return startDate 上班时间
	 */
	@Column(length = 4)
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
	@Column(length = 4)
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
	 * 取得上班时间HH:mm
	 * 
	 * @return _startDate 上班时间HH:mm
	 */
	@Column(length = 5)
	public String get_startDate() {
		return _startDate;
	}

	/**
	 * 设置上班时间HH:mm
	 * 
	 * @param _startDate 上班时间HH:mm
	 */
	public void set_startDate(String _startDate) {
		this._startDate = _startDate;
	}

	/**
	 * 取得下班时间HH:mm
	 * 
	 * @return _endDate 下班时间HH:mm
	 */
	@Column(length = 5)
	public String get_endDate() {
		return _endDate;
	}

	/**
	 * 设置下班时间HH:mm
	 * 
	 * @param _endDate 下班时间HH:mm
	 */
	public void set_endDate(String _endDate) {
		this._endDate = _endDate;
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
