package com.tjhx.service.member;

import java.util.List;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tjhx.dao.member.EmployeeJpaDao;
import com.tjhx.dao.member.EmployeeMyBatisDao;
import com.tjhx.entity.member.Employee;

@Service
@Transactional(readOnly = true)
public class EmployeeManager {
	@SuppressWarnings("unused")
	private static Logger logger = LoggerFactory.getLogger(EmployeeManager.class);

	@Resource
	private EmployeeJpaDao empJpaDao;

	@Resource
	private EmployeeMyBatisDao empMyBatisDao;

	/**
	 * 取得本机构的兼职人员信息
	 * 
	 * @param orgId
	 * @return
	 */
	public List<Employee> getTmpEmployeeByOrgId(String orgId) {
		List<Employee> empList = empMyBatisDao.getTmpEmployeeListByOrgId(orgId);

		return empList;
	}

	@Transactional(readOnly = false)
	public void updateTmpEmployeeList(String orgId, List<Employee> empList) {
		List<Employee> _dbEmpList = empJpaDao.getTmpEmployeeListByOrgId(orgId);

		for (Employee dbEmployee : _dbEmpList) {
			Employee emp = myEquals(dbEmployee, empList);
			if (null != emp) {
				dbEmployee.setName(emp.getName());
				dbEmployee.setWorkFlg(emp.getWorkFlg());

				empJpaDao.save(dbEmployee);
			}

		}
	}

	private Employee myEquals(Employee dbEmployee, List<Employee> empList) {
		for (Employee employee : empList) {
			if (dbEmployee.getUuid().equals(employee.getUuid())) {
				return employee;
			}
		}
		return null;
	}

}
