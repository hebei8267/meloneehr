package cn.hb.dao.dictionary.employment;

import static constant.TestConstant.ROOT_PATH;

import java.io.IOException;
import java.util.List;

import cn.hb.core.test.dao.HibernateDaoTestCase;
import cn.hb.entity.dictionary.employment.EmployeeWorkState;
import cn.hb.dao.dictionary.employment.EmployeeWorkStateDao;
import file.CSVUtils;

/**
 * 员工当前工作状态 DaoTest
 */
public class EmployeeWorkStateDaoTest extends HibernateDaoTestCase {
    private EmployeeWorkStateDao employeeWorkStateDao;

    public EmployeeWorkStateDao getEmployeeWorkStateDao() {
        return employeeWorkStateDao;
    }

    public void setEmployeeWorkStateDao(EmployeeWorkStateDao employeeWorkStateDao) {
        this.employeeWorkStateDao = employeeWorkStateDao;
    }

    public void testCase() throws IOException {
        List<List<String>> csvFileContent = CSVUtils.readCSVFile(ROOT_PATH + "EmployeeWorkState.csv");

        for (List<String> fileLine : csvFileContent) {
            EmployeeWorkState employeeWorkState = new EmployeeWorkState();
            
            for (int i = 0; i < fileLine.size(); i++) {

                String value = fileLine.get(i);
                if (i == 0) {
                    employeeWorkState.setId(value);
                } else if (i == 1) {
                    employeeWorkState.setName(value);
                } else if (i == 2) {
                    employeeWorkState.setNote(value);
                }
            }

            employeeWorkStateDao.save(employeeWorkState);
        }
    }

}
