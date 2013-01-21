package test.com.tjhx.service.info;

import javax.annotation.Resource;

import org.junit.Test;
import org.springframework.test.annotation.Rollback;
import org.springside.modules.test.spring.SpringTransactionalTestCase;

import com.tjhx.entity.info.BankCard;
import com.tjhx.service.info.BankCardManager;

public class BankCardManagerTest extends SpringTransactionalTestCase {
	@Resource
	private BankCardManager bankCardManager;

	@Test
	@Rollback(false)
	public void saveNewBank() {
		BankCard bankCard = new BankCard();
		bankCard.setBankId("95555");
		bankCard.setBankCardNo("1234-1234-1234-1234");
		bankCardManager.addNewBankCard(bankCard);

		BankCard bankCard1 = new BankCard();
		bankCard1.setBankId("95555");
		bankCard1.setBankCardNo("1111-2222-3333-4444");
		bankCardManager.addNewBankCard(bankCard1);
	}
}