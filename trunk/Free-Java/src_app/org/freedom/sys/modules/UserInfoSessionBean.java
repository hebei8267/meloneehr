/*
 * Copyright 2008 by hebei, All rights reserved.
 */
package org.freedom.sys.modules;

import org.freedom.core.bean.BaseBean;

/**
 * 登录用户信息
 * 
 * @author 何贝
 * @since JDK1.5
 */
public class UserInfoSessionBean extends BaseBean {

    private static final long serialVersionUID = 6644616830389518518L;

    public UserInfoSessionBean() {

    }

    /** 登录用户ID */
    private String userId;
    /** 用户名称 */
    private String userName;
    /** 登录用户角色ID */
    private String roleId;
    /** 登录用户角色名称 */
    private String roleName;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getRoleId() {
        return roleId;
    }

    public void setRoleId(String roleId) {
        this.roleId = roleId;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }
}
