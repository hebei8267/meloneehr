package cn.hb.dao.hr.personnel;

import static constant.TestConstant.ROOT_PATH;

import java.io.IOException;
import java.util.List;

import cn.hb.core.test.dao.HibernateDaoTestCase;
import cn.hb.entity.hr.personnel.ContactAddress;
import cn.hb.entity.hr.personnel.Person;
import cn.hb.dao.hr.personnel.ContactAddressDao;
import file.CSVUtils;

/**
 * 联系地址 DaoTest
 */
public class ContactAddressDaoTest extends HibernateDaoTestCase {
    private ContactAddressDao contactAddressDao;
    private PersonDao personDao;

    public ContactAddressDao getContactAddressDao() {
        return contactAddressDao;
    }

    public void setContactAddressDao(ContactAddressDao contactAddressDao) {
        this.contactAddressDao = contactAddressDao;
    }

    public PersonDao getPersonDao() {
        return personDao;
    }

    public void setPersonDao(PersonDao personDao) {
        this.personDao = personDao;
    }

    public void testCase() throws IOException {
        List<List<String>> csvFileContent = CSVUtils.readCSVFile(ROOT_PATH + "ContactAddress.csv");

        for (List<String> fileLine : csvFileContent) {
            ContactAddress contactAddress = new ContactAddress();

            for (int i = 0; i < fileLine.size(); i++) {
                String value = fileLine.get(i);
                if (i == 0) {
                    contactAddress.setId(value);
                } else if (i == 1) {
                    contactAddress.setContactInfoName(value);
                } else if (i == 2) {
                    contactAddress.setContactAddress(value);
                } else if (i == 3) {
                    contactAddress.setPostID(value);
                } else if (i == 4) {
                    contactAddress.setMobileTelephone(value);
                } else if (i == 5) {
                    contactAddress.setTelephone(value);
                } else if (i == 6) {
                    contactAddress.setEmail(value);
                } else if (i == 7) {

                    Person person = personDao.getPersonByID(value);

                    person.addContactAddress(contactAddress);
                    contactAddress.setPerson(person);
                }
            }

            contactAddressDao.save(contactAddress);
        }
    }

}
