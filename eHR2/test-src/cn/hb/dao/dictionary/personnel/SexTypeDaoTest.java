package cn.hb.dao.dictionary.personnel;

import static constant.TestConstant.ROOT_PATH;

import java.io.IOException;
import java.util.List;

import cn.hb.core.test.dao.HibernateDaoTestCase;
import cn.hb.entity.dictionary.personnel.SexType;
import file.CSVUtils;

/**
 * 性别类型 DaoTest
 */
public class SexTypeDaoTest extends HibernateDaoTestCase {
    private SexTypeDao sexTypeDao;

    public SexTypeDao getSexTypeDao() {
        return sexTypeDao;
    }

    public void setSexTypeDao(SexTypeDao sexTypeDao) {
        this.sexTypeDao = sexTypeDao;
    }

    public void testCase() throws IOException {
        List<List<String>> csvFileContent = CSVUtils.readCSVFile(ROOT_PATH + "SexType.csv");

        for (List<String> fileLine : csvFileContent) {
            SexType sexType = new SexType();

            for (int i = 0; i < fileLine.size(); i++) {
                String value = fileLine.get(i);
                if (i == 0) {
                    sexType.setMasterID(value);
                } else if (i == 1) {
                    sexType.setSlaveID(value);
                } else if (i == 2) {
                    sexType.setName(value);
                } else if (i == 3) {
                    sexType.setNote(value);
                }
            }
            sexTypeDao.save(sexType);
        }
    }
}
