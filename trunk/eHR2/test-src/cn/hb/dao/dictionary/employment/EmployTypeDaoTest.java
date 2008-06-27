package cn.hb.dao.dictionary.employment;

import static constant.TestConstant.ROOT_PATH;

import java.io.IOException;
import java.util.List;

import cn.hb.core.test.dao.HibernateDaoTestCase;
import cn.hb.entity.dictionary.employment.EmployType;
import cn.hb.dao.dictionary.employment.EmployTypeDao;
import file.CSVUtils;

/**
 * 雇佣类型 DaoTest
 */
public class EmployTypeDaoTest extends HibernateDaoTestCase {
    private EmployTypeDao employTypeDao;

    public EmployTypeDao getEmployTypeDao() {
        return employTypeDao;
    }

    public void setEmployTypeDao(EmployTypeDao employTypeDao) {
        this.employTypeDao = employTypeDao;
    }

    public void testCase() throws IOException {
        List<List<String>> csvFileContent = CSVUtils.readCSVFile(ROOT_PATH + "EmployType.csv");

        for (List<String> fileLine : csvFileContent) {
            EmployType employType = new EmployType();
            for (int i = 0; i < fileLine.size(); i++) {

                String value = fileLine.get(i);
                if (i == 0) {
                    employType.setId(value);
                } else if (i == 1) {
                    employType.setName(value);
                } else if (i == 2) {
                    employType.setNote(value);
                }
            }

            employTypeDao.save(employType);
        }
    }

}
