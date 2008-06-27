package cn.hb.dao.dictionary.financial;

import static constant.TestConstant.ROOT_PATH;

import java.io.IOException;
import java.util.List;

import cn.hb.core.test.dao.HibernateDaoTestCase;
import cn.hb.entity.dictionary.financial.SalaryType;
import cn.hb.dao.dictionary.financial.SalaryTypeDao;
import file.CSVUtils;

/**
 * 薪水类型 DaoTest
 */
public class SalaryTypeDaoTest extends HibernateDaoTestCase {
    private SalaryTypeDao salaryTypeDao;

    public SalaryTypeDao getSalaryTypeDao() {
        return salaryTypeDao;
    }

    public void setSalaryTypeDao(SalaryTypeDao salaryTypeDao) {
        this.salaryTypeDao = salaryTypeDao;
    }

    public void testCase() throws IOException {

        List<List<String>> csvFileContent = CSVUtils.readCSVFile(ROOT_PATH + "SalaryType.csv");

        for (List<String> fileLine : csvFileContent) {
            SalaryType salaryType = new SalaryType();

            for (int i = 0; i < fileLine.size(); i++) {

                String value = fileLine.get(i);
                if (i == 0) {
                    salaryType.setId(value);
                } else if (i == 1) {
                    salaryType.setName(value);
                } else if (i == 2) {
                    salaryType.setNote(value);
                }
            }

            salaryTypeDao.save(salaryType);
        }
    }

}
