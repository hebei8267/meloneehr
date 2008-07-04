package cn.hb.dao.dictionary.financial;

import static constant.TestConstant.ROOT_PATH;

import java.io.IOException;
import java.util.List;

import cn.hb.core.test.dao.HibernateDaoTestCase;
import cn.hb.entity.dictionary.financial.Job_Salary_RelateType;
import cn.hb.dao.dictionary.financial.Job_Salary_RelateTypeDao;
import file.CSVUtils;

/**
 * 职位-薪酬福利关联类型(排斥_非排斥-关联) DaoTest
 */
public class Job_Salary_RelateTypeDaoTest extends HibernateDaoTestCase {
    private Job_Salary_RelateTypeDao job_Salary_RelateTypeDao;

    public Job_Salary_RelateTypeDao getJob_Salary_RelateTypeDao() {
        return job_Salary_RelateTypeDao;
    }

    public void setJob_Salary_RelateTypeDao(Job_Salary_RelateTypeDao job_Salary_RelateTypeDao) {
        this.job_Salary_RelateTypeDao = job_Salary_RelateTypeDao;
    }

    public void testCase() throws IOException {
        List<List<String>> csvFileContent = CSVUtils.readCSVFile(ROOT_PATH + "Job_Salary_RelateType.csv");

        for (List<String> fileLine : csvFileContent) {
            Job_Salary_RelateType job_Salary_RelateType = new Job_Salary_RelateType();

            for (int i = 0; i < fileLine.size(); i++) {
                String value = fileLine.get(i);
                if (i == 0) {
                    job_Salary_RelateType.setMasterID(value);
                } else if (i == 1) {
                    job_Salary_RelateType.setSlaveID(value);
                } else if (i == 2) {
                    job_Salary_RelateType.setName(value);
                } else if (i == 3) {
                    job_Salary_RelateType.setNote(value);
                }
            }

            job_Salary_RelateTypeDao.save(job_Salary_RelateType);
        }
    }

}
