package cn.hb.dao.security;

import static constant.TestConstant.ROOT_PATH;

import java.io.IOException;
import java.util.List;

import cn.hb.core.test.dao.HibernateDaoTestCase;
import cn.hb.entity.security.User;
import cn.hb.dao.security.UserDao;
import file.CSVUtils;

/**
 * 登录用户 DaoTest
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
        List<List<String>> csvFileContent = CSVUtils.readCSVFile(ROOT_PATH + "User.csv");

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
                    user.setDescription(value);
                }
            }
            userDao.save(user);
        }
    }

}
