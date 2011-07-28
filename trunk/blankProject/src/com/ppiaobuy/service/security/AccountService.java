package com.ppiaobuy.service.security;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ppiaobuy.dao.security.UserDao;
import com.ppiaobuy.entity.security.User;

/**
 * 用户管理类.
 */
// Spring Service Bean的标识.
@Service
public class AccountService {
    private static Logger logger = LoggerFactory.getLogger(AccountService.class);
    
    private UserDao userDao;

    @Transactional(readOnly = true)
    public User getUser(String id) {
        return userDao.get(id);
    }

    /**
     * 按名称查询用户, 并对用户的延迟加载关联进行初始化.
     */
    @Transactional(readOnly = true)
    public User searchLoadedUserByName(String name) {
        User user = userDao.findUniqueBy("name", name);
        userDao.initUser(user);
        return user;
    }

    /**
     * 取得所有用户, 预加载用户的角色.
     */
    @Transactional(readOnly = true)
    public List<User> getAllUserWithRole() {
        List<User> list = userDao.getAllUserWithRoleByDistinctHql();
        logger.info("get {} user sucessful.", list.size());
        return list;
    }

    /**
     * 获取当前用户数量.
     */
    @Transactional(readOnly = true)
    public Long getUserCount() {
        return userDao.getUserCount();
    }

    @Transactional(readOnly = true)
    public User findUserByLoginName(String loginName) {
        return userDao.findUniqueBy("loginName", loginName);
    }

    /**
     * 批量修改用户状态.
     */
    public void disableUsers(List<String> ids) {
        userDao.disableUsers(ids);
    }

    @Autowired
    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }
}
