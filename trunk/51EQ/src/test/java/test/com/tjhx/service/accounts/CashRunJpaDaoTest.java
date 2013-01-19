package test.com.tjhx.service.accounts;

import javax.annotation.Resource;

import org.junit.Test;
import org.springside.modules.test.spring.SpringTransactionalTestCase;

import com.tjhx.dao.accounts.CashRunJpaDao;

public class CashRunJpaDaoTest extends SpringTransactionalTestCase{
	@Resource
	private	CashRunJpaDao cashRunJpaDao;
	@Test
	public void getAll() {
		System.out.println(cashRunJpaDao.calInitAmt("001", "20130119"));

	}

}
