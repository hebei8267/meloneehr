package cn.hb.dao.common;

import static constant.TestConstant.ROOT_PATH;

import java.io.IOException;
import java.util.List;

import cn.hb.core.test.dao.HibernateDaoTestCase;
import cn.hb.entity.common.Nation;
import cn.hb.dao.common.NationDao;
import file.CSVUtils;

/**
 * 民族 DaoTest
 */
public class NationDaoTest extends HibernateDaoTestCase {
    private NationDao nationDao;

    public NationDao getNationDao() {
        return nationDao;
    }

    public void setNationDao(NationDao nationDao) {
        this.nationDao = nationDao;
    }

    public void testCase() throws IOException {
        List<List<String>> csvFileContent = CSVUtils.readCSVFile(ROOT_PATH + "Nation.csv");

        for (List<String> fileLine : csvFileContent) {
            Nation nation = new Nation();

            for (int i = 0; i < fileLine.size(); i++) {

                String value = fileLine.get(i);
                if (i == 0) {
                    nation.setId(value);
                } else if (i == 1) {
                    nation.setName(value);
                } else if (i == 2) {
                    nation.setDescription(value);
                }
            }

            nationDao.save(nation);
        }
    }

}
