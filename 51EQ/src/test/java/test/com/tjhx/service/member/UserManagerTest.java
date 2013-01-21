package test.com.tjhx.service.member;

import javax.annotation.Resource;

import org.junit.Test;
import org.springframework.test.annotation.Rollback;
import org.springside.modules.test.spring.SpringTransactionalTestCase;

import com.tjhx.entity.member.User;
import com.tjhx.service.member.UserManager;
import com.tjhx.service.struct.DepartmentManager;
import com.tjhx.service.struct.OrganizationManager;

public class UserManagerTest extends SpringTransactionalTestCase {
	@Resource
	private UserManager userManager;
	@Resource
	private DepartmentManager departmentManager;
	@Resource
	private OrganizationManager organizationManager;

	@Test
	@Rollback(false)
	public void saveNewUser() {
		User user = new User();
		user.setLoginName("test");
		user.setPassWord("testtest");
		user.setName("123");

		user.setDepartment(departmentManager.getDepartmentByUuid(1));
		user.setOrganization(organizationManager.getOrganizationByUuid(1));

		userManager.addNewUser(user);
	}

}
