package com.tjhx.service;

import static org.junit.Assert.assertEquals;

import javax.annotation.Resource;

import org.junit.Test;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springside.modules.test.spring.SpringTransactionalTestCase;

import com.tjhx.entity.member.User;
import com.tjhx.service.member.UserManager;

@DirtiesContext
@ContextConfiguration(locations = { "classpath*:/applicationContext.xml" })
public class UserManagerTest extends SpringTransactionalTestCase {
	@Resource
	private UserManager userManager;

	@Test
	@Rollback(false)
	public void saveNewUser() {
		User user = new User();
		user.setLoginName("test");
		user.setPassWord("test");
		user.setName("123");
		userManager.addNewUser(user);
		// assertEquals("Dolphin", "Dolphin");
	}

}
