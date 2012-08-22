package test.com.tjhx.service;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springside.modules.test.spring.SpringTransactionalTestCase;

import com.tjhx.dao.jpa.account.FunctionJpaDao;
import com.tjhx.entity.account.Function;

@DirtiesContext
@ContextConfiguration(locations = { "classpath*:/applicationContext.xml" })
@TransactionConfiguration(transactionManager = "defaultTransactionManager")
public class RoleManagerTest extends SpringTransactionalTestCase {
	private FunctionJpaDao functionJpaDao;

	@Test
	public void initFunction() {
		Function f9 = new Function();
		f9.setDisplayName("用户");
		f9.setFunUrl("account/user/list");
		f9.setFunFlg("0");
		functionJpaDao.save(f9);

		Function f1 = new Function();
		f1.setDisplayName("角色");
		f1.setFunUrl("account/role/list");
		f1.setFunFlg("0");
		functionJpaDao.save(f1);

		// ------------------------------------------------------
		Function f2 = new Function();
		f2.setDisplayName("商品品牌");
		f2.setFunUrl("product/productBrand/list");
		f2.setFunFlg("0");
		functionJpaDao.save(f2);

		Function f3 = new Function();
		f3.setDisplayName("商品类型");
		f3.setFunUrl("product/productType/list");
		f3.setFunFlg("0");
		functionJpaDao.save(f3);

		Function f4 = new Function();
		f4.setDisplayName("商品标签");
		f4.setFunUrl("product/productTag/list");
		f4.setFunFlg("0");
		functionJpaDao.save(f4);

		Function f5 = new Function();
		f5.setDisplayName("供应商（商品）");
		f5.setFunUrl("product/productSupplier/list");
		f5.setFunFlg("0");
		functionJpaDao.save(f5);
		// ------------------------------------------------------
		Function f6 = new Function();
		f6.setDisplayName("仓库类型");
		f6.setFunUrl("syscfg/storeType/list");
		f6.setFunFlg("0");
		functionJpaDao.save(f6);

		Function f7 = new Function();
		f7.setDisplayName("仓库");
		f7.setFunUrl("syscfg/store/list");
		f7.setFunFlg("0");
		functionJpaDao.save(f7);

		Function f8 = new Function();
		f8.setDisplayName("门店");
		f8.setFunUrl("syscfg/shop/list");
		f8.setFunFlg("0");
		functionJpaDao.save(f8);

	}

	@Autowired
	public void setFunctionJpaDao(FunctionJpaDao functionJpaDao) {
		this.functionJpaDao = functionJpaDao;
	}

}
