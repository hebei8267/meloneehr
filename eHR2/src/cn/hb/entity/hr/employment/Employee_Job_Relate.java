package cn.hb.entity.hr.employment;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import cn.hb.core.bean.AbstractEntityBean;
import cn.hb.entity.dictionary.employment.HoldingJobType;
import cn.hb.entity.hr.organization.Job;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.hibernate.annotations.NaturalId;

/**
 * @author kaka
 * 
 * 员工-职位
 */
@Entity
@Table(name = "W_EMPLOYEE_JOB_RELATE")
public class Employee_Job_Relate extends AbstractEntityBean {

    private static final long serialVersionUID = -1584690986110244463L;

    public Employee_Job_Relate() {

    }

    /** 员工编号 */
    private String employeeID;

    /** 组织编号 */
    private String organizationID;

    /** 职务编号 */
    private String jobPositionID;

    /** 任职开始时间 */
    private String startDate;

    /** 任职结束时间 */
    private String endDate;

    /** 详细描述 */
    private String note;

    /** 员工 */
    private Employee employee;

    /** 职位 */
    private Job Job;

    /** 任职类型ID */
    private String holdingJobTypeID;

    /** 任职类型 */
    private HoldingJobType holdingJobType;

    /**
     * 取得员工编号
     * 
     * @return 员工编号
     */
    @NaturalId
    @Column(name = "EMPLOYEE_ID", nullable = false, length = 20)
    public String getEmployeeID() {
        return employeeID;
    }

    /**
     * 取得组织编号
     * 
     * @return 组织编号
     */
    @NaturalId
    @Column(name = "ORGANIZATION_ID", nullable = false, length = 20)
    public String getOrganizationID() {
        return organizationID;
    }

    /**
     * 取得职务编号
     * 
     * @return 职务编号
     */
    @NaturalId
    @Column(name = "JOB_POSITION_ID", nullable = false, length = 20)
    public String getJobPositionID() {
        return jobPositionID;
    }

    /**
     * 取得任职开始时间
     * 
     * @return 任职开始时间
     */
    @NaturalId
    @Column(name = "START_DATE", nullable = false, length = 8)
    public String getStartDate() {
        return startDate;
    }

    /**
     * 取得任职结束时间
     * 
     * @return 任职结束时间
     */
    @Basic
    @Column(name = "END_DATE", nullable = false, length = 8)
    public String getEndDate() {
        return endDate;
    }

    /**
     * 取得详细描述
     * 
     * @return 详细描述
     */
    @Basic
    @Column(name = "NOTE")
    public String getNote() {
        return note;
    }

    /**
     * 取得员工
     * 
     * @return 员工
     */
    @ManyToOne
    @JoinColumn(name = "EMPLOYEE_H_ID")
    public Employee getEmployee() {
        return employee;
    }

    /**
     * 取得职位
     * 
     * @return 职位
     */
    @ManyToOne
    @JoinColumn(name = "JOB_H_ID")
    public Job getJob() {
        return Job;
    }

    /**
     * 取得任职类型ID
     * 
     * @return 任职类型ID
     */
    @Basic
    @Column(name = "HOLDING_JOB_TYPE_ID", length = 20)
    public String getHoldingJobTypeID() {
        return holdingJobTypeID;
    }

    /**
     * 取得任职类型
     * 
     * @return 任职类型
     */
    @ManyToOne
    @JoinColumn(name = "HOLDING_JOB_TYPE_H_ID")
    public HoldingJobType getHoldingJobType() {
        return holdingJobType;
    }

    /**
     * 设置员工编号
     * 
     * @param employeeID 员工编号
     */
    public void setEmployeeID(String employeeID) {
        this.employeeID = employeeID;
    }

    /**
     * 设置组织编号
     * 
     * @param organizationID 组织编号
     */
    public void setOrganizationID(String organizationID) {
        this.organizationID = organizationID;
    }

    /**
     * 设置职务编号
     * 
     * @param jobPositionID 职务编号
     */
    public void setJobPositionID(String jobPositionID) {
        this.jobPositionID = jobPositionID;
    }

    /**
     * 设置任职开始时间
     * 
     * @param startDate 任职开始时间
     */
    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    /**
     * 设置任职结束时间
     * 
     * @param endDate 任职结束时间
     */
    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    /**
     * 设置详细描述
     * 
     * @param note 详细描述
     */
    public void setNote(String note) {
        this.note = note;
    }

    /**
     * 设置员工
     * 
     * @param employee 员工
     */
    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    /**
     * 设置职位
     * 
     * @param Job 职位
     */
    public void setJob(Job Job) {
        this.Job = Job;
    }

    /**
     * 设置任职类型ID
     * 
     * @param holdingJobTypeID 任职类型ID
     */
    public void setHoldingJobTypeID(String holdingJobTypeID) {
        this.holdingJobTypeID = holdingJobTypeID;
    }

    /**
     * 设置任职类型
     * 
     * @param holdingJobType 任职类型
     */
    public void setHoldingJobType(HoldingJobType holdingJobType) {
        this.holdingJobType = holdingJobType;
    }

    /**
     * @see java.lang.Object#equals(Object)
     */
    @Override
    public boolean equals(Object object) {
        if (!(object instanceof Employee_Job_Relate)) {
            return false;
        }
        Employee_Job_Relate rhs = (Employee_Job_Relate) object;
        return new EqualsBuilder().append(this.startDate, rhs.startDate).append(this.employeeID, rhs.employeeID)
                .append(this.organizationID, rhs.organizationID).append(this.jobPositionID, rhs.jobPositionID)
                .isEquals();
    }

    /**
     * @see java.lang.Object#hashCode()
     */
    @Override
    public int hashCode() {
        return new HashCodeBuilder(-49983659, 2495327).append(this.startDate).append(this.employeeID).append(
                this.organizationID).append(this.jobPositionID).toHashCode();
    }

}
