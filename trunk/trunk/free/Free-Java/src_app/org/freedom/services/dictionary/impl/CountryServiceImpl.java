/*
 * Copyright 2009 by hebei, All rights reserved.
 */
package org.freedom.services.dictionary.impl;

import java.util.List;

import org.apache.commons.lang.StringUtils;
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

    public boolean updateCountryInfoService(Country country) {

        if (StringUtils.isNotBlank(country.getId()) && StringUtils.isNotBlank(country.getName())) {
            Country dbCountry = countryDao.getCountryByID(country.getId());

            if (dbCountry != null) {
                if (dbCountry.getVersion().equals(country.getVersion())) {

                    dbCountry.setName(country.getName());
                    dbCountry.setDetail(country.getDetail());

                    countryDao.save(dbCountry);
                    return true;
                }
            }
        }
        return false;
    }

    public int delCountryInfoService(String countryID, int dataVersion) {
        Country dbCountry = countryDao.getCountryByID(countryID);
        if (dbCountry == null || !dbCountry.getVersion().equals(dataVersion)) {
            return 1;
        }
        try {

        } catch (Exception e) {
            // TODO: 外键关联未实现
        }
        // 删除国家信息
        countryDao.delete(dbCountry);
        return 0;
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
