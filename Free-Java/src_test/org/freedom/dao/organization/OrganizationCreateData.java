/*
 * Copyright 2009 by hebei, All rights reserved.
 */
package org.freedom.dao.organization;

import static org.freedom.dao.CreateDataConstant.ROOT_PATH;

import java.io.IOException;
import java.util.List;

import org.freedom.core.test.BaseTestCase;
import org.freedom.dao.dictionary.common.CountryDao;
import org.freedom.dao.dictionary.organization.OrganizationTypeDao;
import org.freedom.entity.dictionary.common.Country;
import org.freedom.entity.dictionary.organization.OrganizationType;
import org.freedom.entity.organization.Organization;
import org.freedom.file.CSVFileUtils;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author 何贝
 * @since JDK1.5
 */
public class OrganizationCreateData extends BaseTestCase {
    @Autowired
    private OrganizationDao organizationDao;
    @Autowired
    private OrganizationTypeDao organizationTypeDao;
    @Autowired
    private CountryDao countryDao;

    public OrganizationDao getOrganizationDao() {
        return organizationDao;
    }

    public void setOrganizationDao(OrganizationDao organizationDao) {
        this.organizationDao = organizationDao;
    }

    public void testCase1() throws IOException {
        List<List<String>> csvFileContent = CSVFileUtils.readCSVFile(ROOT_PATH + "Organization.csv");

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

                    parentOrganization.addChildOrganization(organization);

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
