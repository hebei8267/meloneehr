package cn.hb.dao.dictionary.organization;

import org.springframework.stereotype.Component;
import cn.hb.core.dao.impl.HibernateDaoImpl;
import cn.hb.entity.dictionary.organization.Organization_JobPosition_RelateType;

/**
 * 组织-职务关联类型(排斥_非排斥-关联)Dao
 */
@Component("organization_JobPosition_RelateTypeDao")
public class Organization_JobPosition_RelateTypeDao extends HibernateDaoImpl<Organization_JobPosition_RelateType> {
}
