package cn.hb.dao.security;

import org.springframework.stereotype.Component;
import cn.hb.core.dao.impl.HibernateDaoImpl;
import cn.hb.entity.security.User;

/**
 * 登录用户Dao
 */
@Component("userDao")
public class UserDao extends HibernateDaoImpl<User> {
}
