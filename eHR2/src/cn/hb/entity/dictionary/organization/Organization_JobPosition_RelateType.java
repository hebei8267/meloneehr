package cn.hb.entity.dictionary.organization;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;

import cn.hb.entity.dictionary.MasterEntityBean;

/**
 * @author kaka
 * 
 * 组织-职务关联类型(排斥_非排斥-关联)
 */
@Entity
@DiscriminatorValue(value = "Organization_JobPosition_RelateType")
@NamedQueries( { @NamedQuery(name = "Organization_JobPosition_RelateType.getOrganization_JobPosition_RelateTypeByID", query = "select obj from Organization_JobPosition_RelateType obj where obj.masterID = ? And obj.slaveID = ? ") })
public class Organization_JobPosition_RelateType extends MasterEntityBean {

    private static final long serialVersionUID = -4245036351235403763L;

    public final static String MASTER_ID = "0001";

    public Organization_JobPosition_RelateType() {

    }
}
