/*
 * Copyright 2008 by hebei, All rights reserved.
 */
package org.freedom.dao.common;

import static org.freedom.dao.DaoConstant.ROOT_PATH;

import java.io.IOException;
import java.util.List;

import org.freedom.core.test.dao.HibernateDaoTestCase;
import org.freedom.dao.common.UserDao;
import org.freedom.dao.privilege.RoleDao;
import org.freedom.entity.common.User;
import org.freedom.entity.privilege.Role;

import org.freedom.file.CSVFileUtils;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 登录用户Dao Test
 * 
 * @author 何贝
 * @since JDK1.5
 */
public class UserDaoTest extends HibernateDaoTestCase {
    @Autowired
    private UserDao userDao;
    @Autowired
    private RoleDao roleDao;

    public UserDao getUserDao() {
        return userDao;
    }

    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }

    public RoleDao getRoleDao() {
        return roleDao;
    }

    public void setRoleDao(RoleDao roleDao) {
        this.roleDao = roleDao;
    }

    @Test
    public void case1() throws IOException {
        List<List<String>> csvFileContent = CSVFileUtils.readCSVFile(ROOT_PATH + "User.csv");

        for (List<String> fileLine : csvFileContent) {

            User user = new User();

            for (int i = 0; i < fileLine.size(); i++) {

                String value = fileLine.get(i);
                if (i == 0) {
                    user.setId(value);
                } else if (i == 1) {
                    user.setName(value);
                } else if (i == 2) {
                    user.setPassword(value);
                } else if (i == 3) {
                    user.setFirstLoginFlag(Boolean.valueOf(value));
                } else if (i == 4) {
                    Role role = roleDao.getRoleByID(value);
                    user.setRole(role);
                }
            }
            userDao.save(user);
        }
    }

}
