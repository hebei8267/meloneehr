package cn.hb.dao.dictionary.organization;

import org.springframework.stereotype.Component;
import cn.hb.core.dao.impl.HibernateDaoImpl;
import cn.hb.entity.dictionary.organization.OrganizationType;

/**
 * 组织类型Dao
 */
@Component("organizationTypeDao")
public class OrganizationTypeDao extends HibernateDaoImpl<OrganizationType> {
}
