/*
 * Copyright 2008 by hebei, All rights reserved.
 */
package org.freedom.dao.common;

import static org.freedom.dao.CreateDataConstant.ROOT_PATH;

import java.io.IOException;
import java.util.List;

import org.freedom.core.test.BaseTestCase;
import org.freedom.dao.common.RoleDao;
import org.freedom.entity.common.Role;

import org.freedom.file.CSVFileUtils;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 登录用户角色Dao Test
 * 
 * @author 何贝
 * @since JDK1.5
 */
public class RoleCreateData extends BaseTestCase {
    @Autowired
    private RoleDao roleDao;

    public RoleDao getRoleDao() {
        return roleDao;
    }

    public void setRoleDao(RoleDao roleDao) {
        this.roleDao = roleDao;
    }

    public void testCase1() throws IOException {
        List<List<String>> csvFileContent = CSVFileUtils.readCSVFile(ROOT_PATH + "Role.csv");

        for (List<String> fileLine : csvFileContent) {

            Role role = new Role();

            for (int i = 0; i < fileLine.size(); i++) {

                String value = fileLine.get(i);
                if (i == 0) {
                    role.setId(value);
                } else if (i == 1) {
                    role.setName(value);
                } else if (i == 2) {
                    Role parentRole = roleDao.getRoleByID(value);
                    role.setParentRole(parentRole);
                }
            }
            roleDao.save(role);
        }
    }
}