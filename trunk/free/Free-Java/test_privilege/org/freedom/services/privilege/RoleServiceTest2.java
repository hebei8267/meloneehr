/*
 * Copyright 2008 by hebei, All rights reserved.
 */
package org.freedom.services.privilege;

import org.freedom.core.test.BaseTestCase2;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 角色对象相关服务测试
 * 
 * @author 何贝
 * @since JDK1.5
 */
public class RoleServiceTest2 extends BaseTestCase2 {
    @Autowired
    private IRoleService roleService = null;

    public IRoleService getRoleService() {
        return roleService;
    }

    public void setRoleService(IRoleService roleService) {
        this.roleService = roleService;
    }

    @Test
    public void testDelRoleCase5() throws Exception {
        boolean result = roleService.delRoleInfoService("00000001");
        assertEquals(false, result);
    }
}
