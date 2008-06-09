package cn.hb.services.security.impl;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import cn.hb.dao.security.UserDao;
import cn.hb.entity.security.User;
import cn.hb.services.security.ISecurityService;

@Component("securityService")
@Scope("prototype")
public class SecurityServiceImpl implements ISecurityService {
    // ---------------------------------------------------------------------------
    // 接口实现
    // ---------------------------------------------------------------------------
    @Override
    public boolean modUserPassword_Service(String userID, String password) {
        User user = userDao.getUserByID(userID);
        user.setPassword(password);
        user.setFirstLoginFlag(Boolean.FALSE);
        userDao.save(user);

        return true;
    }

    @Override
    public User userLogin_Service(String userID, String password) {
        User user = userDao.getUserByID(userID);

        if (user != null && user.getPassword().equals(password)) {
            return user;
        }
        return null;
    }

    // ---------------------------------------------------------------------------
    // DAO
    // ---------------------------------------------------------------------------
    private UserDao userDao = null;

    public UserDao getUserDao() {
        return userDao;
    }

    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }
}
