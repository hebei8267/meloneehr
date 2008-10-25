/*
 * Copyright 2008 by hebei, All rights reserved.
 */
package org.freedom.view.vo.security;

import org.freedom.core.view.vo.AbstractViewObject;

/**
 * 用户登录页面ViewObject
 * 
 * @author 何贝
 * @since JDK1.5
 */
public class FD000S001ViewObject extends AbstractViewObject {

    private static final long serialVersionUID = -990861134499425659L;
    /**
     * 用户名
     */
    private String userId;
    /**
     * 用户密码
     */
    private String password;
    /**
     * 是否修改密码标记
     */
    private Boolean changePassword = Boolean.FALSE;

    /**
     * 取得用户名
     * 
     * @return 用户名
     */
    public String getUserId() {
        return userId;
    }

    /**
     * 设置用户名
     * 
     * @param userId 用户名
     */
    public void setUserId(String userId) {
        this.userId = userId;
    }

    /**
     * 取得用户密码
     * 
     * @return 用户密码
     */
    public String getPassword() {
        return password;
    }

    /**
     * 设置用户密码
     * 
     * @param password 用户密码
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * 取得是否修改密码标记
     * 
     * @return 是否修改密码标记
     */
    public Boolean getChangePassword() {
        return changePassword;
    }

    /**
     * 设置是否修改密码标记
     * 
     * @param changePassword 是否修改密码标记
     */
    public void setChangePassword(Boolean changePassword) {
        this.changePassword = changePassword;
    }
}
