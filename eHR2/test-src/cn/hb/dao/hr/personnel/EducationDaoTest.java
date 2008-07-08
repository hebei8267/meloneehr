package cn.hb.dao.hr.personnel;

import static constant.TestConstant.ROOT_PATH;

import java.io.IOException;
import java.util.List;

import cn.hb.core.test.dao.HibernateDaoTestCase;
import cn.hb.entity.dictionary.personnel.EducateSpecialty;
import cn.hb.entity.hr.personnel.Education;
import cn.hb.entity.hr.personnel.Person;
import cn.hb.dao.dictionary.personnel.EducateSpecialtyDao;
import cn.hb.dao.hr.personnel.EducationDao;
import file.CSVUtils;

/**
 * 教育信息 DaoTest
 */
public class EducationDaoTest extends HibernateDaoTestCase {
    private EducationDao educationDao;
    private PersonDao personDao;
    private EducateSpecialtyDao educateSpecialtyDao;

    public EducationDao getEducationDao() {
        return educationDao;
    }

    public void setEducationDao(EducationDao educationDao) {
        this.educationDao = educationDao;
    }

    public PersonDao getPersonDao() {
        return personDao;
    }

    public void setPersonDao(PersonDao personDao) {
        this.personDao = personDao;
    }

    public EducateSpecialtyDao getEducateSpecialtyDao() {
        return educateSpecialtyDao;
    }

    public void setEducateSpecialtyDao(EducateSpecialtyDao educateSpecialtyDao) {
        this.educateSpecialtyDao = educateSpecialtyDao;
    }

    public void testCase() throws IOException {
        List<List<String>> csvFileContent = CSVUtils.readCSVFile(ROOT_PATH + "Education.csv");

        for (List<String> fileLine : csvFileContent) {
            Education education = new Education();

            for (int i = 0; i < fileLine.size(); i++) {
                String value = fileLine.get(i);
                if (i == 0) {
                    education.setId(value);
                } else if (i == 1) {
                    education.setStartDate(value);
                } else if (i == 2) {
                    education.setEndDate(value);
                } else if (i == 3) {
                    education.setSchoolName(value);
                } else if (i == 4) {
                    EducateSpecialty educateSpecialty = educateSpecialtyDao.getEducateSpecialtyByID(value);

                    education.setEducateSpecialty(educateSpecialty);
                } else if (i == 5) {

                    Person person = personDao.getPersonByID(value);

                    person.addEducation(education);
                    education.setPerson(person);
                }
            }

            educationDao.save(education);
        }
    }

}
