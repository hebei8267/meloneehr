package cn.hb.dao.hr.employment;

import static constant.TestConstant.ROOT_PATH;

import java.io.IOException;
import java.util.List;

import cn.hb.core.test.dao.HibernateDaoTestCase;
import cn.hb.entity.hr.employment.Employment;
import cn.hb.entity.hr.organization.JobPosition;
import cn.hb.dao.hr.employment.EmploymentDao;
import file.CSVUtils;

/**
 * 雇佣 DaoTest
 */
public class EmploymentDaoTest extends HibernateDaoTestCase {
    private EmploymentDao employmentDao;

    public EmploymentDao getEmploymentDao() {
        return employmentDao;
    }

    public void setEmploymentDao(EmploymentDao employmentDao) {
        this.employmentDao = employmentDao;
    }

    /**
     * 雇佣员工
     * 
     * @throws IOException
     */
    public void testCase() throws IOException {
        List<List<String>> csvFileContent = CSVUtils.readCSVFile(ROOT_PATH + "Employment.csv");

        for (List<String> fileLine : csvFileContent) {
            Employment employment = new Employment();

            for (int i = 0; i < fileLine.size(); i++) {
                String value = fileLine.get(i);
                if (i == 0) {
                    job.setOrganizationID(value);
                } else if (i == 1) {
                    job.setJobPositionID(value);

                    JobPosition jobPosition = jobPositionDao.getJobPositionByID(value);
                    job.setName(jobPosition.getName());
                } else if (i == 2) {
                    job.setStartDate(value);
                } else if (i == 3) {
                    job.setEndDate(value);
                } else if (i == 4) {
                    job.setAssignmentSize(Integer.parseInt(value));
                } else if (i == 5) {
                    job.setAllowExceed(Boolean.parseBoolean(value));
                } else if (i == 6) {
                    job.setIsManager(Boolean.parseBoolean(value));
                }
            }

            employmentDao.save(employment);
        }
    }

}
