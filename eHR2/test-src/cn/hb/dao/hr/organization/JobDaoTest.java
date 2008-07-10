package cn.hb.dao.hr.organization;

import static constant.TestConstant.ROOT_PATH;

import java.io.IOException;
import java.util.List;

import cn.hb.core.test.dao.HibernateDaoTestCase;
import cn.hb.entity.hr.organization.Job;
import cn.hb.entity.hr.organization.JobPosition;
import cn.hb.dao.hr.organization.JobDao;
import file.CSVUtils;

/**
 * 职位 DaoTest
 */
public class JobDaoTest extends HibernateDaoTestCase {
    private JobDao jobDao;
    private JobPositionDao jobPositionDao;

    public JobDao getJobDao() {
        return jobDao;
    }

    public void setJobDao(JobDao jobDao) {
        this.jobDao = jobDao;
    }

    public JobPositionDao getJobPositionDao() {
        return jobPositionDao;
    }

    public void setJobPositionDao(JobPositionDao jobPositionDao) {
        this.jobPositionDao = jobPositionDao;
    }

    public void testCase() throws IOException {
        List<List<String>> csvFileContent = CSVUtils.readCSVFile(ROOT_PATH + "Job.csv");

        for (List<String> fileLine : csvFileContent) {
            Job job = new Job();

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

            jobDao.save(job);
        }

    }

}
