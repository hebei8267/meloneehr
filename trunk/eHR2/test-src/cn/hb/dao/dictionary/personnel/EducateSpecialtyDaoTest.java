package cn.hb.dao.dictionary.personnel;

import static constant.TestConstant.ROOT_PATH;

import java.io.IOException;
import java.util.List;

import cn.hb.core.test.dao.HibernateDaoTestCase;
import cn.hb.entity.dictionary.personnel.EducateSpecialty;
import cn.hb.dao.dictionary.personnel.EducateSpecialtyDao;
import file.CSVUtils;

/**
 * 教育专业 DaoTest
 */
public class EducateSpecialtyDaoTest extends HibernateDaoTestCase {
    private EducateSpecialtyDao educateSpecialtyDao;

    public EducateSpecialtyDao getEducateSpecialtyDao() {
        return educateSpecialtyDao;
    }

    public void setEducateSpecialtyDao(EducateSpecialtyDao educateSpecialtyDao) {
        this.educateSpecialtyDao = educateSpecialtyDao;
    }

    public void testCase() throws IOException {
        List<List<String>> csvFileContent = CSVUtils.readCSVFile(ROOT_PATH + "EducateSpecialty.csv");

        for (List<String> fileLine : csvFileContent) {
            EducateSpecialty educateSpecialty = new EducateSpecialty();

            for (int i = 0; i < fileLine.size(); i++) {
                String value = fileLine.get(i);
                if (i == 0) {
                    educateSpecialty.setId(value);
                } else if (i == 1) {
                    educateSpecialty.setName(value);
                } else if (i == 2) {
                    EducateSpecialty parentEducateSpecialty = educateSpecialtyDao.getEducateSpecialtyByID(value);

                    parentEducateSpecialty.addSubEducateSpecialty(educateSpecialty);

                    educateSpecialty.setParentEducateSpecialty(parentEducateSpecialty);

                } else if (i == 3) {
                    educateSpecialty.setNote(value);
                }
            }

            educateSpecialtyDao.save(educateSpecialty);
        }
    }

}
