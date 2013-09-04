package com.tjhx.service.member;

import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tjhx.dao.member.EmployeeJpaDao;
import com.tjhx.dao.member.EmployeeMyBatisDao;
import com.tjhx.daozk.UserInfoMyBatisDao;
import com.tjhx.entity.member.Employee;
import com.tjhx.entity.zknet.UserInfo;

@Service
@Transactional(readOnly = true)
public class EmployeeManager {
	@SuppressWarnings("unused")
	private static Logger logger = LoggerFactory.getLogger(EmployeeManager.class);

	@Resource
	private EmployeeJpaDao empJpaDao;
	@Resource
	private EmployeeMyBatisDao empMyBatisDao;
	@Resource
	private UserInfoMyBatisDao userInfoMyBatisDao;

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

	public List<Employee> getEmployeeByOrgId(String orgId) {
		if (StringUtils.isBlank(orgId)) {
			return empMyBatisDao.getEmployeeList();
		} else {
			return empMyBatisDao.getEmployeeListByOrgId(orgId);
		}
	}

	/**
	 * 同步中控打卡数据库数据
	 */
	public void zkDataSyn() {
		empMyBatisDao.deleteEmployeeByEmpType("1");

		List<UserInfo> userList = userInfoMyBatisDao.getUserInfoList();
		for (UserInfo userInfo : userList) {
			Employee emp = new Employee();

			emp.setZkid(userInfo.getUserid());
			emp.setCode(userInfo.getBadgenumber());
			emp.setName(userInfo.getName());
			emp.setZkOrgId(userInfo.getDefaultdeptid());
			emp.setEmpType("1");
			emp.setWorkFlg("1");

			empJpaDao.save(emp);

		}

	}

}
