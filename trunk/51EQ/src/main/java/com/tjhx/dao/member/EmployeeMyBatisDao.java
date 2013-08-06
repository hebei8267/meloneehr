package com.tjhx.dao.member;

import java.util.List;

import com.tjhx.entity.member.Employee;

public interface EmployeeMyBatisDao {

	public List<Employee> getEmployeeListByOrgId(String orgId);

}
