package cn.hb.dao.dictionary.employment;

import static constant.TestConstant.ROOT_PATH;

import java.io.IOException;
import java.util.List;

import cn.hb.core.test.dao.HibernateDaoTestCase;
import cn.hb.entity.dictionary.employment.HoldingJobType;
import cn.hb.dao.dictionary.employment.HoldingJobTypeDao;
import file.CSVUtils;

/**
 * 任职类型 DaoTest
 */
public class HoldingJobTypeDaoTest extends HibernateDaoTestCase {
    private HoldingJobTypeDao holdingJobTypeDao;

    public HoldingJobTypeDao getHoldingJobTypeDao() {
        return holdingJobTypeDao;
    }

    public void setHoldingJobTypeDao(HoldingJobTypeDao holdingJobTypeDao) {
        this.holdingJobTypeDao = holdingJobTypeDao;
    }

    public void testCase() throws IOException {
        List<List<String>> csvFileContent = CSVUtils.readCSVFile(ROOT_PATH + "HoldingJobType.csv");

        for (List<String> fileLine : csvFileContent) {
            HoldingJobType holdingJobType = new HoldingJobType();
            for (int i = 0; i < fileLine.size(); i++) {
                String value = fileLine.get(i);
                if (i == 0) {
                    holdingJobType.setMasterID(value);
                } else if (i == 1) {
                    holdingJobType.setSlaveID(value);
                } else if (i == 2) {
                    holdingJobType.setName(value);
                } else if (i == 3) {
                    holdingJobType.setNote(value);
                }
            }
            holdingJobTypeDao.save(holdingJobType);
        }
    }

}
