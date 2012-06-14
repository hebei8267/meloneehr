/*
 * Copyright 2009 by hebei, All rights reserved.
 */
package org.freedom.dao;

import org.freedom.dao.common.RoleDaoTest;
import org.freedom.dao.common.UserDaoTest;
import org.freedom.dao.dictionary.common.CountryDaoTest;
import org.freedom.dao.dictionary.organization.OrganizationTypeDaoTest;
import org.freedom.dao.organization.OrganizationDaoTest;
import org.freedom.dao.ui.MenuNodeDaoTest;
import org.freedom.dao.ui.MenuNodePermitDaoTest;
import org.freedom.dao.ui.MenuNodeTypeDaoTest;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

/**
 * @author 何贝
 * @since JDK1.5
 */
@RunWith(Suite.class)
@Suite.SuiteClasses( { RoleDaoTest.class,
                       UserDaoTest.class,
                       CountryDaoTest.class,
                       OrganizationTypeDaoTest.class,
                       OrganizationDaoTest.class,
                       MenuNodeDaoTest.class,
                       MenuNodePermitDaoTest.class,
                       MenuNodeTypeDaoTest.class})
public class AllDaoTest {

}
