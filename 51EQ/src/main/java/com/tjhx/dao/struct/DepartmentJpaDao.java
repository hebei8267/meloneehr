package com.tjhx.dao.struct;

import org.springframework.data.domain.Sort;
import org.springframework.data.repository.CrudRepository;

import com.tjhx.entity.struct.Department;

public interface DepartmentJpaDao extends CrudRepository<Department, Integer> {

	@SuppressWarnings("rawtypes")
	public Iterable findAll(Sort sort);

	/**
	 * 取得部门信息
	 * 
	 * @param name 名称
	 * @return 部门信息
	 */
	public Department findByName(String name);
}
