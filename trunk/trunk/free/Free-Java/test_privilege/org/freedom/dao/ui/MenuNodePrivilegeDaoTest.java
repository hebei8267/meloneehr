package org.freedom.dao.ui;

/*
 * Copyright 2008 by hebei, All rights reserved.
 */
import static org.freedom.dao.DaoConstant.ROOT_PATH;

import java.io.IOException;
import java.util.List;

import org.freedom.core.test.BaseTestCase;
import org.freedom.dao.privilege.RoleDao;
import org.freedom.entity.privilege.Role;
import org.freedom.entity.ui.MenuNode;
import org.freedom.entity.ui.MenuNodePrivilege;
import org.freedom.file.CSVFileUtils;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 登录用户角色可访问菜单树结点权限Dao Test
 * 
 * @author 何贝
 * @since JDK1.5
 */
public class MenuNodePrivilegeDaoTest extends BaseTestCase {
    @Autowired
    private MenuNodePrivilegeDao menuNodePrivilegeDao;
    @Autowired
    private RoleDao roleDao;
    @Autowired
    private MenuNodeDao menuNodeDao;

    public MenuNodePrivilegeDao getMenuNodePrivilegeDao() {
        return menuNodePrivilegeDao;
    }

    public void setMenuNodePrivilegeDao(MenuNodePrivilegeDao menuNodePrivilegeDao) {
        this.menuNodePrivilegeDao = menuNodePrivilegeDao;
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

    @Test
    public void case1() throws IOException {
        List<List<String>> csvFileContent = CSVFileUtils.readCSVFile(ROOT_PATH + "MenuNodePermit.csv");

        for (List<String> fileLine : csvFileContent) {

            MenuNodePrivilege rolePrivilege = new MenuNodePrivilege();

            for (int i = 0; i < fileLine.size(); i++) {

                String value = fileLine.get(i);
                if (i == 0) {
                    MenuNode menuNode = menuNodeDao.getMenuNodeByID(value);
                    rolePrivilege.setMenuNode(menuNode);
                } else if (i == 1) {
                    Role role = roleDao.getRoleByID(value);
                    rolePrivilege.setRole(role);
                }
            }
            menuNodePrivilegeDao.save(rolePrivilege);
        }
    }
}
