/*
 * Copyright 2008 by hebei, All rights reserved.
 */
package test.org.freedom.dao;

import org.freedom.core.test.dao.HibernateDaoTestCase;

import test.org.freedom.dao.security.RoleDaoTest;
import test.org.freedom.dao.security.UserDaoTest;
import test.org.freedom.dao.ui.MenuNodeDaoTest;
import test.org.freedom.dao.ui.MenuNodePermitDaoTest;
import test.org.freedom.dao.ui.MenuNodeTypeDaoTest;

import junit.framework.Test;
import junit.framework.TestSuite;

/**
 * AllDaoTest Main
 * 
 * @author 何贝
 * @since JDK1.5
 */
public class AllDaoTest extends HibernateDaoTestCase {
    public static Test suite() {
        TestSuite suite = new TestSuite();
        suite.addTestSuite(CreateTableTest.class);

        suite.addTestSuite(RoleDaoTest.class);
        suite.addTestSuite(UserDaoTest.class);
        suite.addTestSuite(MenuNodeDaoTest.class);
        suite.addTestSuite(MenuNodeTypeDaoTest.class);
        suite.addTestSuite(MenuNodePermitDaoTest.class);
        return suite;
    }
}
