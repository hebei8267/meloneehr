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
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author 何贝
 * @since JDK1.5
 */
@Service("loginLogoutService")
// 默认将类中的所有函数纳入事务管理.
@Transactional
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

    @Transactional(readOnly = true)
    public User userLoginService(String userID, String password) {
        User user = userDao.getUserByID(userID);

        if (user != null && user.getPassword().equals(password)) {
            return user;
        }
        return null;
    }

    @Transactional(readOnly = true)
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
}
