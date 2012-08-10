package com.tjhx.dao.myBatis.account;

import java.util.List;

import com.tjhx.entity.account.Role;

public interface RoleMyBatisDao {

	public List<Role> getRoleList(Role role);

}
