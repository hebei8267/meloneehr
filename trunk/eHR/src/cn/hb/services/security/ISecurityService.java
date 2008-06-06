package cn.hb.services.security;

import cn.hb.core.services.IService;
import cn.hb.entity.security.User;

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
     * @return true-修改成功 false-修改失败
     */
    public boolean modUserPassword_Service(String userID, String password);
}
