/*
 * Copyright 2008 by hebei, All rights reserved.
 */
package org.freedom.services.security;

import org.freedom.core.test.BaseTestCase;
import org.freedom.entity.common.User;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 用户登录登出服务测试
 * 
 * @author 何贝
 * @since JDK1.5
 */
public class LoginLogoutServiceTest extends BaseTestCase {
    @Autowired
    private ILoginLogoutService loginLogoutService = null;

    public ILoginLogoutService getLoginLogoutService() {
        return loginLogoutService;
    }

    public void setLoginLogoutService(ILoginLogoutService loginLogoutService) {
        this.loginLogoutService = loginLogoutService;
    }

    @Test
    public void loginCase1() throws Exception {
        User user = loginLogoutService.userLoginService("00000001", "00000001");
        assertEquals("00000001", user.getId());
    }

    @Test
    public void loginCase2() throws Exception {
        User user = loginLogoutService.userLoginService("00000001", "00000002");
        assertEquals(null, user);
    }

    @Test
    public void loginCase3() throws Exception {
        User user = loginLogoutService.userLoginService("00000002", "00000001");
        assertEquals(null, user);
    }

    @Test
    public void getInfoCase4() throws Exception {
        User user = loginLogoutService.userLoginService("00000001", "00000001");
        assertEquals(Boolean.TRUE, user.getFirstLoginFlag());
    }

    @Test
    public void modUserPwdCase5() throws Exception {
        boolean result = loginLogoutService.modUserPwdService("00000001", "00000001", "00000001");
        assertEquals(true, result);
    }

    @Test
    public void getInfoCase6() throws Exception {
        User user = loginLogoutService.userLoginService("00000001", "00000001");
        assertEquals(Boolean.FALSE, user.getFirstLoginFlag());
    }

    @Test
    public void modUserPwdCase7() throws Exception {
        boolean result = loginLogoutService.modUserPwdService("00000002", "00000001", "00000002");
        assertEquals(false, result);
    }
}
