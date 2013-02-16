package test.com.tjhx.service.member;

import javax.annotation.Resource;

import org.junit.Test;
import org.springframework.test.annotation.Rollback;
import org.springside.modules.test.spring.SpringTransactionalTestCase;

import com.tjhx.entity.member.User;
import com.tjhx.service.member.UserManager;
import com.tjhx.service.struct.OrganizationManager;

public class UserManagerTest extends SpringTransactionalTestCase {
	@Resource
	private UserManager userManager;
	@Resource
	private OrganizationManager organizationManager;

	@Test
	@Rollback(false)
	public void saveNewUser() {
		User user = new User();
		user.setLoginName("admin");
		user.setName("系统管理员");

		user.setOrgUuid("1");
		user.setRoleUuid("1");

		userManager.addNewUser(user);
	}

}