package cn.hb.dao.hr.organization;

import static constant.TestConstant.ROOT_PATH;

import java.io.IOException;
import java.util.List;

import cn.hb.core.test.dao.HibernateDaoTestCase;
import cn.hb.entity.dictionary.communal.Country;
import cn.hb.entity.dictionary.organization.OrganizationType;
import cn.hb.entity.hr.organization.Organization;
import cn.hb.dao.dictionary.communal.CountryDao;
import cn.hb.dao.dictionary.organization.OrganizationTypeDao;
import cn.hb.dao.hr.organization.OrganizationDao;
import file.CSVUtils;

/**
 * 组织 DaoTest
 */
public class OrganizationDaoTest extends HibernateDaoTestCase {
    private OrganizationDao organizationDao;
    private OrganizationTypeDao organizationTypeDao;
    private CountryDao countryDao;

    public OrganizationDao getOrganizationDao() {
        return organizationDao;
    }

    public void setOrganizationDao(OrganizationDao organizationDao) {
        this.organizationDao = organizationDao;
    }

    public OrganizationTypeDao getOrganizationTypeDao() {
        return organizationTypeDao;
    }

    public void setOrganizationTypeDao(OrganizationTypeDao organizationTypeDao) {
        this.organizationTypeDao = organizationTypeDao;
    }

    public CountryDao getCountryDao() {
        return countryDao;
    }

    public void setCountryDao(CountryDao countryDao) {
        this.countryDao = countryDao;
    }

    public void testCase() throws IOException {
        List<List<String>> csvFileContent = CSVUtils.readCSVFile(ROOT_PATH + "Organization.csv");

        for (List<String> fileLine : csvFileContent) {
            Organization organization = new Organization();

            for (int i = 0; i < fileLine.size(); i++) {
                String value = fileLine.get(i);
                if (i == 0) {
                    organization.setId(value);
                } else if (i == 1) {
                    organization.setName(value);
                } else if (i == 2) {
                    organization.setStartDate(value);
                } else if (i == 3) {
                    organization.setEndDate(value);
                } else if (i == 4) {
                    Organization parentOrganization = organizationDao.getOrganizationByID(value);

                    parentOrganization.addSubOrganization(organization);

                    organization.setParentOrganization(parentOrganization);
                } else if (i == 5) {
                    organization.setAddress(value);
                } else if (i == 6) {
                    organization.setTelephone(value);
                } else if (i == 7) {
                    organization.setFax(value);
                } else if (i == 8) {
                    OrganizationType organizationType = organizationTypeDao.getOrganizationTypeByID(value);
                    organization.setOrganizationType(organizationType);
                } else if (i == 9) {
                    Country ountry = countryDao.getCountryByID(value);
                    organization.setLocalCountry(ountry);
                }
            }

            organizationDao.save(organization);
        }

    }
}
