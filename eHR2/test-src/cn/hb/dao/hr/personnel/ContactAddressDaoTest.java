package cn.hb.dao.hr.personnel;

import cn.hb.core.test.dao.HibernateDaoTestCase;
import cn.hb.entity.hr.personnel.ContactAddress;
import cn.hb.dao.hr.personnel.ContactAddressDao;

/**
 * 联系地址 DaoTest
 */
public class ContactAddressDaoTest extends HibernateDaoTestCase {
	private ContactAddressDao contactAddressDao;

	public ContactAddressDao getContactAddressDao() {
		return contactAddressDao;
	}

	public void setContactAddressDao(ContactAddressDao contactAddressDao) {
		this.contactAddressDao = contactAddressDao;
	}

	public void testCase() {
		ContactAddress contactAddress = new ContactAddress();
	}

}
