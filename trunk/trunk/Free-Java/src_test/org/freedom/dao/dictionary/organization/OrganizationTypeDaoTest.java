/*
 * Copyright 2009 by hebei, All rights reserved.
 */
package org.freedom.dao.dictionary.organization;

import org.freedom.core.test.BaseTestCase;
import org.freedom.entity.dictionary.organization.OrganizationType;
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

    public void testCase1() {
        OrganizationType type = organizationTypeDao.getOrganizationTypeByID("00000002");
        System.out.println("OrganizationType: " + type);
        assertNotNull(type);
    }

    public void testCase2() {
        String maxID = organizationTypeDao.getMaxID();
        System.out.println("maxID: " + maxID);
        assertNotNull(maxID);
    }
}
