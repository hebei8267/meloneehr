/*
 * Copyright 2008 by hebei, All rights reserved.
 */
package org.freedom.services.security.impl;

import java.util.ArrayList;
import java.util.List;

import org.freedom.dao.security.RoleDao;
import org.freedom.dao.security.UserDao;
import org.freedom.entity.security.Role;
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
    // DAO
    // ---------------------------------------------------------------------------
    private UserDao userDao = null;
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

    // ---------------------------------------------------------------------------
    // 接口实现
    // ---------------------------------------------------------------------------
    public List<Role> getAllRoleInfoList_Service() {
        List<Role> dbRoleList = roleDao.getAll();

        List<Role> _resultRoleList = new ArrayList<Role>();

        for (Role dbRole : dbRoleList) {
            Role _role = new Role();

            _role.setId(dbRole.getId());
            _role.setName(dbRole.getName());

            _resultRoleList.add(_role);
        }

        return _resultRoleList;
    }

    public boolean modUserPassword_Service(String userID, String oldPassword, String newPassword) {
        User user = userDao.getUserByID(userID);
        if (user == null || !user.getPassword().equals(oldPassword)) {
            return false;
        }
        user.setPassword(newPassword);
        user.setFirstLoginFlag(Boolean.FALSE);
        userDao.save(user);

        return true;
    }

    public User userLogin_Service(String userID, String password) {
        User user = userDao.getUserByID(userID);

        if (user != null && user.getPassword().equals(password)) {
            return user;
        }
        return null;
    }
}
