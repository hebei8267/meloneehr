package cn.hb.dao.dictionary.organization;

import static constant.TestConstant.ROOT_PATH;

import java.io.IOException;
import java.util.List;

import cn.hb.core.test.dao.HibernateDaoTestCase;
import cn.hb.entity.dictionary.organization.Organization_JobPosition_RelateType;
import cn.hb.dao.dictionary.organization.Organization_JobPosition_RelateTypeDao;
import file.CSVUtils;

/**
 * 组织-职务关联类型(排斥_非排斥-关联) DaoTest
 */
public class Organization_JobPosition_RelateTypeDaoTest extends HibernateDaoTestCase {
    private Organization_JobPosition_RelateTypeDao organization_JobPosition_RelateTypeDao;

    public Organization_JobPosition_RelateTypeDao getOrganization_JobPosition_RelateTypeDao() {
        return organization_JobPosition_RelateTypeDao;
    }

    public void setOrganization_JobPosition_RelateTypeDao(
            Organization_JobPosition_RelateTypeDao organization_JobPosition_RelateTypeDao) {
        this.organization_JobPosition_RelateTypeDao = organization_JobPosition_RelateTypeDao;
    }

    public void testCase() throws IOException {
        List<List<String>> csvFileContent = CSVUtils.readCSVFile(ROOT_PATH + "Organization_JobPosition_RelateType.csv");

        for (List<String> fileLine : csvFileContent) {
            Organization_JobPosition_RelateType organization_JobPosition_RelateType = new Organization_JobPosition_RelateType();

            for (int i = 0; i < fileLine.size(); i++) {
                String value = fileLine.get(i);
                if (i == 0) {
                    organization_JobPosition_RelateType.setMasterID(value);
                } else if (i == 1) {
                    organization_JobPosition_RelateType.setSlaveID(value);
                } else if (i == 2) {
                    organization_JobPosition_RelateType.setName(value);
                } else if (i == 3) {
                    organization_JobPosition_RelateType.setNote(value);
                }
            }

            organization_JobPosition_RelateTypeDao.save(organization_JobPosition_RelateType);
        }
    }
}
