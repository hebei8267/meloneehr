package test.com.tjhx.service;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

import test.com.tjhx.service.info.BankCardManagerTest;
import test.com.tjhx.service.info.BankManagerTest;
import test.com.tjhx.service.info.RegionManagerTest;
import test.com.tjhx.service.info.SupplierManagerTest;
import test.com.tjhx.service.member.RoleManagerTest;
import test.com.tjhx.service.member.UserManagerTest;
import test.com.tjhx.service.struct.OrganizationManagerTest;

@RunWith(Suite.class)
@Suite.SuiteClasses( { SupplierManagerTest.class,
	OrganizationManagerTest.class,
	UserManagerTest.class,
	BankManagerTest.class,
	BankCardManagerTest.class,
	RoleManagerTest.class,
	RegionManagerTest.class
                    })
public class AllTestCase {

	

}
