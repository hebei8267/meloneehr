package cn.hb.service.security;

import cn.hb.core.service.IService;
import cn.hb.entity.security.User;

/**
 * @author kaka
 * 
 */
public interface ISecurityService extends IService {
    /**
     * 用户登录效验
     * 
     * @param userID 用户ID
     * @param password 用户密码
     * @return true-用户信息 false-null
     */
    public User userLogin_Service(String userID, String password);

    /**
     * 修改用户密码
     * 
     * @param userID 用户ID
     * @param password 用户密码
     * @return 0-修改成功 1-该用户对象不存在 2-修改失败
     */
    public Integer modUserPassword_Service(String userID, String password);
}
