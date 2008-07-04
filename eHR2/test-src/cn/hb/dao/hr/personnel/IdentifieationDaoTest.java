package cn.hb.dao.hr.personnel;

import cn.hb.core.test.dao.HibernateDaoTestCase;
import cn.hb.entity.hr.personnel.Identifieation;
import cn.hb.dao.hr.personnel.IdentifieationDao;

/**
 * 身份标识 DaoTest
 */
public class IdentifieationDaoTest extends HibernateDaoTestCase {
	private IdentifieationDao identifieationDao;

	public IdentifieationDao getIdentifieationDao() {
		return identifieationDao;
	}

	public void setIdentifieationDao(IdentifieationDao identifieationDao) {
		this.identifieationDao = identifieationDao;
	}

	public void testCase() {
		Identifieation identifieation = new Identifieation();
	}

}
