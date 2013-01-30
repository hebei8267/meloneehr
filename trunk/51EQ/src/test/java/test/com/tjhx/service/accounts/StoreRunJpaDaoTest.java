package test.com.tjhx.service.accounts;

import javax.annotation.Resource;

import org.junit.Test;
import org.springside.modules.test.spring.SpringTransactionalTestCase;

import com.tjhx.dao.accounts.StoreRunJpaDao;

public class StoreRunJpaDaoTest extends SpringTransactionalTestCase {
	@Resource
	private StoreRunJpaDao storeRunJpaDao;

	@Test
	public void getAll() {
		System.out.println(storeRunJpaDao == null);

	}

}
