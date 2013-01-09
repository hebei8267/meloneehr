package com.tjhx.dao.struct;

import org.springframework.data.domain.Sort;
import org.springframework.data.repository.CrudRepository;

import com.tjhx.entity.struct.Department;

public interface DepartmentJpaDao extends CrudRepository<Department, Integer> {

	@SuppressWarnings("rawtypes")
	public Iterable findAll(Sort sort);
}
