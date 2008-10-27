package test.org.freedom.dao.security;

/*
 * Copyright 2008 by hebei, All rights reserved.
 */
import static test.org.freedom.dao.DaoConstant.ROOT_PATH;

import java.io.IOException;
import java.util.List;

import org.freedom.core.test.dao.HibernateDaoTestCase;
import org.freedom.dao.security.RoleDao;
import org.freedom.dao.security.RoleMenuNodePermitDao;
import org.freedom.dao.ui.MenuNodeDao;
import org.freedom.entity.security.Role;
import org.freedom.entity.security.RoleMenuNodePermit;
import org.freedom.entity.ui.MenuNode;
import org.freedom.file.CSVFileUtils;

/**
 * 登录用户角色可访问菜单树结点权限Dao Test
 * 
 * @author 何贝
 * @since JDK1.5
 */
public class RoleMenuNodePermitDaoTest extends HibernateDaoTestCase {
    private RoleMenuNodePermitDao roleMenuNodePermitDao;
    private RoleDao roleDao;
    private MenuNodeDao menuNodeDao;

    public RoleMenuNodePermitDao getRoleMenuNodePermitDao() {
        return roleMenuNodePermitDao;
    }

    public void setRoleMenuNodePermitDao(RoleMenuNodePermitDao roleMenuNodePermitDao) {
        this.roleMenuNodePermitDao = roleMenuNodePermitDao;
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
        List<List<String>> csvFileContent = CSVFileUtils.readCSVFile(ROOT_PATH + "RoleMenuNodePermit.csv");

        for (List<String> fileLine : csvFileContent) {

            RoleMenuNodePermit rolePermit = new RoleMenuNodePermit();

            for (int i = 0; i < fileLine.size(); i++) {

                String value = fileLine.get(i);
                if (i == 0) {
                    MenuNode menuNode = menuNodeDao.getMenuNodeByID(value);
                    rolePermit.setMenuNode(menuNode);
                } else if (i == 1) {
                    Role role = roleDao.getRoleByID(value);
                    rolePermit.setRole(role);
                } else if (i == 2) {
                    rolePermit.setHoldRoleID(value);
                }
            }
            roleMenuNodePermitDao.save(rolePermit);
        }
    }
}
