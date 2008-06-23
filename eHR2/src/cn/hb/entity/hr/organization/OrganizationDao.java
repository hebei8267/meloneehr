package cn.hb.dao.hr.organization;

import org.springframework.stereotype.Component;
import cn.hb.core.dao.impl.HibernateDaoImpl;
import cn.hb.entity.hr.organization.Organization;

/**
 * 组织Dao
 */
@Component("organizationDao")
public class OrganizationDao extends HibernateDaoImpl<Organization> {
}
