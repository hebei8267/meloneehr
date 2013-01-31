package test.com.tjhx.service.info;

import javax.annotation.Resource;

import org.junit.Test;
import org.springframework.test.annotation.Rollback;
import org.springside.modules.test.spring.SpringTransactionalTestCase;

import com.tjhx.entity.info.Supplier;
import com.tjhx.service.info.SupplierManager;

public class SupplierManagerTest extends SpringTransactionalTestCase {

	@Resource
	private SupplierManager supplierManager;

	@Test
	@Rollback(false)
	public void saveNewSupplier() {
		Supplier supplier = new Supplier();
		supplier.setName("供应商1");
		supplier.setSupplierBwId("001");
		supplierManager.addNewSupplier(supplier);

		Supplier supplier1 = new Supplier();
		supplier1.setName("供应商2");
		supplier1.setSupplierBwId("002");
		supplierManager.addNewSupplier(supplier1);
	}
}
