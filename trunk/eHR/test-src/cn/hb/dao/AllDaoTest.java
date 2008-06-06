package cn.hb.dao;

import cn.hb.core.test.dao.HibernateDaoTestCase;
import cn.hb.dao.common.CountryDaoTest;
import cn.hb.dao.security.UserDaoTest;
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

        suite.addTestSuite(UserDaoTest.class);
        // suite.addTestSuite(NativePlaceDaoTest.class);
        //
        // suite.addTestSuite(OrganizationDaoTest.class);
        // suite.addTestSuite(JobLevelDaoTest.class);
        // suite.addTestSuite(OrganizationJobLevelDaoTest.class);
        //
        // suite.addTestSuite(CultureClassDaoTest.class);
        return suite;
    }
}
