/*
 * Copyright 2008 by hebei, All rights reserved.
 */
package test.org.freedom.dao.security;

import static test.org.freedom.dao.DaoConstant.ROOT_PATH;

import java.io.IOException;
import java.util.List;

import org.freedom.core.test.dao.HibernateDaoTestCase;
import org.freedom.dao.security.UserDao;
import org.freedom.entity.security.User;
import org.freedom.file.CSVFileUtils;

/**
 * 登录用户Dao Test
 * 
 * @author 何贝
 * @since JDK1.5
 */
public class UserDaoTest extends HibernateDaoTestCase {
    private UserDao userDao;

    public UserDao getUserDao() {
        return userDao;
    }

    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }

    public void testCase() throws IOException {
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
                }
            }
            userDao.save(user);
        }
    }

}
