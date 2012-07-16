package com.tjhx.dao.account;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.tjhx.entity.account.Permission;

public interface PermissionJpaDao extends CrudRepository<Permission, Integer> {
	@Query("select perm from Permission perm left join perm.role role where role.roleName = :roleName")
	public List<Permission> getPermissionByRoleName(@Param("roleName") String roleName);
}
