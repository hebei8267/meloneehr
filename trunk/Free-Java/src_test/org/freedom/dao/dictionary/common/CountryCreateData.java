/*
 * Copyright 2009 by hebei, All rights reserved.
 */
package org.freedom.dao.dictionary.common;

import static org.freedom.dao.CreateDataConstant.ROOT_PATH;

import java.io.IOException;
import java.util.List;

import org.freedom.core.test.BaseTestCase;
import org.freedom.entity.dictionary.common.Country;
import org.freedom.file.CSVFileUtils;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author 何贝
 * @since JDK1.5
 */
public class CountryCreateData extends BaseTestCase {
    @Autowired
    private CountryDao countryDao;

    public CountryDao getCountryDao() {
        return countryDao;
    }

    public void setCountryDao(CountryDao countryDao) {
        this.countryDao = countryDao;
    }

    public void testCase1() throws IOException {
        List<List<String>> csvFileContent = CSVFileUtils.readCSVFile(ROOT_PATH + "Country.csv");

        for (List<String> fileLine : csvFileContent) {

            Country country = new Country();

            for (int i = 0; i < fileLine.size(); i++) {

                String value = fileLine.get(i);
                if (i == 0) {
                    country.setId(value);
                } else if (i == 1) {
                    country.setName(value);
                } else if (i == 2) {
                    country.setDetail(value);
                }
            }
            countryDao.save(country);
        }
    }
}
