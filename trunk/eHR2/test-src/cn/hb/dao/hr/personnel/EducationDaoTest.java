package cn.hb.dao.hr.personnel;

import cn.hb.core.test.dao.HibernateDaoTestCase;
import cn.hb.entity.hr.personnel.Education;
import cn.hb.dao.hr.personnel.EducationDao;

/**
 * 教育信息 DaoTest
 */
public class EducationDaoTest extends HibernateDaoTestCase {
	private EducationDao educationDao;

	public EducationDao getEducationDao() {
		return educationDao;
	}

	public void setEducationDao(EducationDao educationDao) {
		this.educationDao = educationDao;
	}

	public void testCase() {
		Education education = new Education();
	}

}
