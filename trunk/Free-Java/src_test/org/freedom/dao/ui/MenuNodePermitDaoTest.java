/*
 * Copyright 2009 by hebei, All rights reserved.
 */
package org.freedom.dao.ui;

import java.util.ArrayList;
import java.util.List;

import org.freedom.core.test.BaseTestCase;
import org.freedom.entity.ui.MenuNodePermit;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author 何贝
 * @since JDK1.5
 */
public class MenuNodePermitDaoTest extends BaseTestCase {
    @Autowired
    private MenuNodePermitDao menuNodePermitDao;

    public MenuNodePermitDao getMenuNodePermitDao() {
        return menuNodePermitDao;
    }

    public void setMenuNodePermitDao(MenuNodePermitDao menuNodePermitDao) {
        this.menuNodePermitDao = menuNodePermitDao;
    }

    public void testCase1() {
        // 当前为系统管理员用户
        List<String> treeNodeIdList = menuNodePermitDao.getMenuNodePermitListByUserID("00000001");
        for (String treeNodeId : treeNodeIdList) {
            System.out.println("treeNodeId: " + treeNodeId);
        }
        assertNotNull(treeNodeIdList);
    }

    public void testCase2() {
        // 系统管理员角色
        List<MenuNodePermit> permitList = menuNodePermitDao.getMenuNodePermitListByRoleID("00000002");

        for (MenuNodePermit permit : permitList) {
            System.out.println("testCase2 permit: " + permit);
        }
        assertNotNull(permitList);
    }

    public void testCase3() {
        List<MenuNodePermit> permitList = menuNodePermitDao.getMenuNodePermitListByMenuNodeID("00000002");
        for (MenuNodePermit permit : permitList) {
            System.out.println("testCase3 permit: " + permit);
        }
        assertNotNull(permitList);
    }

    public void testCase4() {
        MenuNodePermit permit = menuNodePermitDao.getMenuNodePermitByRoleIDAndMenuNodeID("00000005", "00000002");
        System.out.println("testCase4 permit: " + permit);
        assertNotNull(permit);
    }

    public void testCase5() {
        List<String> roleIDList = new ArrayList<String>();
        roleIDList.add("00000006");
        menuNodePermitDao.delMenuNodePermitByRoleID(roleIDList);
    }

    public void testCase6() {
        List<String> list = new ArrayList<String>();
        list.add("00000006");
        menuNodePermitDao.delMenuNodePermitByMenuNodeID(list);
    }
}
