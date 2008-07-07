package cn.hb.entity.hr.organization;

import cn.hb.core.bean.AbstractEntityBean;
import cn.hb.entity.dictionary.organization.Organization_JobPosition_RelateType;

/** 组织-职务（关联） */
public class Organization_JobPosition_Relate {
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

}
