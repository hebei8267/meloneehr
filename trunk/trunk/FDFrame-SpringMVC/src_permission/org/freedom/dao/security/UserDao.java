/*
 * Copyright 2008 by hebei, All rights reserved.
 */
package org.freedom.dao.security;

import java.util.List;

import org.freedom.core.dao.impl.HibernateDaoImpl;
import org.freedom.entity.security.User;
import org.springframework.stereotype.Component;

/**
 * 登录用户Dao
 * 
 * @author 何贝
 * @since JDK1.5
 */
@Component("userDao")
public class UserDao extends HibernateDaoImpl<User> {
	/**
	 * 根据用户ID取得用户信息
	 * 
	 * @param userID 用户ID
	 * @return User 用户信息
	 */
	@SuppressWarnings("unchecked")
	public User getUserByID(String userID) {
		List<User> resultList = getHibernateTemplate().findByNamedQuery("User.getUserByID", userID);
		if (resultList.size() > 0) {
			return resultList.get(0);
		}
		return null;
	}

}
