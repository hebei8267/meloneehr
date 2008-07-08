package cn.hb.dao.hr.organization;

import org.springframework.stereotype.Component;
import cn.hb.core.dao.impl.HibernateDaoImpl;
import cn.hb.entity.hr.organization.Organization_JobPosition_Relate;

/**
 * 组织-职务（关联）Dao
 */
@Component("organization_JobPosition_RelateDao")
public class Organization_JobPosition_RelateDao extends HibernateDaoImpl<Organization_JobPosition_Relate> {
}
