/*
 * Copyright 2008 by hebei, All rights reserved.
 */
package org.freedom.services.security.impl;

import org.freedom.dao.common.RoleDao;
import org.freedom.dao.common.UserDao;
import org.freedom.entity.common.Role;
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
    public boolean modUserPwdService(String userID, String oldPassword, String newPassword) {
        User user = userDao.getUserByID(userID);
        if (user == null || !user.getPassword().equals(oldPassword)) {
            return false;
        }
        user.setPassword(newPassword);
        user.setFirstLoginFlag(false);
        userDao.save(user);

        return true;
    }

    public User userLoginService(String userID, String password) {
        User user = userDao.getUserByID(userID);

        if (user != null && user.getPassword().equals(password)) {
            return user;
        }
        return null;
    }

    public Role getRoleInfoService(String roleID) {
        return roleDao.getRoleByID(roleID);

    }

    // ---------------------------------------------------------------------------
    // DAO
    // ---------------------------------------------------------------------------
    @Autowired
    private UserDao userDao = null;
    @Autowired
    private RoleDao roleDao = null;

    public UserDao getUserDao() {
        return userDao;
    }

    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }

    public RoleDao getRoleDao() {
        return roleDao;
    }

    public void setRoleDao(RoleDao roleDao) {
        this.roleDao = roleDao;
    }

}
