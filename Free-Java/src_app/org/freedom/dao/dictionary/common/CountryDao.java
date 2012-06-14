/*
 * Copyright 2009 by hebei, All rights reserved.
 */
package org.freedom.dao.dictionary.common;

import java.util.List;

import org.freedom.core.orm.hibernate.BaseHibernateDao;
import org.freedom.entity.dictionary.common.Country;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Order;
import org.springframework.stereotype.Repository;

/**
 * 国家Dao
 * 
 * @author 何贝
 * @since JDK1.5
 */
@Repository("countryDao")
@SuppressWarnings("unchecked")
public class CountryDao extends BaseHibernateDao<Country> {
    @Override
    public List<Country> getAll() {
        return findByCriteria(DetachedCriteria.forClass(Country.class).addOrder(Order.asc("id")));
    }

    /**
     * 根据国家ID取得国家信息
     * 
     * @param countryID 国家ID
     * @return Country 国家
     */
    public Country getCountryByID(String countryID) {
        return (Country) findUniqueByNamedQuery("Country.getCountryByID", countryID);
    }
}
