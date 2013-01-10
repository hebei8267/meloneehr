package test.com.tjhx.service;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

import test.com.tjhx.service.info.GoodsSupplierManagerTest;
import test.com.tjhx.service.member.UserManagerTest;
import test.com.tjhx.service.struct.DepartmentManagerTest;
import test.com.tjhx.service.struct.OrganizationManagerTest;

@RunWith(Suite.class)
@Suite.SuiteClasses( { GoodsSupplierManagerTest.class,
	DepartmentManagerTest.class,
	OrganizationManagerTest.class,
	UserManagerTest.class
                    })
public class AllTestCase {

	

}
