package cn.hb.dao.dictionary.personnel;

import static constant.TestConstant.ROOT_PATH;

import java.io.IOException;
import java.util.List;

import cn.hb.core.test.dao.HibernateDaoTestCase;
import cn.hb.entity.dictionary.personnel.Currculum;
import cn.hb.dao.dictionary.personnel.CurrculumDao;
import file.CSVUtils;

/**
 * 学历信息 DaoTest
 */
public class CurrculumDaoTest extends HibernateDaoTestCase {
    private CurrculumDao currculumDao;

    public CurrculumDao getCurrculumDao() {
        return currculumDao;
    }

    public void setCurrculumDao(CurrculumDao currculumDao) {
        this.currculumDao = currculumDao;
    }

    public void testCase() throws IOException {
        List<List<String>> csvFileContent = CSVUtils.readCSVFile(ROOT_PATH + "Currculum.csv");

        for (List<String> fileLine : csvFileContent) {
            Currculum currculum = new Currculum();
            for (int i = 0; i < fileLine.size(); i++) {
                String value = fileLine.get(i);
                if (i == 0) {
                    currculum.setId(value);
                } else if (i == 1) {
                    currculum.setName(value);
                } else if (i == 2) {
                    currculum.setNote(value);
                }
            }
            currculumDao.save(currculum);
        }
    }
}
