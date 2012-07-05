package com.tjhx.dao.account;

import org.springframework.data.repository.CrudRepository;

import com.tjhx.entity.account.Role;

public interface RoleJpaDao extends CrudRepository<Role, Integer> {

}
