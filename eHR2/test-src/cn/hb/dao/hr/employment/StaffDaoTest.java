package cn.hb.dao.hr.employment;

import cn.hb.core.test.dao.HibernateDaoTestCase;
import cn.hb.entity.hr.employment.Staff;
import cn.hb.dao.hr.employment.StaffDao;

/**
 * 员工 DaoTest
 */
public class StaffDaoTest extends HibernateDaoTestCase {
	private StaffDao staffDao;

	public StaffDao getStaffDao() {
		return staffDao;
	}

	public void setStaffDao(StaffDao staffDao) {
		this.staffDao = staffDao;
	}

	public void testCase() {
		Staff staff = new Staff();
	}

}
