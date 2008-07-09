package cn.hb.entity.hr.organization;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import cn.hb.core.bean.AbstractEntityBean;
import cn.hb.entity.dictionary.organization.JobPositionType;
import cn.hb.entity.dictionary.organization.Organization_JobPosition_RelateType;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.hibernate.annotations.NaturalId;

/**
 * @author kaka
 * 
 * 职务
 */
@Entity
@Table(name = "W_JOB_POSITION")
public class JobPosition extends AbstractEntityBean {

    private static final long serialVersionUID = 3442796182575340021L;

    public JobPosition() {
    }

    /** 编号 */
    private String id;

    /** 名称 */
    private String name;

    /** 详细描述 */
    private String note;

    /** 职种(职务类型)ID */
    private String jobPositionTypeID;

    /** 职种(职务类型) */
    private JobPositionType jobPositionType;

    /** 组织-职务关联类型(排斥_非排斥-关联)ID */
    private String organization_JobPosition_RelateTypeID;

    /** 组织-职务关联类型(排斥_非排斥-关联) */
    private Organization_JobPosition_RelateType organization_JobPosition_RelateType;

    /**
     * 取得编号
     * 
     * @return 编号
     */
    @NaturalId
    @Column(name = "JOB_POSITION_ID", nullable = false, length = 20)
    public String getId() {
        return id;
    }

    /**
     * 取得名称
     * 
     * @return 名称
     */
    @Basic
    @Column(name = "NAME", nullable = false, unique = true, length = 20)
    public String getName() {
        return name;
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
     * 取得职种(职务类型)ID
     * 
     * @return 职种(职务类型)ID
     */
    @Basic
    @Column(name = "JOB_POSITION_TYPE_ID", nullable = false, length = 20)
    public String getJobPositionTypeID() {
        return jobPositionTypeID;
    }

    /**
     * 取得职种(职务类型)
     * 
     * @return 职种(职务类型)
     */
    @ManyToOne
    @JoinColumn(name = "JOB_POSITION_TYPE_H_ID")
    public JobPositionType getJobPositionType() {
        return jobPositionType;
    }

    /**
     * 取得组织-职务关联类型(排斥_非排斥-关联)ID
     * 
     * @return 组织-职务关联类型(排斥_非排斥-关联)ID
     */
    @Basic
    @Column(name = "ORGANIZATION_JOB_POSITION_RELATE_TYPE_ID", nullable = false, length = 20)
    public String getOrganization_JobPosition_RelateTypeID() {
        return organization_JobPosition_RelateTypeID;
    }

    /**
     * 取得组织-职务关联类型(排斥_非排斥-关联)
     * 
     * @return 组织-职务关联类型(排斥_非排斥-关联)
     */
    @ManyToOne
    @JoinColumn(name = "ORGANIZATION_JOB_POSITION_RELATE_TYPE_H_ID")
    public Organization_JobPosition_RelateType getOrganization_JobPosition_RelateType() {
        return organization_JobPosition_RelateType;
    }

    /**
     * 设置编号
     * 
     * @param id 编号
     */
    public void setId(String id) {
        this.id = id;
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
     * 设置详细描述
     * 
     * @param note 详细描述
     */
    public void setNote(String note) {
        this.note = note;
    }

    /**
     * 设置职种(职务类型)ID
     * 
     * @param jobPositionTypeID 职种(职务类型)ID
     */
    protected void setJobPositionTypeID(String jobPositionTypeID) {
        this.jobPositionTypeID = jobPositionTypeID;
    }

    /**
     * 设置职种(职务类型)
     * 
     * @param jobPositionType 职种(职务类型)
     */
    public void setJobPositionType(JobPositionType jobPositionType) {
        if (jobPositionType != null) {
            this.jobPositionTypeID = jobPositionType.getId();
        }
        this.jobPositionType = jobPositionType;
    }

    /**
     * 设置组织-职务关联类型(排斥_非排斥-关联)ID
     * 
     * @param organization_JobPosition_RelateTypeID 组织-职务关联类型(排斥_非排斥-关联)ID
     */
    protected void setOrganization_JobPosition_RelateTypeID(String organization_JobPosition_RelateTypeID) {
        this.organization_JobPosition_RelateTypeID = organization_JobPosition_RelateTypeID;
    }

    /**
     * 设置组织-职务关联类型(排斥_非排斥-关联)
     * 
     * @param organization_JobPosition_RelateType 组织-职务关联类型(排斥_非排斥-关联)
     */
    public void setOrganization_JobPosition_RelateType(
            Organization_JobPosition_RelateType organization_JobPosition_RelateType) {
        if (organization_JobPosition_RelateType != null) {
            this.organization_JobPosition_RelateTypeID = organization_JobPosition_RelateType.getMasterID()
                    + organization_JobPosition_RelateType.getSlaveID();
        }
        this.organization_JobPosition_RelateType = organization_JobPosition_RelateType;
    }

    /**
     * @see java.lang.Object#equals(Object)
     */
    @Override
    public boolean equals(Object object) {
        if (!(object instanceof JobPosition)) {
            return false;
        }
        JobPosition rhs = (JobPosition) object;
        return new EqualsBuilder().append(this.id, rhs.id).isEquals();
    }

    /**
     * @see java.lang.Object#hashCode()
     */
    @Override
    public int hashCode() {
        return new HashCodeBuilder(-1664116589, -423980045).append(this.id).toHashCode();
    }

}
