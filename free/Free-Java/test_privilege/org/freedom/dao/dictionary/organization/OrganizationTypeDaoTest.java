/*
 * Copyright 2009 by hebei, All rights reserved.
 */
package org.freedom.dao.dictionary.organization;

import static org.freedom.dao.DaoConstant.ROOT_PATH;

import java.io.IOException;
import java.util.List;

import org.freedom.core.test.BaseTestCase;
import org.freedom.entity.dictionary.organization.OrganizationType;
import org.freedom.file.CSVFileUtils;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author 何贝
 * @since JDK1.5
 */
public class OrganizationTypeDaoTest extends BaseTestCase {
    @Autowired
    private OrganizationTypeDao organizationTypeDao;

    public OrganizationTypeDao getOrganizationTypeDao() {
        return organizationTypeDao;
    }

    public void setOrganizationTypeDao(OrganizationTypeDao organizationTypeDao) {
        this.organizationTypeDao = organizationTypeDao;
    }

    @Test
    public void case1() throws IOException {
        List<List<String>> csvFileContent = CSVFileUtils.readCSVFile(ROOT_PATH + "OrganizationType.csv");

        for (List<String> fileLine : csvFileContent) {

            OrganizationType organizationType = new OrganizationType();

            for (int i = 0; i < fileLine.size(); i++) {

                String value = fileLine.get(i);
                if (i == 0) {
                    organizationType.setId(value);
                } else if (i == 1) {
                    organizationType.setName(value);
                }
            }
            organizationTypeDao.save(organizationType);
        }
    }
}
