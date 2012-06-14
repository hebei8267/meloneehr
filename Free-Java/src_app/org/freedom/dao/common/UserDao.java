/*
 * Copyright 2008 by hebei, All rights reserved.
 */
package org.freedom.dao.common;

import org.freedom.core.orm.hibernate.HibernateDao;
import org.freedom.entity.common.User;

import org.springframework.stereotype.Repository;

/**
 * 登录用户Dao
 * 
 * @author 何贝
 * @since JDK1.5
 */
@Repository("userDao")
public class UserDao extends HibernateDao<User> {
    /**
     * 根据用户ID取得用户信息
     * 
     * @param userID 用户ID
     * @return User 用户信息
     */
    public User getUserByID(String userID) {
        return (User) findUniqueByNamedQuery("User.getUserByID", userID);
    }
}
