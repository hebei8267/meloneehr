/*
 * Copyright 2009 by hebei, All rights reserved.
 */
package org.freedom.dao.dictionary.organization;

import org.freedom.core.orm.hibernate.BaseHibernateDao;
import org.freedom.entity.dictionary.organization.OrganizationType;
import org.springframework.stereotype.Repository;

/**
 * 组织类型Dao
 * 
 * @author 何贝
 * @since JDK1.5
 */
@Repository("organizationTypeDao")
public class OrganizationTypeDao extends BaseHibernateDao<OrganizationType> {
    /**
     * 根据组织类型ID取得组织类型
     * 
     * @param organizationTypeID 组织类型ID
     * @return OrganizationType 组织类型
     */
    public OrganizationType getOrganizationTypeByID(String orgTypeID) {
        return (OrganizationType) findUniqueByNamedQuery("OrganizationType.getOrganizationTypeByID", orgTypeID);
    }

    /**
     * 自动计算当前最大ID
     * 
     * @return 最大ID
     */
    public String getMaxID() {
        String maxID = (String) findUniqueByNamedQuery("OrganizationType.getMaxID");
        return formatMaxID(maxID);
    }
}
