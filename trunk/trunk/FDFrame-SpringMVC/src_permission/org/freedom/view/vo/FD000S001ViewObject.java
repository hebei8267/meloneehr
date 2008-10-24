/*
 * Copyright 2008 by hebei, All rights reserved.
 */
package org.freedom.view.vo;

/**
 * 用户登录页面ViewObject
 * 
 * @author 何贝
 * @since JDK1.5
 */
public class FD000S001ViewObject {
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

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Boolean getChangePassword() {
        return changePassword;
    }

    public void setChangePassword(Boolean changePassword) {
        this.changePassword = changePassword;
    }
}
