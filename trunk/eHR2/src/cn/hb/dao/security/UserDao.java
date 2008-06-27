package cn.hb.dao.security;

import java.util.List;

import org.springframework.stereotype.Component;
import cn.hb.core.dao.impl.HibernateDaoImpl;
import cn.hb.entity.security.User;

/**
 * 登录用户Dao
 */
@Component("userDao")
public class UserDao extends HibernateDaoImpl<User> {
    /**
     * 根据用户ID取得用户信息
     * 
     * @param userID 用户ID
     * @return User 用户信息
     */
    @SuppressWarnings("unchecked")
    public User getUserByID(String userID) {
        List<User> resultList = getHibernateTemplate().findByNamedQuery("User.getUserByID", userID);
        if (resultList.size() > 0) {
            return resultList.get(0);
        }
        return null;
    }
}
