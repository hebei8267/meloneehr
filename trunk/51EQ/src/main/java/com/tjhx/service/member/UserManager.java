package com.tjhx.service.member;

import java.lang.reflect.InvocationTargetException;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tjhx.dao.member.UserJpaDao;
import com.tjhx.entity.member.User;
import com.tjhx.service.ServiceException;

@Service
@Transactional(readOnly = true)
public class UserManager {
	@Resource
	private UserJpaDao userJpaDao;

	/**
	 * 取得所有User信息
	 * 
	 * @return User信息列表
	 */
	@SuppressWarnings("unchecked")
	public List<User> getAllUser() {
		return (List<User>) userJpaDao.findAll(new Sort(new Sort.Order(Sort.Direction.ASC, "uuid")));
	}

	/**
	 * 根据编号取得User信息
	 * 
	 * @param uuid User编号
	 * @return User信息
	 */
	public User getUserByUuid(Integer uuid) {
		return userJpaDao.findOne(uuid);
	}

	/**
	 * 删除User信息
	 * 
	 * @param uuid User编号
	 */
	@Transactional(readOnly = false)
	public void delUserByUuid(Integer uuid) {
		userJpaDao.delete(uuid);
	}

	/**
	 * 添加新User信息
	 * 
	 * @param user User信息
	 */
	@Transactional(readOnly = false)
	public void addNewUser(User user) {

		User _dbUser = findByLoginName(user.getName());
		// 该User已存在!
		if (null != _dbUser) {
			throw new ServiceException("ERR_MSG_USER_001");
		}

		userJpaDao.save(user);
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
	 * 更新User信息
	 * 
	 * @param user User信息
	 * @throws InvocationTargetException
	 * @throws IllegalAccessException
	 */
	@Transactional(readOnly = false)
	public void updateUser(User user) throws IllegalAccessException, InvocationTargetException {
		// ----------------------------------------------------------------------------
		// TODO 修改开始
		User _dbUser = userJpaDao.findOne(user.getUuid());
		if (null == _dbUser) {
			// User不存在!
			throw new ServiceException("ERR_MSG_USER_002");
		}

		_dbUser.setName(user.getName());
		_dbUser.setDescTxt(user.getDescTxt());

		// ----------------------------------------------------------------------------
		userJpaDao.save(_dbUser);
	}
}