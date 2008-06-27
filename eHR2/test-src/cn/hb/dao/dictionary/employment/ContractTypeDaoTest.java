package cn.hb.dao.dictionary.employment;

import static constant.TestConstant.ROOT_PATH;

import java.io.IOException;
import java.util.List;

import cn.hb.core.test.dao.HibernateDaoTestCase;
import cn.hb.entity.dictionary.employment.ContractType;
import cn.hb.dao.dictionary.employment.ContractTypeDao;
import file.CSVUtils;

/**
 * 合同类型 DaoTest
 */
public class ContractTypeDaoTest extends HibernateDaoTestCase {
    private ContractTypeDao contractTypeDao;

    public ContractTypeDao getContractTypeDao() {
        return contractTypeDao;
    }

    public void setContractTypeDao(ContractTypeDao contractTypeDao) {
        this.contractTypeDao = contractTypeDao;
    }

    public void testCase() throws IOException {
        List<List<String>> csvFileContent = CSVUtils.readCSVFile(ROOT_PATH + "ContractType.csv");

        for (List<String> fileLine : csvFileContent) {
            ContractType contractType = new ContractType();
            for (int i = 0; i < fileLine.size(); i++) {

                String value = fileLine.get(i);
                if (i == 0) {
                    contractType.setId(value);
                } else if (i == 1) {
                    contractType.setName(value);
                } else if (i == 2) {
                    contractType.setNote(value);
                }
            }

            contractTypeDao.save(contractType);
        }
    }

}
