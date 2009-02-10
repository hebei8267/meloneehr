package org.freedom.dao.organization;

import java.util.List;

import org.freedom.core.dao.impl.HibernateDaoImpl;
import org.freedom.entity.organization.Organization;
import org.springframework.stereotype.Component;

/**
 * 组织Dao
 */
@Component("organizationDao")
public class OrganizationDao extends HibernateDaoImpl<Organization> {

    /**
     * 根据组织ID取得组织信息
     * 
     * @param organizationID 组织ID
     * @return 组织信息
     */
    @SuppressWarnings("unchecked")
    public Organization getOrganizationByID(String organizationID) {
        List<Organization> resultList = getHibernateTemplate().findByNamedQuery("Organization.getOrganizationByID",
                organizationID);
        if (resultList.size() > 0) {
            return resultList.get(0);
        }
        return null;
    }
}
