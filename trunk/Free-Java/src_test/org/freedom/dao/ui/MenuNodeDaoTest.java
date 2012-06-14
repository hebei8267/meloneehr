/*
 * Copyright 2009 by hebei, All rights reserved.
 */
package org.freedom.dao.ui;

import org.freedom.core.test.BaseTestCase;
import org.freedom.entity.ui.MenuNode;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author 何贝
 * @since JDK1.5
 */
public class MenuNodeDaoTest extends BaseTestCase {
    @Autowired
    private MenuNodeDao menuNodeDao;

    public MenuNodeDao getMenuNodeDao() {
        return menuNodeDao;
    }

    public void setMenuNodeDao(MenuNodeDao menuNodeDao) {
        this.menuNodeDao = menuNodeDao;
    }

    public void testCase1() {
        MenuNode menuNode = menuNodeDao.getMenuNodeByID("00000001");
        System.out.println("menuNode: " + menuNode);
        assertNotNull(menuNode);
    }

    public void testCase2() {
        String maxID = menuNodeDao.getMaxID();
        System.out.println("maxID: " + maxID);
        assertNotNull(maxID);
    }

    public void testCase3() {
        // 没做 删除菜单节点的访问权限
        // MenuNode menuNode = menuNodeDao.getMenuNodeByID("00000002");
        // menuNodeDao.delete(menuNode);
    }
}
