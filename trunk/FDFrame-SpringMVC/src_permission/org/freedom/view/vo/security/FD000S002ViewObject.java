/*
 * Copyright 2008 by hebei, All rights reserved.
 */
package org.freedom.view.vo.security;

import org.freedom.core.view.vo.AbstractViewObject;

/**
 * 用户密码变更ViewObject
 * 
 * @author 何贝
 * @since JDK1.5
 */
public class FD000S002ViewObject extends AbstractViewObject {

    private static final long serialVersionUID = 5744552413996915739L;
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
    private String newPassword2;

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
    public String getNewPassword2() {
        return newPassword2;
    }

    /**
     * 设置新密码(确认用)
     * 
     * @param newPassword2 新密码(确认用)
     */
    public void setNewPassword2(String newPassword2) {
        this.newPassword2 = newPassword2;
    }
}
