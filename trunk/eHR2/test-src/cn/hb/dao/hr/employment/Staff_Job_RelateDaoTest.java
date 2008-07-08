package cn.hb.dao.hr.employment;

import cn.hb.core.test.dao.HibernateDaoTestCase;
import cn.hb.entity.hr.employment.Staff_Job_Relate;
import cn.hb.dao.hr.employment.Staff_Job_RelateDao;

/**
 * 员工-职位 DaoTest
 */
public class Staff_Job_RelateDaoTest extends HibernateDaoTestCase {
	private Staff_Job_RelateDao staff_Job_RelateDao;

	public Staff_Job_RelateDao getStaff_Job_RelateDao() {
		return staff_Job_RelateDao;
	}

	public void setStaff_Job_RelateDao(Staff_Job_RelateDao staff_Job_RelateDao) {
		this.staff_Job_RelateDao = staff_Job_RelateDao;
	}

	public void testCase() {
		Staff_Job_Relate staff_Job_Relate = new Staff_Job_Relate();
	}

}
