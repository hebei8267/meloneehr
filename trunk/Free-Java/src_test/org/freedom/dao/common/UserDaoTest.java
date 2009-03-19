/*
 * Copyright 2009 by hebei, All rights reserved.
 */
package org.freedom.dao.common;

import org.freedom.core.test.BaseTestCase;
import org.freedom.entity.common.User;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author 何贝
 * @since JDK1.5
 */
public class UserDaoTest extends BaseTestCase {
    @Autowired
    private UserDao userDao;

    public UserDao getUserDao() {
        return userDao;
    }

    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }

    public void testCase1() {
        User user = userDao.getUserByID("00000001");
        System.out.println("user: " + user);
        assertNotNull(user);
    }
}
