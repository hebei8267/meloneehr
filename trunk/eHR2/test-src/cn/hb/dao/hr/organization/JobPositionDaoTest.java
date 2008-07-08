package cn.hb.dao.hr.organization;

import cn.hb.core.test.dao.HibernateDaoTestCase;
import cn.hb.entity.hr.organization.JobPosition;
import cn.hb.dao.hr.organization.JobPositionDao;

/**
 * 职务 DaoTest
 */
public class JobPositionDaoTest extends HibernateDaoTestCase {
    private JobPositionDao jobPositionDao;

    public JobPositionDao getJobPositionDao() {
        return jobPositionDao;
    }

    public void setJobPositionDao(JobPositionDao jobPositionDao) {
        this.jobPositionDao = jobPositionDao;
    }

    public void testCase() {
        JobPosition jobPosition = new JobPosition();
    }

}
