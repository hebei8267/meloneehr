package test.com.tjhx.service;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springside.modules.test.spring.SpringTransactionalTestCase;

@DirtiesContext
@ContextConfiguration(locations = { "classpath*:/applicationContext.xml" })
@TransactionConfiguration(transactionManager = "defaultTransactionManager")
public class AccountManagerTest extends SpringTransactionalTestCase {
	@Test
	public void saveNewUser() {
		assertEquals("Dolphin", "Dolphin");
	}

}
