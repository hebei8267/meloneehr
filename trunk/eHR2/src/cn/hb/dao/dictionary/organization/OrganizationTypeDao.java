package cn.hb.dao.dictionary.organization;

import java.util.List;

import org.springframework.stereotype.Component;
import cn.hb.core.dao.impl.HibernateDaoImpl;
import cn.hb.entity.dictionary.organization.OrganizationType;

/**
 * 组织类型Dao
 */
@Component("organizationTypeDao")
public class OrganizationTypeDao extends HibernateDaoImpl<OrganizationType> {
    /**
     * 根据组织类型ID取得组织类型信息
     * 
     * @param organizationTypeID 组织类型ID
     * @return 组织类型
     */
    @SuppressWarnings("unchecked")
    public OrganizationType getOrganizationTypeByID(String organizationTypeID) {
        List<OrganizationType> resultList = getHibernateTemplate().findByNamedQuery(
                "OrganizationType.getOrganizationTypeByID", organizationTypeID);
        if (resultList.size() > 0) {
            return resultList.get(0);
        }
        return null;
    }
}
