package cn.hb.dao.dictionary.personnel;

import static constant.TestConstant.ROOT_PATH;

import java.io.IOException;
import java.util.List;

import cn.hb.core.test.dao.HibernateDaoTestCase;
import cn.hb.dao.dictionary.personnel.MarriageStateDao;
import cn.hb.entity.dictionary.personnel.MarriageState;
import file.CSVUtils;

/**
 * 婚姻状况 DaoTest
 */
public class MarriageStateDaoTest extends HibernateDaoTestCase {
    private MarriageStateDao marriageStateDao;

    public MarriageStateDao getMarriageStateDao() {
        return marriageStateDao;
    }

    public void setMarriageStateDao(MarriageStateDao marriageStateDao) {
        this.marriageStateDao = marriageStateDao;
    }

    public void testCase() throws IOException {
        List<List<String>> csvFileContent = CSVUtils.readCSVFile(ROOT_PATH + "MarriageState.csv");

        for (List<String> fileLine : csvFileContent) {
            MarriageState marriageState = new MarriageState();

            for (int i = 0; i < fileLine.size(); i++) {
                String value = fileLine.get(i);
                if (i == 0) {
                    marriageState.setMasterID(value);
                } else if (i == 1) {
                    marriageState.setSlaveID(value);
                } else if (i == 2) {
                    marriageState.setName(value);
                } else if (i == 3) {
                    marriageState.setNote(value);
                }
            }
            marriageStateDao.save(marriageState);
        }
    }

}
