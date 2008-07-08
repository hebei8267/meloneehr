package cn.hb.dao.hr.employment;

import cn.hb.core.test.dao.HibernateDaoTestCase;
import cn.hb.entity.hr.employment.Job;
import cn.hb.dao.hr.employment.JobDao;

/**
 * 职位 DaoTest
 */
public class JobDaoTest extends HibernateDaoTestCase {
	private JobDao jobDao;

	public JobDao getJobDao() {
		return jobDao;
	}

	public void setJobDao(JobDao jobDao) {
		this.jobDao = jobDao;
	}

	public void testCase() {
		Job job = new Job();
	}

}
