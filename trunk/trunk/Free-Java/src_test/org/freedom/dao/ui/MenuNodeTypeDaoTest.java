/*
 * Copyright 2009 by hebei, All rights reserved.
 */
package org.freedom.dao.ui;

import java.util.List;

import org.freedom.core.test.BaseTestCase;
import org.freedom.entity.ui.MenuNodeType;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author 何贝
 * @since JDK1.5
 */
public class MenuNodeTypeDaoTest extends BaseTestCase {
    @Autowired
    private MenuNodeTypeDao menuNodeTypeDao;

    public MenuNodeTypeDao getMenuNodeTypeDao() {
        return menuNodeTypeDao;
    }

    public void setMenuNodeTypeDao(MenuNodeTypeDao menuNodeTypeDao) {
        this.menuNodeTypeDao = menuNodeTypeDao;
    }

    public void testCase1() {
        List<MenuNodeType> l = menuNodeTypeDao.getMenuNodeTypeList();
        System.out.println(l.size());
    }

    public void testCase2() {
        System.out.println(menuNodeTypeDao.getMenuNodeTypeByID("area"));
    }
}
