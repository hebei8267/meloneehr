package cn.hb.entity.dictionary.organization;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

import cn.hb.entity.dictionary.MasterEntityBean;

/**
 * @author kaka
 * 
 * 组织-职务关联类型(排斥_非排斥-关联)
 */
@Entity
@DiscriminatorValue(value = "Organization_JobPosition_RelateType")
public class Organization_JobPosition_RelateType extends MasterEntityBean {

    private static final long serialVersionUID = -4245036351235403763L;

    public Organization_JobPosition_RelateType() {

    }
}
