package cn.hb.dao.dictionary.communal;

import static constant.TestConstant.ROOT_PATH;

import java.io.IOException;
import java.util.List;

import cn.hb.core.test.dao.HibernateDaoTestCase;
import cn.hb.entity.dictionary.communal.Country;
import cn.hb.dao.dictionary.communal.CountryDao;
import file.CSVUtils;

/**
 * 国家 DaoTest
 */
public class CountryDaoTest extends HibernateDaoTestCase {
    private CountryDao countryDao;

    public CountryDao getCountryDao() {
        return countryDao;
    }

    public void setCountryDao(CountryDao countryDao) {
        this.countryDao = countryDao;
    }

    public void testCase() throws IOException {
        List<List<String>> csvFileContent = CSVUtils.readCSVFile(ROOT_PATH + "Country.csv");

        for (List<String> fileLine : csvFileContent) {
            Country country = new Country();
            for (int i = 0; i < fileLine.size(); i++) {

                String value = fileLine.get(i);
                if (i == 0) {
                    country.setId(value);
                } else if (i == 1) {
                    country.setNote(value);
                } else if (i == 2) {
                    country.setName(value);
                } else if (i == 3) {
                    country.setShortName(value);
                }
            }
            countryDao.save(country);
        }
    }

}
