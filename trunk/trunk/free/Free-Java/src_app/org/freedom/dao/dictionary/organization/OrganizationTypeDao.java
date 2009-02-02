/*
 * Copyright 2009 by hebei, All rights reserved.
 */
package org.freedom.dao.dictionary.organization;

import java.util.List;

import org.freedom.core.dao.impl.HibernateDaoImpl;
import org.freedom.entity.dictionary.organization.OrganizationType;
import org.springframework.stereotype.Component;

/**
 * 组织类型Dao
 * 
 * @author 何贝
 * @since JDK1.5
 */
@Component("organizationTypeDao")
public class OrganizationTypeDao extends HibernateDaoImpl<OrganizationType> {
    /**
     * 根据组织类型ID取得组织类型
     * 
     * @param organizationTypeID 组织类型ID
     * @return OrganizationType 组织类型
     */
    @SuppressWarnings("unchecked")
    public OrganizationType getOrganizationTypeByID(String orgTypeID) {
        List<OrganizationType> resultList = getHibernateTemplate().findByNamedQuery("OrganizationType.getOrganizationTypeByID",
                orgTypeID);
        if (resultList.size() > 0) {
            return resultList.get(0);
        }
        return null;
    }

    /**
     * 自动计算当前最大ID
     * 
     * @return 最大ID
     */
    @SuppressWarnings("unchecked")
    public String getMaxID() {
        List<String> resultList = getHibernateTemplate().findByNamedQuery("OrganizationType.getMaxID");
        String maxID = "";
        if (resultList.size() > 0) {
            maxID = resultList.get(0);
        }

        return formatMaxID(maxID);
    }
}
