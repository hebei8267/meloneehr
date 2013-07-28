package com.tjhx.dao.member;

import org.springframework.data.repository.CrudRepository;

import com.tjhx.entity.member.Employee;

public interface EmployeeJpaDao extends CrudRepository<Employee, Integer> {

}
