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
		org.setId("001");
		org.setName("总机构");
		org.setBwId("BW001");

		organizationManager.addNewOrganization(org);
	}
}
