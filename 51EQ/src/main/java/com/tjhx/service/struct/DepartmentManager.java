package com.tjhx.service.struct;

import java.lang.reflect.InvocationTargetException;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tjhx.dao.struct.DepartmentJpaDao;
import com.tjhx.entity.struct.Department;
import com.tjhx.service.ServiceException;

@Service
@Transactional(readOnly = true)
public class DepartmentManager {
	@Resource
	private DepartmentJpaDao departmentJpaDao;

	/**
	 * 取得所有部门信息
	 * 
	 * @return 部门信息列表
	 */
	@SuppressWarnings("unchecked")
	public List<Department> getAllDepartment() {
		return (List<Department>) departmentJpaDao.findAll(new Sort(new Sort.Order(Sort.Direction.ASC, "uuid")));
	}

	/**
	 * 根据编号取得部门信息
	 * 
	 * @param uuid 部门编号
	 * @return 部门信息
	 */
	public Department getDepartmentByUuid(Integer uuid) {
		return departmentJpaDao.findOne(uuid);
	}

	/**
	 * 删除部门信息
	 * 
	 * @param uuid 部门编号
	 */
	@Transactional(readOnly = false)
	public void delDepartmentByUuid(Integer uuid) {
		departmentJpaDao.delete(uuid);
	}

	/**
	 * 添加新部门信息
	 * 
	 * @param department 部门信息
	 */
	@Transactional(readOnly = false)
	public void addNewDepartment(Department department) {
		Department _dbDepartment = findByName(department.getName());
		// 该部门已存在!
		if (null != _dbDepartment) {
			throw new ServiceException("ERR_MSG_DEP_001");
		}
		departmentJpaDao.save(department);
	}

	/**
	 * 取得部门信息
	 * 
	 * @param name 名称
	 * @return 部门信息
	 */
	public Department findByName(String name) {
		return departmentJpaDao.findByName(name);
	}

	/**
	 * 更新部门信息
	 * 
	 * @param department 部门信息
	 * @throws InvocationTargetException
	 * @throws IllegalAccessException
	 */
	@Transactional(readOnly = false)
	public void updateDepartment(Department department) throws IllegalAccessException, InvocationTargetException {
		// ----------------------------------------------------------------------------
		// TODO 修改开始
		Department _dbDepartment = departmentJpaDao.findOne(department.getUuid());
		if (null == _dbDepartment) {
			// 部门不存在!
			throw new ServiceException("?????????????????");
		}

		_dbDepartment.setName(department.getName());

		// ----------------------------------------------------------------------------
		departmentJpaDao.save(_dbDepartment);
	}
}