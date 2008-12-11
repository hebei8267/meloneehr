/*
 * Copyright 2008 by hebei, All rights reserved.
 */
package org.freedom.dao.privilege;

import static org.freedom.dao.DaoConstant.ROOT_PATH;

import java.io.IOException;
import java.util.List;

import org.freedom.core.test.dao.HibernateDaoTestCase;
import org.freedom.dao.privilege.RoleDao;
import org.freedom.entity.privilege.Role;

import org.freedom.file.CSVFileUtils;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 登录用户角色Dao Test
 * 
 * @author 何贝
 * @since JDK1.5
 */
public class RoleDaoTest extends HibernateDaoTestCase {
    @Autowired
    private RoleDao roleDao;

    public RoleDao getRoleDao() {
        return roleDao;
    }

    public void setRoleDao(RoleDao roleDao) {
        this.roleDao = roleDao;
    }

    @Test
    public void case1() throws IOException {
        List<List<String>> csvFileContent = CSVFileUtils.readCSVFile(ROOT_PATH + "Role.csv");

        for (List<String> fileLine : csvFileContent) {

            Role role = new Role();

            for (int i = 0; i < fileLine.size(); i++) {

                String value = fileLine.get(i);
                if (i == 0) {
                    role.setId(value);
                } else if (i == 1) {
                    role.setName(value);
                }
            }
            roleDao.save(role);
        }
    }
}
