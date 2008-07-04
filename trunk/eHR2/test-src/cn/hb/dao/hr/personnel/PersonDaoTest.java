package cn.hb.dao.hr.personnel;

import cn.hb.core.test.dao.HibernateDaoTestCase;
import cn.hb.entity.hr.personnel.Person;
import cn.hb.dao.hr.personnel.PersonDao;

/**
 * 个人基本信息 DaoTest
 */
public class PersonDaoTest extends HibernateDaoTestCase {
	private PersonDao personDao;

	public PersonDao getPersonDao() {
		return personDao;
	}

	public void setPersonDao(PersonDao personDao) {
		this.personDao = personDao;
	}

	public void testCase() {
		Person person = new Person();
	}

}
