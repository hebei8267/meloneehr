/*
 * Copyright 2009 by hebei, All rights reserved.
 */
package org.freedom.dao.organization;

import org.freedom.core.test.BaseTestCase;
import org.freedom.entity.organization.Organization;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author 何贝
 * @since JDK1.5
 */
public class OrganizationDaoTest extends BaseTestCase {
    @Autowired
    private OrganizationDao organizationDao;

    public OrganizationDao getOrganizationDao() {
        return organizationDao;
    }

    public void setOrganizationDao(OrganizationDao organizationDao) {
        this.organizationDao = organizationDao;
    }

    public void testCase1() {
        Organization organization = organizationDao.getOrganizationByID("00000001");
        System.out.println("organization: " + organization);
        assertNotNull(organization);
    }
}
