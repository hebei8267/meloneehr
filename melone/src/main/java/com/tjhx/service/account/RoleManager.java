package com.tjhx.service.account;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tjhx.dao.jpa.account.FunctionJpaDao;
import com.tjhx.dao.jpa.account.PermissionJpaDao;
import com.tjhx.dao.jpa.account.RoleJpaDao;
import com.tjhx.entity.account.Function;
import com.tjhx.entity.account.Permission;
import com.tjhx.entity.account.Role;
import com.tjhx.service.ServiceException;

@Service
@Transactional(readOnly = true)
public class RoleManager {
	private RoleJpaDao roleJpaDao;
	private FunctionJpaDao functionJpaDao;
	private PermissionJpaDao permissionJpaDao;

	/**
	 * 取得所有功能资源信息
	 * 
	 * @return 功能资源信息列表
	 */
	public List<Function> getAllFunction() {
		return (List<Function>) functionJpaDao.findAll();
	}

	/**
	 * 取得角色信息(根据参数)
	 * 
	 * @param shop
	 * @return
	 */
	public List<Role> getRoleList(String roleName) {
		if (StringUtils.isBlank(roleName)) {
			return (List<Role>) roleJpaDao.findAll();
		} else {
			return roleJpaDao.findByNameLike("%" + roleName + "%");
		}
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
	 * 删除角色信息
	 * 
	 * @param uuid 角色编号
	 */
	@Transactional(readOnly = false)
	public void delRoleByUuid(Integer uuid) {
		roleJpaDao.delete(uuid);
	}

	/**
	 * 更新角色信息
	 * 
	 * @param role 角色信息
	 * @param permissionSet 资源访问权限集合
	 */
	@Transactional(readOnly = false)
	public void updateRole(Role role) {

		Role _role = roleJpaDao.findOne(role.getUuid());

		if (null == _role) {
			// 角色不存在
			throw new ServiceException("ERR_MSG_PDU_011");
		}

		// 删除原有菜单资源
		permissionJpaDao.delete(_role.getPermissionSet());// 执行SQL
		_role.delAllPermission();// 断开关联

		// 添加新选中菜单资源
		for (int i = 0; i < role.getFunIds().length; i++) {
			Function _fun = functionJpaDao.findOne(Integer.valueOf(role.getFunIds()[i]));

			Permission _per = new Permission();
			_per.setRole(_role);
			_per.setFunction(_fun);

			_role.addPermission(_per);
		}

		// 角色名称
		_role.setName(role.getName());
		// 角色详细描述
		_role.setDescTxt(role.getDescTxt());

		roleJpaDao.save(_role);
	}

	/**
	 * 添加新角色信息
	 * 
	 * @param role 角色信息
	 * @param funIds 资源编号集合
	 */
	@Transactional(readOnly = false)
	public void addNewRole(Role role) {

		for (int i = 0; i < role.getFunIds().length; i++) {
			Function _fun = functionJpaDao.findOne(Integer.valueOf(role.getFunIds()[i]));

			Permission _per = new Permission();
			_per.setRole(role);
			_per.setFunction(_fun);

			role.addPermission(_per);
		}
		roleJpaDao.save(role);
	}

	@Autowired
	public void setRoleJpaDao(RoleJpaDao roleJpaDao) {
		this.roleJpaDao = roleJpaDao;
	}

	@Autowired
	public void setFunctionJpaDao(FunctionJpaDao functionJpaDao) {
		this.functionJpaDao = functionJpaDao;
	}

	@Autowired
	public void setPermissionJpaDao(PermissionJpaDao permissionJpaDao) {
		this.permissionJpaDao = permissionJpaDao;
	}

}
