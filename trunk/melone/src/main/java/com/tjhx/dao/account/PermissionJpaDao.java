package com.tjhx.dao.account;

import org.springframework.data.repository.CrudRepository;

import com.tjhx.entity.account.Permission;

public interface PermissionJpaDao extends CrudRepository<Permission, Integer> {

}
