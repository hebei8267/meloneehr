/*
 * Copyright 2008 by hebei, All rights reserved.
 */
package org.freedom.dao;

import org.freedom.core.test.BaseTestCase;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

import org.freedom.dao.common.UserDaoTest;
import org.freedom.dao.privilege.RoleDaoTest;

/**
 * AllDaoTest Main
 * 
 * @author 何贝
 * @since JDK1.5
 */
@RunWith(Suite.class)
@Suite.SuiteClasses( { CreateTableTest.class, RoleDaoTest.class, UserDaoTest.class })
public class AllDaoTest extends BaseTestCase {

}
