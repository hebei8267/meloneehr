package com.tjhx.service.account;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tjhx.dao.jpa.account.UserJpaDao;
import com.tjhx.entity.account.Role;
import com.tjhx.entity.account.User;
import com.tjhx.service.ServiceException;

/**
 * 账户相关管理类
 */
@Service
@Transactional(readOnly = true)
public class AccountManager {
	private UserJpaDao userJpaDao;

	/**
	 * 取得所有用户信息
	 * 
	 * @return 用户信息列表
	 */
	public List<User> getAllUser() {
		return (List<User>) userJpaDao.findAll(new Sort(new Sort.Order(Sort.Direction.ASC, "uuid")));
	}

	/**
	 * 取得用户信息
	 * 
	 * @param loginName 登录名称
	 * @return 用户信息
	 */
	public User findByLoginName(String loginName) {
		return userJpaDao.findByLoginName(loginName);
	}

	/**
	 * 删除用户信息
	 * 
	 * @param uuid User编号
	 */
	@Transactional(readOnly = false)
	public void delUserByUuid(Integer uuid) {
		userJpaDao.delete(uuid);
	}

	/**
	 * 更新用户信息
	 * 
	 * @param user 用户信息
	 * @param role 角色名称
	 */
	@Transactional(readOnly = false)
	public void updateUser(User user, Role role) {
		User _user = userJpaDao.findOne(user.getUuid());
		if (null == _user) {
			// TODO 用户不存在
			throw new ServiceException();
		}
		user.setRole(role);
		userJpaDao.save(user);
	}

	/**
	 * 添加新用户信息
	 * 
	 * @param user 用户信息
	 * @param role 角色名称
	 */
	@Transactional(readOnly = false)
	public void saveNewUser(User user, Role role) {
		User _user = findByLoginName(user.getLoginName());
		if (null != _user) {
			// TODO 用户登录名重复
			throw new ServiceException();
		}
		user.setRole(role);
		userJpaDao.save(user);
	}

	@Autowired
	public void setUserJpaDao(UserJpaDao userJpaDao) {
		this.userJpaDao = userJpaDao;
	}
}
