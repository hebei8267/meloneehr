/*
 * Copyright 2008 by hebei, All rights reserved.
 */
package org.freedom.view.module.security.s002;

import org.freedom.sys.view.AbstractViewObject;

/**
 * @author 何贝
 * @since JDK1.5
 */
public class Security002ViewObject extends AbstractViewObject {
    /**
     * 
     */
    private static final long serialVersionUID = 1170320091705819632L;
    /**
     * 用户名
     */
    private String userId;
    /**
     * 原密码
     */
    private String oldPassword;
    /**
     * 新密码
     */
    private String newPassword;
    /**
     * 新密码(确认用)
     */
    private String confirmPassword;

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
     * 取得原密码
     * 
     * @return 原密码
     */
    public String getOldPassword() {
        return oldPassword;
    }

    /**
     * 设置原密码
     * 
     * @param oldPassword 原密码
     */
    public void setOldPassword(String oldPassword) {
        this.oldPassword = oldPassword;
    }

    /**
     * 取得新密码
     * 
     * @return 新密码
     */
    public String getNewPassword() {
        return newPassword;
    }

    /**
     * 设置新密码
     * 
     * @param newPassword 新密码
     */
    public void setNewPassword(String newPassword) {
        this.newPassword = newPassword;
    }

    /**
     * 取得新密码(确认用)
     * 
     * @return 新密码(确认用)
     */
    public String getConfirmPassword() {
        return confirmPassword;
    }

    /**
     * 设置新密码(确认用)
     * 
     * @param confirmPassword 新密码(确认用)
     */
    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }

}
