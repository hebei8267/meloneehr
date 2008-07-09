package cn.hb.dao.hr.organization;

import static constant.TestConstant.ROOT_PATH;

import java.io.IOException;
import java.util.List;

import cn.hb.core.test.dao.HibernateDaoTestCase;
import cn.hb.entity.dictionary.organization.Organization_JobPosition_RelateType;
import cn.hb.entity.hr.organization.JobPosition;
import cn.hb.entity.hr.organization.Organization;
import cn.hb.entity.hr.organization.Organization_JobPosition_Relate;
import cn.hb.dao.dictionary.organization.Organization_JobPosition_RelateTypeDao;
import cn.hb.dao.hr.organization.Organization_JobPosition_RelateDao;
import file.CSVUtils;

/**
 * 组织-职务（关联） DaoTest
 */
public class Organization_JobPosition_RelateDaoTest extends HibernateDaoTestCase {
    private Organization_JobPosition_RelateDao organization_JobPosition_RelateDao;
    private OrganizationDao organizationDao;
    private JobPositionDao jobPositionDao;
    private Organization_JobPosition_RelateTypeDao organization_JobPosition_RelateTypeDao;

    public Organization_JobPosition_RelateDao getOrganization_JobPosition_RelateDao() {
        return organization_JobPosition_RelateDao;
    }

    public void setOrganization_JobPosition_RelateDao(
            Organization_JobPosition_RelateDao organization_JobPosition_RelateDao) {
        this.organization_JobPosition_RelateDao = organization_JobPosition_RelateDao;
    }

    public OrganizationDao getOrganizationDao() {
        return organizationDao;
    }

    public void setOrganizationDao(OrganizationDao organizationDao) {
        this.organizationDao = organizationDao;
    }

    public JobPositionDao getJobPositionDao() {
        return jobPositionDao;
    }

    public void setJobPositionDao(JobPositionDao jobPositionDao) {
        this.jobPositionDao = jobPositionDao;
    }

    public Organization_JobPosition_RelateTypeDao getOrganization_JobPosition_RelateTypeDao() {
        return organization_JobPosition_RelateTypeDao;
    }

    public void setOrganization_JobPosition_RelateTypeDao(
            Organization_JobPosition_RelateTypeDao organization_JobPosition_RelateTypeDao) {
        this.organization_JobPosition_RelateTypeDao = organization_JobPosition_RelateTypeDao;
    }

    public void testCase() throws IOException {
        List<List<String>> csvFileContent = CSVUtils.readCSVFile(ROOT_PATH + "Organization_JobPosition_Relate.csv");

        for (List<String> fileLine : csvFileContent) {
            Organization_JobPosition_Relate organization_JobPosition_Relate = new Organization_JobPosition_Relate();

            for (int i = 0; i < fileLine.size(); i++) {
                String value = fileLine.get(i);
                if (i == 0) {
                    Organization organization = organizationDao.getOrganizationByID(value);

                    organization_JobPosition_Relate.setOrganization(organization);
                } else if (i == 1) {
                    JobPosition jobPosition = jobPositionDao.getJobPositionByID(value);
                    organization_JobPosition_Relate.setJobPosition(jobPosition);
                } else if (i == 2) {
                    Organization_JobPosition_RelateType organization_JobPosition_RelateType = organization_JobPosition_RelateTypeDao
                            .getOrganization_JobPosition_RelateTypeByID(value);
                    organization_JobPosition_Relate
                            .setOrganization_JobPosition_RelateType(organization_JobPosition_RelateType);
                }
            }

            organization_JobPosition_RelateDao.save(organization_JobPosition_Relate);
        }
    }
}
