package com.tjhx.dao.myBatis.account;

public interface PermissionMyBatisDao {

	/**
	 * 删除原有拥有菜单资源
	 * 
	 * @param roleUuid
	 */
	public void delPermissionByRoleUuid(Integer roleUuid);

}
