package cn.hb.dao;

import cn.hb.core.test.dao.HibernateDaoTestCase;
import cn.hb.dao.dictionary.communal.CountryDaoTest;
import cn.hb.dao.dictionary.communal.NationDaoTest;
import cn.hb.dao.dictionary.communal.NativeplaceDaoTest;
import cn.hb.dao.dictionary.organization.JobPositionTypeDaoTest;
import cn.hb.dao.dictionary.organization.OrganizationTypeDaoTest;
import cn.hb.dao.security.UserDaoTest;
import cn.hb.dao.ui.MenuNodeDaoTest;
import junit.framework.Test;
import junit.framework.TestSuite;

/**
 * 
 * @author 何 貝
 * 
 */
public class AllDaoTest extends HibernateDaoTestCase {
    public static Test suite() {
        TestSuite suite = new TestSuite();
        suite.addTestSuite(CreateTableTest.class);

        suite.addTestSuite(CountryDaoTest.class);
        suite.addTestSuite(NationDaoTest.class);
        suite.addTestSuite(NativeplaceDaoTest.class);

        suite.addTestSuite(JobPositionTypeDaoTest.class);
        suite.addTestSuite(OrganizationTypeDaoTest.class);

        suite.addTestSuite(UserDaoTest.class);

        suite.addTestSuite(MenuNodeDaoTest.class);
        return suite;
    }
}
