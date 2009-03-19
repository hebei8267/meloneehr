/*
 * Copyright 2009 by hebei, All rights reserved.
 */
package org.freedom.dao.dictionary.common;

import java.util.List;

import org.freedom.core.test.BaseTestCase;
import org.freedom.entity.dictionary.common.Country;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author 何贝
 * @since JDK1.5
 */
public class CountryDaoTest extends BaseTestCase {
    @Autowired
    private CountryDao countryDao;

    public CountryDao getCountryDao() {
        return countryDao;
    }

    public void setCountryDao(CountryDao countryDao) {
        this.countryDao = countryDao;
    }

    public void testCase1() {
        List<Country> cList = countryDao.getAll();
        for (Country country : cList) {
            System.out.println("testCase1");
            System.out.println("country: " + country);
        }
        assertNotNull(cList);
    }

    public void testCase2() {
        Country country = countryDao.getCountryByID("CN");
        System.out.println("country: " + country);
        assertNotNull(country);
    }
}
