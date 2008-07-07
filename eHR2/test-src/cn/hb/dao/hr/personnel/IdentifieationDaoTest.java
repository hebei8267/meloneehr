package cn.hb.dao.hr.personnel;

import static constant.TestConstant.ROOT_PATH;

import java.io.IOException;
import java.util.List;

import cn.hb.core.test.dao.HibernateDaoTestCase;
import cn.hb.entity.dictionary.personnel.IdentifieationType;
import cn.hb.entity.hr.personnel.Identifieation;
import cn.hb.dao.dictionary.personnel.IdentifieationTypeDao;
import cn.hb.dao.hr.personnel.IdentifieationDao;
import file.CSVUtils;

/**
 * 身份标识 DaoTest
 */
public class IdentifieationDaoTest extends HibernateDaoTestCase {
    private IdentifieationDao identifieationDao;
    private IdentifieationTypeDao identifieationTypeDao;

    public IdentifieationDao getIdentifieationDao() {
        return identifieationDao;
    }

    public void setIdentifieationDao(IdentifieationDao identifieationDao) {
        this.identifieationDao = identifieationDao;
    }

    public IdentifieationTypeDao getIdentifieationTypeDao() {
        return identifieationTypeDao;
    }

    public void setIdentifieationTypeDao(IdentifieationTypeDao identifieationTypeDao) {
        this.identifieationTypeDao = identifieationTypeDao;
    }

    public void testCase() throws IOException {
        List<List<String>> csvFileContent = CSVUtils.readCSVFile(ROOT_PATH + "Identifieation.csv");

        for (List<String> fileLine : csvFileContent) {
            Identifieation identifieation = new Identifieation();

            for (int i = 0; i < fileLine.size(); i++) {
                String value = fileLine.get(i);
                if (i == 0) {
                    identifieation.setNo(value);
                } else if (i == 1) {
                    identifieation.setStartDate(value);
                } else if (i == 2) {
                    identifieation.setEndDate(value);
                } else if (i == 3) {
                    IdentifieationType identifieationType = identifieationTypeDao.getIdentifieationTypeByID(value);

                    identifieation.setIdentifieationType(identifieationType);
                }
            }

            identifieationDao.save(identifieation);
        }
    }

}
