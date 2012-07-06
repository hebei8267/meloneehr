/*
 * Copyright 2008 by hebei, All rights reserved.
 */
package org.freedom.dao.ui;

import static org.freedom.dao.CreateDataConstant.ROOT_PATH;

import java.io.IOException;
import java.util.List;

import org.freedom.core.test.BaseTestCase;
import org.freedom.dao.ui.MenuNodeTypeDao;
import org.freedom.entity.ui.MenuNodeType;
import org.freedom.file.CSVFileUtils;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 菜单树结点类型Dao Test
 * 
 * @author 何贝
 * @since JDK1.5
 */
public class MenuNodeTypeCreateData extends BaseTestCase {
    @Autowired
    private MenuNodeTypeDao menuNodeTypeDao;

    public MenuNodeTypeDao getMenuNodeTypeDao() {
        return menuNodeTypeDao;
    }

    public void setMenuNodeTypeDao(MenuNodeTypeDao menuNodeTypeDao) {
        this.menuNodeTypeDao = menuNodeTypeDao;
    }

    public void testCase1() throws IOException {
        List<List<String>> csvFileContent = CSVFileUtils.readCSVFile(ROOT_PATH + "MenuNodeType.csv");

        for (List<String> fileLine : csvFileContent) {
            MenuNodeType menuNodeType = new MenuNodeType();

            for (int i = 0; i < fileLine.size(); i++) {
                String value = fileLine.get(i);
                if (i == 0) {
                    menuNodeType.setMasterID(value);
                } else if (i == 1) {
                    menuNodeType.setSlaveID(value);
                } else if (i == 2) {
                    menuNodeType.setName(value);
                }
            }

            menuNodeTypeDao.save(menuNodeType);
        }
    }
}