package cn.hb.entity.hr.organization;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import org.hibernate.annotations.NaturalId;

import cn.hb.core.bean.AbstractEntityBean;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;

/**
 * @author kaka
 * 
 * 职位
 */
@Entity
@Table(name = "W_JOB")
public class Job extends AbstractEntityBean {

    private static final long serialVersionUID = 8007085956734624977L;

    public Job() {
    }

    /** 组织编号 */
    private String organizationID;

    /** 职务编号 */
    private String jobPositionID;

    /** 设立时间 */
    private String startDate;

    /** 名称 */
    private String name;

    /** 编制人数 */
    private Integer assignmentSize;

    /** 详细描述 */
    private String note;

    /** 是否允许超编 */
    private Boolean allowExceed;

    /** 撤销时间 */
    private String endDate;

    /** 撤销原因 */
    private String endNote;

    /** 是否为该组织的负责人 */
    private Boolean isManager;

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
     * 取得设立时间
     * 
     * @return 设立时间
     */
    @NaturalId
    @Column(name = "START_DATE", nullable = false, length = 8)
    public String getStartDate() {
        return startDate;
    }

    /**
     * 取得名称
     * 
     * @return 名称
     */
    @Basic
    @Column(name = "NAME", nullable = false, length = 20)
    public String getName() {
        return name;
    }

    /**
     * 取得编制人数
     * 
     * @return 编制人数
     */
    @Basic
    @Column(name = "ASSIGNMENT_SIZE", nullable = false)
    public Integer getAssignmentSize() {
        return assignmentSize;
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
     * 取得是否允许超编
     * 
     * @return 是否允许超编
     */
    @Basic
    @Column(name = "ALLOW_EXCEED", nullable = false)
    public Boolean getAllowExceed() {
        return allowExceed;
    }

    /**
     * 取得撤销时间
     * 
     * @return 撤销时间
     */
    @Basic
    @Column(name = "END_DATE", nullable = false, length = 8)
    public String getEndDate() {
        return endDate;
    }

    /**
     * 取得撤销原因
     * 
     * @return 撤销原因
     */
    @Basic
    @Column(name = "END_NOTE")
    public String getEndNote() {
        return endNote;
    }

    /**
     * 取得是否为该组织的负责人
     * 
     * @return 是否为该组织的负责人
     */
    @Basic
    @Column(name = "IS_MANAGER", nullable = false)
    public Boolean getIsManager() {
        return isManager;
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
     * 设置设立时间
     * 
     * @param startDate 设立时间
     */
    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    /**
     * 设置名称
     * 
     * @param name 名称
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * 设置编制人数
     * 
     * @param assignmentSize 编制人数
     */
    public void setAssignmentSize(Integer assignmentSize) {
        this.assignmentSize = assignmentSize;
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
     * 设置是否允许超编
     * 
     * @param allowExceed 是否允许超编
     */
    public void setAllowExceed(Boolean allowExceed) {
        this.allowExceed = allowExceed;
    }

    /**
     * 设置撤销时间
     * 
     * @param endDate 撤销时间
     */
    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    /**
     * 设置撤销原因
     * 
     * @param endNote 撤销原因
     */
    public void setEndNote(String endNote) {
        this.endNote = endNote;
    }

    /**
     * 设置是否为该组织的负责人
     * 
     * @param isManager 是否为该组织的负责人
     */

    public void setIsManager(Boolean isManager) {
        this.isManager = isManager;
    }

    /**
     * @see java.lang.Object#equals(Object)
     */
    @Override
    public boolean equals(Object object) {
        if (!(object instanceof Job)) {
            return false;
        }
        Job rhs = (Job) object;
        return new EqualsBuilder().append(this.startDate, rhs.startDate)
                .append(this.organizationID, rhs.organizationID).isEquals();
    }

    /**
     * @see java.lang.Object#hashCode()
     */
    @Override
    public int hashCode() {
        return new HashCodeBuilder(946557585, -839632819).append(this.startDate).append(this.organizationID).append(
                this.jobPositionID).toHashCode();
    }

}
