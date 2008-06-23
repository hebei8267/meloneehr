package cn.hb.dao.hr.organization;

import static constant.TestConstant.ROOT_PATH;

import java.io.IOException;
import java.util.List;

import cn.hb.core.test.dao.HibernateDaoTestCase;
import cn.hb.entity.hr.organization.Organization;
import cn.hb.dao.hr.organization.OrganizationDao;
import file.CSVUtils;

/**
 * 组织 DaoTest
 */
public class OrganizationDaoTest extends HibernateDaoTestCase {
    private OrganizationDao organizationDao;

    public OrganizationDao getOrganizationDao() {
        return organizationDao;
    }

    public void setOrganizationDao(OrganizationDao organizationDao) {
        this.organizationDao = organizationDao;
    }

    public void testCase() throws IOException {
        List<List<String>> csvFileContent = CSVUtils.readCSVFile(ROOT_PATH + "Organization.csv");

        for (List<String> fileLine : csvFileContent) {
            Organization organization = new Organization();

            for (int i = 0; i < fileLine.size(); i++) {
                String value = fileLine.get(i);
                if (i == 0) {
                    organization.setId(value);
                } else if (i == 1) {
                    organization.setName(value);
                } else if (i == 2) {
                    organization.setStartDate(value);
                } else if (i == 3) {
                    organization.setEndDate(value);
                } else if (i == 4) {
                    Organization parentOrganization = organizationDao.getOrganizationByID(value);

                    parentOrganization.addSubOrganization(organization);

                    organization.setParentOrganization(parentOrganization);
                }
            }

            organizationDao.save(organization);
        }
    }

}
