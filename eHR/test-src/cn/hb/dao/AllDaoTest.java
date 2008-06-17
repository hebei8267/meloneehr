package cn.hb.dao;

import cn.hb.core.test.dao.HibernateDaoTestCase;
import cn.hb.dao.common.CountryDaoTest;
import cn.hb.dao.common.NationDaoTest;
import cn.hb.dao.common.NativeplaceDaoTest;
import cn.hb.dao.hr.organization.OrganizationTypeDaoTest;
import cn.hb.dao.hr.personnel.CardKindDaoTest;
import cn.hb.dao.hr.personnel.MarriageStateDaoTest;
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

        suite.addTestSuite(OrganizationTypeDaoTest.class);

        suite.addTestSuite(MarriageStateDaoTest.class);
        suite.addTestSuite(CardKindDaoTest.class);

        suite.addTestSuite(UserDaoTest.class);
        // suite.addTestSuite(NativePlaceDaoTest.class);
        //
        // suite.addTestSuite(OrganizationDaoTest.class);
        // suite.addTestSuite(JobLevelDaoTest.class);
        // suite.addTestSuite(OrganizationJobLevelDaoTest.class);
        //
        // suite.addTestSuite(CultureClassDaoTest.class);

        suite.addTestSuite(MenuNodeDaoTest.class);
        return suite;
    }
}
