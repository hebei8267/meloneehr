package cn.hb.dao.hr.organization;

import static constant.TestConstant.ROOT_PATH;

import java.io.IOException;
import java.util.List;

import cn.hb.core.test.dao.HibernateDaoTestCase;
import cn.hb.entity.dictionary.organization.JobPositionType;
import cn.hb.entity.dictionary.organization.Organization_JobPosition_RelateType;
import cn.hb.entity.hr.organization.JobPosition;
import cn.hb.dao.dictionary.organization.JobPositionTypeDao;
import cn.hb.dao.dictionary.organization.Organization_JobPosition_RelateTypeDao;
import cn.hb.dao.hr.organization.JobPositionDao;
import file.CSVUtils;

/**
 * 职务 DaoTest
 */
public class JobPositionDaoTest extends HibernateDaoTestCase {
    private JobPositionDao jobPositionDao;

    private JobPositionTypeDao jobPositionTypeDao;
    private Organization_JobPosition_RelateTypeDao organization_JobPosition_RelateTypeDao;

    public JobPositionDao getJobPositionDao() {
        return jobPositionDao;
    }

    public void setJobPositionDao(JobPositionDao jobPositionDao) {
        this.jobPositionDao = jobPositionDao;
    }

    public JobPositionTypeDao getJobPositionTypeDao() {
        return jobPositionTypeDao;
    }

    public void setJobPositionTypeDao(JobPositionTypeDao jobPositionTypeDao) {
        this.jobPositionTypeDao = jobPositionTypeDao;
    }

    public Organization_JobPosition_RelateTypeDao getOrganization_JobPosition_RelateTypeDao() {
        return organization_JobPosition_RelateTypeDao;
    }

    public void setOrganization_JobPosition_RelateTypeDao(
            Organization_JobPosition_RelateTypeDao organization_JobPosition_RelateTypeDao) {
        this.organization_JobPosition_RelateTypeDao = organization_JobPosition_RelateTypeDao;
    }

    public void testCase() throws IOException {
        List<List<String>> csvFileContent = CSVUtils.readCSVFile(ROOT_PATH + "JobPosition.csv");

        for (List<String> fileLine : csvFileContent) {
            JobPosition jobPosition = new JobPosition();

            for (int i = 0; i < fileLine.size(); i++) {
                String value = fileLine.get(i);
                if (i == 0) {
                    jobPosition.setId(value);
                } else if (i == 1) {
                    jobPosition.setName(value);
                } else if (i == 2) {
                    JobPositionType jobPositionType = jobPositionTypeDao.getJobPositionTypeByID(value);
                    jobPosition.setJobPositionType(jobPositionType);
                } else if (i == 3) {
                    Organization_JobPosition_RelateType organization_JobPosition_RelateType = organization_JobPosition_RelateTypeDao
                            .getOrganization_JobPosition_RelateTypeByID(value);
                    jobPosition.setOrganization_JobPosition_RelateType(organization_JobPosition_RelateType);
                }
            }

            jobPositionDao.save(jobPosition);
        }

    }
}
