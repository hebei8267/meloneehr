package org.freedom.dao.ui;

/*
 * Copyright 2008 by hebei, All rights reserved.
 */
import static org.freedom.dao.DaoConstant.ROOT_PATH;

import java.io.IOException;
import java.util.List;

import org.freedom.core.test.dao.HibernateDaoTestCase;
import org.freedom.dao.security.RoleDao;
import org.freedom.dao.ui.MenuNodeDao;
import org.freedom.dao.ui.MenuNodePermitDao;
import org.freedom.entity.security.Role;
import org.freedom.entity.ui.MenuNode;
import org.freedom.entity.ui.MenuNodePermit;
import org.freedom.file.CSVFileUtils;

/**
 * 登录用户角色可访问菜单树结点权限Dao Test
 * 
 * @author 何贝
 * @since JDK1.5
 */
public class MenuNodePermitDaoTest extends HibernateDaoTestCase {
    private MenuNodePermitDao menuNodePermitDao;
    private RoleDao roleDao;
    private MenuNodeDao menuNodeDao;

    public MenuNodePermitDao getMenuNodePermitDao() {
        return menuNodePermitDao;
    }

    public void setMenuNodePermitDao(MenuNodePermitDao menuNodePermitDao) {
        this.menuNodePermitDao = menuNodePermitDao;
    }

    public RoleDao getRoleDao() {
        return roleDao;
    }

    public void setRoleDao(RoleDao roleDao) {
        this.roleDao = roleDao;
    }

    public MenuNodeDao getMenuNodeDao() {
        return menuNodeDao;
    }

    public void setMenuNodeDao(MenuNodeDao menuNodeDao) {
        this.menuNodeDao = menuNodeDao;
    }

    public void testCase() throws IOException {
        List<List<String>> csvFileContent = CSVFileUtils.readCSVFile(ROOT_PATH + "MenuNodePermit.csv");

        for (List<String> fileLine : csvFileContent) {

            MenuNodePermit rolePermit = new MenuNodePermit();

            for (int i = 0; i < fileLine.size(); i++) {

                String value = fileLine.get(i);
                if (i == 0) {
                    MenuNode menuNode = menuNodeDao.getMenuNodeByID(value);
                    rolePermit.setMenuNode(menuNode);
                } else if (i == 1) {
                    Role role = roleDao.getRoleByID(value);
                    rolePermit.setRole(role);
                }
            }
            menuNodePermitDao.save(rolePermit);
        }
    }
}
