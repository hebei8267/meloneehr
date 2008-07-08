package cn.hb.entity.hr.organization;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import cn.hb.core.bean.AbstractEntityBean;
import cn.hb.entity.dictionary.organization.Organization_JobPosition_RelateType;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.hibernate.annotations.NaturalId;

/**
 * @author kaka
 * 
 * 组织-职务（关联）
 */
@Entity
@Table(name = "W_ORGANIZATION_JOB_POSITION_RELATE")
public class Organization_JobPosition_Relate extends AbstractEntityBean {

    private static final long serialVersionUID = -8187611671009451918L;

    public Organization_JobPosition_Relate() {
    }

    /** 组织编号 */
    private String organizationID;

    /** 职务编号 */
    private String jobPositionID;

    /** 组织 */
    private Organization organization;

    /** 职务 */
    private JobPosition jobPosition;

    /** 组织-职务关联类型(排斥_非排斥-关联)ID */
    private String organization_JobPosition_RelateTypeID;

    /** 组织-职务关联类型(排斥_非排斥-关联) */
    private Organization_JobPosition_RelateType organization_JobPosition_RelateType;

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
     * 取得组织
     * 
     * @return 组织
     */
    @ManyToOne
    @JoinColumn(name = "ORGANIZATION_H_ID")
    public Organization getOrganization() {
        return organization;
    }

    /**
     * 取得职务
     * 
     * @return 职务
     */
    @ManyToOne
    @JoinColumn(name = "JOB_POSITION_H_ID")
    public JobPosition getJobPosition() {
        return jobPosition;
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
     * 设置组织编号
     * 
     * @param organizationID 组织编号
     */
    protected void setOrganizationID(String organizationID) {
        this.organizationID = organizationID;
    }

    /**
     * 设置职务编号
     * 
     * @param jobPositionID 职务编号
     */
    protected void setJobPositionID(String jobPositionID) {
        this.jobPositionID = jobPositionID;
    }

    /**
     * 设置组织
     * 
     * @param organization 组织
     */
    public void setOrganization(Organization organization) {
        if (organization != null) {
            this.organizationID = organization.getId();
        }
        this.organization = organization;
    }

    /**
     * 设置职务
     * 
     * @param jobPosition 职务
     */
    public void setJobPosition(JobPosition jobPosition) {
        if (jobPosition != null) {
            this.jobPositionID = jobPosition.getId();
        }
        this.jobPosition = jobPosition;
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
        if (!(object instanceof Organization_JobPosition_Relate)) {
            return false;
        }
        Organization_JobPosition_Relate rhs = (Organization_JobPosition_Relate) object;
        return new EqualsBuilder().append(this.organizationID, rhs.organizationID).append(this.jobPositionID,
                rhs.jobPositionID).isEquals();
    }

    /**
     * @see java.lang.Object#hashCode()
     */
    @Override
    public int hashCode() {
        return new HashCodeBuilder(-1181889567, 963220387).append(this.organizationID).append(this.organizationID)
                .append(this.jobPositionID).toHashCode();
    }

}
