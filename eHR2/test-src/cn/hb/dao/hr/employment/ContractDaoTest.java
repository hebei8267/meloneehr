package cn.hb.dao.hr.employment;

import cn.hb.core.test.dao.HibernateDaoTestCase;
import cn.hb.entity.hr.employment.Contract;
import cn.hb.dao.hr.employment.ContractDao;

/**
 * 合同 DaoTest
 */
public class ContractDaoTest extends HibernateDaoTestCase {
	private ContractDao contractDao;

	public ContractDao getContractDao() {
		return contractDao;
	}

	public void setContractDao(ContractDao contractDao) {
		this.contractDao = contractDao;
	}

	public void testCase() {
		Contract contract = new Contract();
	}

}
