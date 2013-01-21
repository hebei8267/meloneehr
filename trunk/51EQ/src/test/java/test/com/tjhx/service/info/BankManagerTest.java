package test.com.tjhx.service.info;

import javax.annotation.Resource;

import org.junit.Test;
import org.springframework.test.annotation.Rollback;
import org.springside.modules.test.spring.SpringTransactionalTestCase;

import com.tjhx.entity.info.Bank;
import com.tjhx.service.info.BankManager;

public class BankManagerTest extends SpringTransactionalTestCase {
	@Resource
	private BankManager bankManager;

	@Test
	@Rollback(false)
	public void saveNewBank() {
		Bank bank = new Bank();
		bank.setBankId("95533");
		bank.setName("建设银行");
		bankManager.addNewBank(bank);

		Bank bank1 = new Bank();
		bank1.setBankId("95555");
		bank1.setName("招商银行");
		bankManager.addNewBank(bank1);
	}
}