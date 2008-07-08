package cn.hb.dao.hr.organization;

import cn.hb.core.test.dao.HibernateDaoTestCase;
import cn.hb.entity.hr.organization.Organization;
import cn.hb.dao.hr.organization.OrganizationDao;

/**
 * 组织 DaoTest
 */
public class OrganizationDaoTest extends HibernateDaoTestCase {
    private OrganizationDao organizationDao;

    public OrganizationDao getOrganizationDao() {
        return organizationDao;
    }

    public void setOrganizationDao(OrganizationDao organizationDao) {
        this.organizationDao = organizationDao;
    }

    public void testCase() {
        Organization organization = new Organization();
    }

}
