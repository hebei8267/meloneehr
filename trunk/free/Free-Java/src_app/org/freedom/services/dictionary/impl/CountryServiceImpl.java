/*
 * Copyright 2009 by hebei, All rights reserved.
 */
package org.freedom.services.dictionary.impl;

import java.util.List;

import org.freedom.dao.dictionary.common.CountryDao;
import org.freedom.entity.dictionary.common.Country;
import org.freedom.services.dictionary.ICountryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 * 国家(数据字典)信息相关服务
 * 
 * @author 何贝
 * @since JDK1.5
 */
@Component("countryService")
@Scope("prototype")
public class CountryServiceImpl implements ICountryService {
    // ---------------------------------------------------------------------------
    // 接口实现
    // ---------------------------------------------------------------------------
    public List<Country> getAllCountryInfoListService() {
        return countryDao.getAll();
    }

    // ---------------------------------------------------------------------------
    // DAO
    // ---------------------------------------------------------------------------
    @Autowired
    private CountryDao countryDao;

    public CountryDao getCountryDao() {
        return countryDao;
    }

    public void setCountryDao(CountryDao countryDao) {
        this.countryDao = countryDao;
    }
}
