/*
 * Copyright 2008 by hebei, All rights reserved.
 */
package org.freedom.view.security;

import org.freedom.core.domain.UserInfoSessionBean;
import org.freedom.core.view.AbstractViewBean;
import org.freedom.entity.security.User;
import org.freedom.services.security.ISecurityService;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import static org.freedom.view.constant.MesssageIDSecurity.ERROR_LOGIN_FAILED;
import static org.freedom.view.constant.MesssageIDSecurity.TIP_FIRST_LOGIN_FLAG;

/**
 * 用户登录FacesView
 * 
 * @author 何贝
 * @since JDK1.5
 */
@Component("Security001FView")
@Scope("request")
public class Security001FView extends AbstractViewBean {

    private static final long serialVersionUID = 3101578630313125390L;
    /** 用户名 */
    private String userId;
    /** 用户密码 */
    private String password;
    /** 变更密码 */
    private String loginFlag = Boolean.TRUE.toString();

    private ISecurityService securityService;

    @Override
    public void create() {

    }

    @Override
    public void destroy() {

    }

    @Override
    public void init() {

    }

    public String loginAction() {

        // 用户登录
        User user = securityService.userLogin_Service(userId, password);
        if (user == null) {// 输入用户名或密码错误

            addErrorMessage(ERROR_LOGIN_FAILED);

            return null;
        }

        if (user.getFirstLoginFlag().equals(Boolean.TRUE)) {// 登录成功--（第一次登录系统）

            addErrorMessage(TIP_FIRST_LOGIN_FLAG);

            // 保存Session登录用户信息
            saveUserInfoToSession(user);

            return "modPassword";
        } else {// 登录成功--画面迁移到指定页面
            if (loginFlag.equals(Boolean.TRUE.toString())) {

                // 保存Session登录用户信息
                saveUserInfoToSession(user);

                return "loginSucceed";
            } else {

                // 保存Session登录用户信息
                saveUserInfoToSession(user);

                return "modPassword";
            }
        }

    }

    /**
     * 保存Session登录用户信息
     */
    private void saveUserInfoToSession(User user) {

        UserInfoSessionBean userInfo = new UserInfoSessionBean();
        userInfo.setUserId(user.getId());
        userInfo.setUserName(user.getName());

        saveUserInfo(userInfo);
    }

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
     * 取得变更密码
     * 
     * @return 变更密码
     */
    public String getLoginFlag() {
        return loginFlag;
    }

    /**
     * 设置变更密码
     * 
     * @param loginFlag 变更密码
     */
    public void setLoginFlag(String loginFlag) {
        this.loginFlag = loginFlag;
    }

    public ISecurityService getSecurityService() {
        return securityService;
    }

    public void setSecurityService(ISecurityService securityService) {
        this.securityService = securityService;
    }
}
