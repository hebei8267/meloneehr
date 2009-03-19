/*
 * Copyright 2008 by hebei, All rights reserved.
 */
package org.freedom.services.permit;

import org.freedom.core.test.BaseTestCase;
import org.freedom.services.permit.IRoleService;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 角色对象相关服务测试
 * 
 * @author 何贝
 * @since JDK1.5
 */
public class RoleServiceTest2 extends BaseTestCase {
    @Autowired
    private IRoleService roleService = null;

    public IRoleService getRoleService() {
        return roleService;
    }

    public void setRoleService(IRoleService roleService) {
        this.roleService = roleService;
    }

    public void testDelRoleCase1() throws Exception {
        int result = roleService.delRoleInfoService("00000003", 0);
        System.out.println(result);
        result = roleService.delRoleInfoService("00000003", 0);
        System.out.println(result);
        assertNotNull(null);
    }

    // 只能成功一次(数据已经删除)
    public void delRoleCase2() throws Exception {
        int result = roleService.delRoleInfoService("00000003", 0);
        assertEquals(1, result);
    }
}
