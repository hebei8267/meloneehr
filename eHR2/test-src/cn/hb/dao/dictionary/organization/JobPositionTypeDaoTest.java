package cn.hb.dao.dictionary.organization;

import static constant.TestConstant.ROOT_PATH;

import java.io.IOException;
import java.util.List;

import cn.hb.core.test.dao.HibernateDaoTestCase;
import cn.hb.entity.dictionary.organization.JobPositionType;
import file.CSVUtils;

/**
 * @author kaka
 * 
 * 职种(职务类型) DaoTest
 */
public class JobPositionTypeDaoTest extends HibernateDaoTestCase {
    private JobPositionTypeDao jobPositionTypeDao;

    public JobPositionTypeDao getJobPositionTypeDao() {
        return jobPositionTypeDao;
    }

    public void setJobPositionTypeDao(JobPositionTypeDao jobPositionTypeDao) {
        this.jobPositionTypeDao = jobPositionTypeDao;
    }

    public void testCase() throws IOException {
        List<List<String>> csvFileContent = CSVUtils.readCSVFile(ROOT_PATH + "JobPositionType.csv");

        for (List<String> fileLine : csvFileContent) {
            JobPositionType jobPositionType = new JobPositionType();

            for (int i = 0; i < fileLine.size(); i++) {
                String value = fileLine.get(i);
                if (i == 0) {
                    jobPositionType.setId(value);
                } else if (i == 1) {
                    jobPositionType.setName(value);
                } else if (i == 2) {
                    JobPositionType parentJobPositionType = jobPositionTypeDao.getJobPositionTypeByID(value);

                    parentJobPositionType.addSubJobPositionType(jobPositionType);

                    jobPositionType.setParentJobPositionType(parentJobPositionType);

                } else if (i == 3) {
                    jobPositionType.setNote(value);
                }
            }

            jobPositionTypeDao.save(jobPositionType);
        }
    }
}
