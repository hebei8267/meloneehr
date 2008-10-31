/*
 * Copyright 2008 by hebei, All rights reserved.
 */
package org.freedom.services.security.impl;

import java.util.List;

import org.freedom.dao.security.RoleMenuNodePermitDao;
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
    // 接口实现
    // ---------------------------------------------------------------------------
    /**
     * 用户登录效验
     * 
     * @param userID 用户ID
     * @param password 用户密码
     * @return 成功-用户信息 失败-null object
     */
    public User userLogin_Service(String userID, String password) {
        User user = userDao.getUserByID(userID);

        if (user != null && user.getPassword().equals(password)) {
            return user;
        }
        return null;
    }

    /**
     * 修改用户密码
     * 
     * @param userID 用户ID
     * @param oldPassword 原用户密码
     * @param newPassword 新用户密码
     * @return true-修改成功 false-修改失败(用户ID或原用户密码不匹配)
     */
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

    /**
     * 删除指定菜单节点的角色关联关系
     * 
     * @param menuNodeID 菜单节点
     * @param roleList 要删除角色列表
     * @return 删除的记录行数
     */
    public int delRoleMenuNodePermit_Service(String menuNodeID, List<Role> roleList) {
        String[] roleIDs = new String[roleList.size()];
        for (int i = 0; i < roleList.size(); i++) {
            roleIDs[i] = roleList.get(i).getId();
        }
        return roleMenuNodePermitDao.delRoleMenuNodePermitByMenuNodeID(menuNodeID, roleIDs);

    }

    // ---------------------------------------------------------------------------
    // DAO
    // ---------------------------------------------------------------------------
    private UserDao userDao = null;
    private RoleMenuNodePermitDao roleMenuNodePermitDao = null;

    public UserDao getUserDao() {
        return userDao;
    }

    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }

    public RoleMenuNodePermitDao getRoleMenuNodePermitDao() {
        return roleMenuNodePermitDao;
    }

    public void setRoleMenuNodePermitDao(RoleMenuNodePermitDao roleMenuNodePermitDao) {
        this.roleMenuNodePermitDao = roleMenuNodePermitDao;
    }

}
