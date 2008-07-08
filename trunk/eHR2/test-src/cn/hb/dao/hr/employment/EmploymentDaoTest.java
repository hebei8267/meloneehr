package cn.hb.dao.hr.employment;

import cn.hb.core.test.dao.HibernateDaoTestCase;
import cn.hb.entity.hr.employment.Employment;
import cn.hb.dao.hr.employment.EmploymentDao;

/**
 * 雇佣 DaoTest
 */
public class EmploymentDaoTest extends HibernateDaoTestCase {
	private EmploymentDao employmentDao;

	public EmploymentDao getEmploymentDao() {
		return employmentDao;
	}

	public void setEmploymentDao(EmploymentDao employmentDao) {
		this.employmentDao = employmentDao;
	}

	public void testCase() {
		Employment employment = new Employment();
	}

}
