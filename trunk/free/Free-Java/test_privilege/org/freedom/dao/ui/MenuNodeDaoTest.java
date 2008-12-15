/*
 * Copyright 2008 by hebei, All rights reserved.
 */
package org.freedom.dao.ui;

import static org.freedom.dao.DaoConstant.ROOT_PATH;

import java.io.IOException;
import java.util.List;

import org.freedom.core.test.BaseTestCase2;
import org.freedom.dao.ui.MenuNodeDao;
import org.freedom.entity.ui.MenuNode;
import org.freedom.file.CSVFileUtils;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 菜单树结点Dao Test
 * 
 * @author 何贝
 * @since JDK1.5
 */
public class MenuNodeDaoTest extends BaseTestCase2 {
    @Autowired
    private MenuNodeDao menuNodeDao;

    public MenuNodeDao getMenuNodeDao() {
        return menuNodeDao;
    }

    public void setMenuNodeDao(MenuNodeDao menuNodeDao) {
        this.menuNodeDao = menuNodeDao;
    }

    // public void testCase() throws IOException {
    // MenuNode menuNode = menuNodeDao.get(8);
    //
    // print(menuNode);
    //
    // MenuNode parent = menuNode.getParentNode();
    // if (parent != null) {
    // print(parent);
    // }
    // }

    // private void print(MenuNode menuNode) {
    // System.out.println(menuNode.getId());
    // System.out.println(menuNode.getNodeTxt());
    // }

    @Test
    public void testCase1() throws IOException {
        List<List<String>> csvFileContent = CSVFileUtils.readCSVFile(ROOT_PATH + "MenuNode.csv");

        for (List<String> fileLine : csvFileContent) {
            MenuNode menuNode = new MenuNode();

            for (int i = 0; i < fileLine.size(); i++) {
                String value = fileLine.get(i);
                if (i == 0) {
                    menuNode.setId(value);
                } else if (i == 1) {
                    menuNode.setNodeTxt(value);
                } else if (i == 2) {
                    menuNode.setNodeType(value);
                } else if (i == 3) {
                    menuNode.setDefaultPermit(Boolean.valueOf(value));
                } else if (i == 4) {
                    menuNode.setActionContent(value);
                } else if (i == 5) {
                    MenuNode parentNode = menuNodeDao.getMenuNodeByID(value);

                    parentNode.addChildNode(menuNode);

                    menuNode.setParentNode(parentNode);
                }
            }

            menuNodeDao.save(menuNode);
        }
    }

}
