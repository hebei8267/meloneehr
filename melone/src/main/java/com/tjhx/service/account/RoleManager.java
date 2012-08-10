package com.tjhx.service.account;

import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tjhx.dao.jpa.account.RoleJpaDao;
import com.tjhx.dao.myBatis.account.RoleMyBatisDao;
import com.tjhx.entity.account.Permission;
import com.tjhx.entity.account.Role;
import com.tjhx.service.ServiceException;

@Service
@Transactional(readOnly = true)
public class RoleManager {
	private RoleJpaDao roleJpaDao;
	private RoleMyBatisDao roleMyBatisDao;

	/**
	 * 取得所有角色信息
	 * 
	 * @return 角色信息列表
	 */
	@SuppressWarnings("unchecked")
	public List<Role> getAllRole() {
		return (List<Role>) roleJpaDao.findAll(new Sort(new Sort.Order(Sort.Direction.ASC, "uuid")));
	}

	/**
	 * 取得角色信息(根据参数)
	 * 
	 * @param shop
	 * @return
	 */
	public List<Role> getRoleList(Role role) {
		return roleMyBatisDao.getRoleList(role);
	}

	/**
	 * 根据编号取得角色信息
	 * 
	 * @param uuid 角色编号
	 * @return 角色信息
	 */
	public Role getRoleByUuid(Integer uuid) {
		return roleJpaDao.findOne(uuid);
	}

	/**
	 * 取得角色信息
	 * 
	 * @param name 角色名称
	 * @return 角色信息
	 */
	public Role findByName(String name) {
		return roleJpaDao.findByName(name);
	}

	/**
	 * 更新角色信息
	 * 
	 * @param role 角色信息
	 * @param permissionSet 资源访问权限集合
	 */
	@Transactional(readOnly = false)
	public void updateRole(Role role, Set<Permission> permissionSet) {
		Role _role = roleJpaDao.findOne(role.getUuid());
		if (null == _role) {
			// TODO 角色不存在
			throw new ServiceException();
		}
		role.setPermissionSet(permissionSet);
		roleJpaDao.save(role);
	}

	/**
	 * 添加新角色信息
	 * 
	 * @param role 角色信息
	 * @param permissionSet 资源访问权限集合
	 */
	@Transactional(readOnly = false)
	public void saveNewRole(Role role, Set<Permission> permissionSet) {
		Role _role = findByName(role.getName());
		if (null != _role) {
			// TODO 角色名称重复
			throw new ServiceException();
		}
		role.setPermissionSet(permissionSet);
		roleJpaDao.save(role);
	}

	/**
	 * 取得角色拥有的权限
	 * 
	 * @param roleName 角色名称
	 * @return 权限列表
	 */
	public List<Permission> getPermissionByRoleName(String roleName) {
		// TODO
		return null;
	}

	/**
	 * 取得角色未拥有的权限
	 * 
	 * @param roleName 角色名称
	 * @return 权限列表
	 */
	public List<Permission> getNoPermissionByRoleName(String roleName) {
		// TODO
		return null;
	}

	@Autowired
	public void setRoleJpaDao(RoleJpaDao roleJpaDao) {
		this.roleJpaDao = roleJpaDao;
	}

	@Autowired
	public void setRoleMyBatisDao(RoleMyBatisDao roleMyBatisDao) {
		this.roleMyBatisDao = roleMyBatisDao;
	}

}
