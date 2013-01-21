package test.com.tjhx.service.struct;

import javax.annotation.Resource;

import org.junit.Test;
import org.springframework.test.annotation.Rollback;
import org.springside.modules.test.spring.SpringTransactionalTestCase;

import com.tjhx.entity.struct.Department;
import com.tjhx.service.struct.DepartmentManager;

public class DepartmentManagerTest extends SpringTransactionalTestCase {
	@Resource
	private DepartmentManager departmentManager;

	@Test
	@Rollback(false)
	public void saveNewDep() {
		Department dep = new Department();
		dep.setId("001");
		dep.setName("总部门");
		dep.setBwId("BW001");

		departmentManager.addNewDepartment(dep);
	}

}
