package test.com.tjhx.service.struct;

import javax.annotation.Resource;

import org.junit.Test;
import org.springframework.test.annotation.Rollback;
import org.springside.modules.test.spring.SpringTransactionalTestCase;

import com.tjhx.entity.struct.Organization;
import com.tjhx.service.struct.OrganizationManager;

public class OrganizationManagerTest extends SpringTransactionalTestCase {

	@Resource
	private OrganizationManager organizationManager;

	@Test
	@Rollback(false)
	public void saveNewOrg() {
		Organization org = new Organization();
		org.setName("总部");
		org.setBwId("00D");

		organizationManager.addNewOrganization(org);
	}
}
