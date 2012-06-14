/*
 * Copyright 2008 by hebei, All rights reserved.
 */
package org.freedom.dao;

import org.freedom.core.test.BaseTestCase;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

import org.freedom.dao.common.RoleCreateData;
import org.freedom.dao.common.UserCreateData;
import org.freedom.dao.dictionary.common.CountryCreateData;
import org.freedom.dao.dictionary.organization.OrganizationTypeCreateData;
import org.freedom.dao.organization.OrganizationCreateData;
import org.freedom.dao.ui.MenuNodeCreateData;
import org.freedom.dao.ui.MenuNodePermitCreateData;
import org.freedom.dao.ui.MenuNodeTypeCreateData;

/**
 * AllDaoTest Main
 * 
 * @author 何贝
 * @since JDK1.5
 */
@RunWith(Suite.class)
@Suite.SuiteClasses( { CreateTableTest.class, 
                       RoleCreateData.class, 
                       UserCreateData.class, 
                       MenuNodeTypeCreateData.class,
                       MenuNodeCreateData.class,
                       MenuNodePermitCreateData.class,
                       CountryCreateData.class,
                       OrganizationTypeCreateData.class,
                       OrganizationCreateData.class,
                       })
public class CreateData extends BaseTestCase {

}
