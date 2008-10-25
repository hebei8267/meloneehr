/*
 * Copyright 2008 by hebei, All rights reserved.
 */
package org.freedom.services.security.impl;

import org.freedom.dao.security.UserDao;
import org.freedom.entity.security.User;
import org.freedom.services.security.ISecurityService;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 * 系统安全相关服务
 * 
 * @author 何贝
 * @since JDK1.5
 */
@Component("securityService")
@Scope("prototype")
public class SecurityServiceImpl implements ISecurityService {
    // ---------------------------------------------------------------------------
    // 接口实现
    // ---------------------------------------------------------------------------
    public User userLogin_Service(String userID, String password) {
        User user = userDao.getUserByID(userID);

        if (user != null && user.getPassword().equals(password)) {
            return user;
        }
        return null;
    }

    public boolean modUserPassword_Service(String userID, String password) {
        User user = userDao.getUserByID(userID);
        if (user == null) {
            return false;
        }
        user.setPassword(password);
        user.setFirstLoginFlag(Boolean.FALSE);
        userDao.save(user);

        return true;
    }

    // ---------------------------------------------------------------------------
    // DAO
    // ---------------------------------------------------------------------------
    private UserDao userDao = null;

    public UserDao getUserDao() {
        return userDao;
    }

    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }

}
