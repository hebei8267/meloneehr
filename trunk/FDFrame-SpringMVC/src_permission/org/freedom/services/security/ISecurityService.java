/*
 * Copyright 2008 by hebei, All rights reserved.
 */
package org.freedom.services.security;

import java.util.List;

import org.freedom.entity.security.Role;
import org.freedom.entity.security.User;

/**
 * 系统安全相关服务
 * 
 * @author 何贝
 * @since JDK1.5
 */
public interface ISecurityService {
    /**
     * 用户登录效验
     * 
     * @param userID 用户ID
     * @param password 用户密码
     * @return 成功-用户信息 失败-null object
     */
    public User userLogin_Service(String userID, String password);

    /**
     * 修改用户密码
     * 
     * @param userID 用户ID
     * @param oldPassword 原用户密码
     * @param newPassword 新用户密码
     * @return true-修改成功 false-修改失败(用户ID或原用户密码不匹配)
     */
    public boolean modUserPassword_Service(String userID, String oldPassword, String newPassword);

    /**
     * 取得所有角色列表信息
     * 
     * @return 角色列表信息
     */
    public List<Role> getAllRoleInfoList_Service();
}
