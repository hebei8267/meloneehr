/*
 * Copyright 2008 by hebei, All rights reserved.
 */
package org.freedom.services.privilege;

import org.freedom.core.test.BaseTestCase;
import org.freedom.entity.common.Role;
import org.freedom.services.permit.IRoleService;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 角色对象相关服务测试
 * 
 * @author 何贝
 * @since JDK1.5
 */
public class RoleServiceTest extends BaseTestCase {
    @Autowired
    private IRoleService roleService = null;

    public IRoleService getRoleService() {
        return roleService;
    }

    public void setRoleService(IRoleService roleService) {
        this.roleService = roleService;
    }

    @Test
    public void addRoleCase1() throws Exception {
        Role role = new Role();
        role.setName("根节点下角色");
        boolean result = roleService.addRoleInfoService(role);
        assertEquals(true, result);
    }

    @Test
    public void addRoleCase2() throws Exception {
        Role role = new Role();
        role.setName("系统管理员下角色");
        boolean result = roleService.addRoleInfoService(role, Role.ADMIN_ROLE_ID);
        assertEquals(true, result);
    }

    @Test
    public void addRoleCase3() throws Exception {

        Role role1 = new Role();
        role1.setName("添加失败的角色");
        boolean result1 = roleService.addRoleInfoService(role1, "");
        assertEquals(false, result1);
    }

    @Test
    public void addRoleCase4() throws Exception {
        Role role = new Role();
        role.setId("00000007");
        role.setName("修改后的角色");
        role.setDetail("修改后的描述");
        boolean result = roleService.modRoleInfoService(role);
        assertEquals(true, result);
    }

}
