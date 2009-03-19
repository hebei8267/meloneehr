/*
 * Copyright 2009 by hebei, All rights reserved.
 */
package org.freedom.dao.common;

import java.util.ArrayList;
import java.util.List;

import org.freedom.core.test.BaseTestCase;
import org.freedom.entity.common.Role;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author 何贝
 * @since JDK1.5
 */
public class RoleDaoTest extends BaseTestCase {
    @Autowired
    private RoleDao roleDao;

    public RoleDao getRoleDao() {
        return roleDao;
    }

    public void setRoleDao(RoleDao roleDao) {
        this.roleDao = roleDao;
    }

    public void testCase1() {
        Role role = roleDao.getRoleByID("00000002");
        System.out.println("role: " + role);
        assertNotNull(role);
    }

    public void testCase2() {
        String maxID = roleDao.getMaxID();
        System.out.println("maxID: " + maxID);
        assertNotNull(maxID);
    }

    public void testCase3() {
        List<String> list = new ArrayList<String>();
        list.add("00000002");

        Long count = roleDao.getRole4UserCount(list);
        System.out.println("count: " + count);
        assertNotNull(count);
    }

    public void testCase4() {
        List<Role> roleList = roleDao.getRoleListByMenuNodeID("000000031");
        for (Role role : roleList) {
            System.out.println("Role ID: " + role.getId());
        }
        assertNotNull(roleList);
    }
}
