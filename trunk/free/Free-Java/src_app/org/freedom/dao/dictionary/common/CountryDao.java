/*
 * Copyright 2009 by hebei, All rights reserved.
 */
package org.freedom.dao.dictionary.common;

import java.util.List;

import org.freedom.core.dao.impl.HibernateDaoImpl;
import org.freedom.entity.dictionary.common.Country;
import org.springframework.stereotype.Component;

/**
 * 国家Dao
 * 
 * @author 何贝
 * @since JDK1.5
 */
@Component("countryDao")
public class CountryDao extends HibernateDaoImpl<Country> {
    /**
     * 根据国家ID取得国家信息
     * 
     * @param countryID 国家ID
     * @return Country 国家
     */
    @SuppressWarnings("unchecked")
    public Country getCountryByID(String countryID) {
        List<Country> resultList = getHibernateTemplate().findByNamedQuery("Country.getCountryByID", countryID);
        if (resultList.size() > 0) {
            return resultList.get(0);
        }
        return null;
    }
}
