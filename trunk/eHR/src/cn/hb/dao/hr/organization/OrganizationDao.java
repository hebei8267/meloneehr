package cn.hb.dao.hr.organization;

import java.util.List;

import org.springframework.stereotype.Component;
import cn.hb.core.dao.impl.HibernateDaoImpl;
import cn.hb.entity.hr.organization.Organization;

/**
 * 组织Dao
 */
@Component("organizationDao")
public class OrganizationDao extends HibernateDaoImpl<Organization> {

    /**
     * 根据组织ID取得组织信息
     * 
     * @param organizationID 组织ID
     * @return
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

    public String getMaxOrganizationID() {
        // TODO Auto-generated method stub
        return null;
    }
}