package cn.hb.dao.dictionary.personnel;

import static constant.TestConstant.ROOT_PATH;

import java.io.IOException;
import java.util.List;

import cn.hb.core.test.dao.HibernateDaoTestCase;
import cn.hb.entity.dictionary.personnel.IdentifieationType;
import cn.hb.dao.dictionary.personnel.IdentifieationTypeDao;
import file.CSVUtils;

/**
 * 身份标识类型 DaoTest
 */
public class IdentifieationTypeDaoTest extends HibernateDaoTestCase {
    private IdentifieationTypeDao identifieationTypeDao;

    public IdentifieationTypeDao getIdentifieationTypeDao() {
        return identifieationTypeDao;
    }

    public void setIdentifieationTypeDao(IdentifieationTypeDao identifieationTypeDao) {
        this.identifieationTypeDao = identifieationTypeDao;
    }

    public void testCase() throws IOException {
        List<List<String>> csvFileContent = CSVUtils.readCSVFile(ROOT_PATH + "IdentifieationType.csv");

        for (List<String> fileLine : csvFileContent) {
            IdentifieationType identifieationType = new IdentifieationType();
            for (int i = 0; i < fileLine.size(); i++) {
                String value = fileLine.get(i);
                if (i == 0) {
                    identifieationType.setMasterID(value);
                } else if (i == 1) {
                    identifieationType.setSlaveID(value);
                } else if (i == 2) {
                    identifieationType.setName(value);
                } else if (i == 3) {
                    identifieationType.setNote(value);
                }
            }
            identifieationTypeDao.save(identifieationType);
        }
    }

}
