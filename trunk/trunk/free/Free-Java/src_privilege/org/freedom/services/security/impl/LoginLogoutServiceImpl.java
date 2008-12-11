/*
 * Copyright 2008 by hebei, All rights reserved.
 */
package org.freedom.services.security.impl;

import org.freedom.dao.common.UserDao;
import org.freedom.entity.common.User;
import org.freedom.services.security.ILoginLogoutService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 * @author 何贝
 * @since JDK1.5
 */
@Component("loginLogoutService")
@Scope("prototype")
public class LoginLogoutServiceImpl implements ILoginLogoutService {
    // ---------------------------------------------------------------------------
    // 接口实现
    // ---------------------------------------------------------------------------
    /**
     * 用户登录效验
     * 
     * @param userID 用户ID
     * @param password 用户密码
     * @return 成功-用户信息 失败-null
     */
    public boolean modUserPwdService(String userID, String oldPassword, String newPassword) {
        User user = userDao.getUserByID(userID);
        if (user == null || !user.getPassword().equals(oldPassword)) {
            return false;
        }
        user.setPassword(newPassword);
        user.setFirstLoginFlag(Boolean.FALSE);
        userDao.save(user);

        return true;
    }

    /**
     * 修改用户密码
     * 
     * @param userID 用户ID
     * @param oldPassword 原用户密码
     * @param newPassword 新用户密码
     * @return true-修改成功 false-修改失败(用户ID或原用户密码不匹配)
     */
    public User userLoginService(String userID, String password) {
        User user = userDao.getUserByID(userID);

        if (user != null && user.getPassword().equals(password)) {
            return user;
        }
        return null;
    }

    // ---------------------------------------------------------------------------
    // DAO
    // ---------------------------------------------------------------------------
    @Autowired
    private UserDao userDao = null;

    public UserDao getUserDao() {
        return userDao;
    }

    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }
}
