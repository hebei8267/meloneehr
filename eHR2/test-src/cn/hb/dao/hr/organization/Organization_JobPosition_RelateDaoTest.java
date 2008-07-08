package cn.hb.dao.hr.organization;

import cn.hb.core.test.dao.HibernateDaoTestCase;
import cn.hb.entity.hr.organization.Organization_JobPosition_Relate;
import cn.hb.dao.hr.organization.Organization_JobPosition_RelateDao;

/**
 * 组织-职务（关联） DaoTest
 */
public class Organization_JobPosition_RelateDaoTest extends HibernateDaoTestCase {
    private Organization_JobPosition_RelateDao organization_JobPosition_RelateDao;

    public Organization_JobPosition_RelateDao getOrganization_JobPosition_RelateDao() {
        return organization_JobPosition_RelateDao;
    }

    public void setOrganization_JobPosition_RelateDao(
            Organization_JobPosition_RelateDao organization_JobPosition_RelateDao) {
        this.organization_JobPosition_RelateDao = organization_JobPosition_RelateDao;
    }

    public void testCase() {
        Organization_JobPosition_Relate organization_JobPosition_Relate = new Organization_JobPosition_Relate();
    }

}
