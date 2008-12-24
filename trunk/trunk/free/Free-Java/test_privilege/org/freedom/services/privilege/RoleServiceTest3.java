/*
 * Copyright 2008 by hebei, All rights reserved.
 */
package org.freedom.services.privilege;

import org.freedom.core.domain.TreeNode;
import org.freedom.core.test.BaseTestCase;
import org.freedom.services.permit.IRoleService;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 角色对象相关服务测试
 * 
 * @author 何贝
 * @since JDK1.5
 */
public class RoleServiceTest3 extends BaseTestCase {
    @Autowired
    private IRoleService roleService = null;

    public IRoleService getRoleService() {
        return roleService;
    }

    public void setRoleService(IRoleService roleService) {
        this.roleService = roleService;
    }

    @Test
    public void delRoleCase1() throws Exception {
        TreeNode treeNode = roleService.getAllRoleInfoTreeService();
        System.out.println(treeNode);
    }
}
