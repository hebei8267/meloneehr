package cn.hb.dao.hr.organization;

import static constant.TestConstant.ROOT_PATH;

import java.io.IOException;
import java.util.List;

import cn.hb.core.test.dao.HibernateDaoTestCase;
import cn.hb.entity.hr.organization.OrganizationType;
import cn.hb.dao.hr.organization.OrganizationTypeDao;
import file.CSVUtils;

/**
 * 组织类型 DaoTest
 */
public class OrganizationTypeDaoTest extends HibernateDaoTestCase {
    private OrganizationTypeDao organizationTypeDao;

    public OrganizationTypeDao getOrganizationTypeDao() {
        return organizationTypeDao;
    }

    public void setOrganizationTypeDao(OrganizationTypeDao organizationTypeDao) {
        this.organizationTypeDao = organizationTypeDao;
    }

    public void testCase() throws IOException {
        List<List<String>> csvFileContent = CSVUtils.readCSVFile(ROOT_PATH + "OrganizationType.csv");

        for (List<String> fileLine : csvFileContent) {
            OrganizationType organizationType = new OrganizationType();

            for (int i = 0; i < fileLine.size(); i++) {
                String value = fileLine.get(i);
                if (i == 0) {
                    organizationType.setId(value);
                } else if (i == 1) {
                    organizationType.setName(value);
                } else if (i == 2) {
                    organizationType.setDescription(value);
                }
            }

            organizationTypeDao.save(organizationType);
        }
    }

}
