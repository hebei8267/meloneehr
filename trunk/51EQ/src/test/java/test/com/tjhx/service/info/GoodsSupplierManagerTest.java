package test.com.tjhx.service.info;

import javax.annotation.Resource;

import org.junit.Test;
import org.springframework.test.annotation.Rollback;
import org.springside.modules.test.spring.SpringTransactionalTestCase;

import com.tjhx.entity.info.Supplier;
import com.tjhx.service.info.SupplierManager;

public class GoodsSupplierManagerTest extends SpringTransactionalTestCase {

	@Resource
	private SupplierManager goodsSupplierManager;

	@Test
	@Rollback(false)
	public void saveNewSupplier() {
		Supplier supplier = new Supplier();
		supplier.setName("供应商1");
		supplier.setSupplierBwId("001");
		goodsSupplierManager.addNewGoodsSupplier(supplier);

		Supplier supplier1 = new Supplier();
		supplier1.setName("供应商2");
		supplier1.setSupplierBwId("002");
		goodsSupplierManager.addNewGoodsSupplier(supplier1);
	}
}
