/*
 * Copyright 2008 by hebei, All rights reserved.
 */
package org.freedom.services.security;

import org.freedom.entity.common.Role;
import org.freedom.entity.common.User;

/**
 * 用户登录登出服务
 * 
 * @author 何贝
 * @since JDK1.5
 */
public interface ILoginLogoutService {
    /**
     * 取得角色信息根据其ID
     * 
     * @param roleID 角色ID
     * @return 角色信息
     */
    public Role getRoleInfoService(String roleID);

    /**
     * 修改用户密码
     * 
     * @param userID 用户ID
     * @param oldPassword 原用户密码
     * @param newPassword 新用户密码
     * @return true-修改成功 false-修改失败(用户ID或原用户密码不匹配)
     */
    public boolean modUserPwdService(String userID, String oldPassword, String newPassword);

    /**
     * 用户登录效验
     * 
     * @param userID 用户ID
     * @param password 用户密码
     * @return 成功-用户信息 失败-null
     */
    public User userLoginService(String userID, String password);
}
