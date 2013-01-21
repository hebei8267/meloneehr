package test.com.tjhx.service.accounts;

import javax.annotation.Resource;

import org.junit.Test;
import org.springside.modules.test.spring.SpringTransactionalTestCase;

import com.tjhx.dao.accounts.StorageRunJpaDao;

public class StorageRunJpaDaoTest extends SpringTransactionalTestCase {
	@Resource
	private StorageRunJpaDao storageRunJpaDao;

	@Test
	public void getAll() {
		System.out.println(storageRunJpaDao == null);

	}

}
