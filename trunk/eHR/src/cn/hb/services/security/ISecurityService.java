package cn.hb.services.security;

import cn.hb.core.services.IService;

public interface ISecurityService extends IService {
    /**
     * 用户登录效验
     * 
     * @param userID 用户ID
     * @param password 用户密码
     * @return true-登录成功 false-登录失败
     */
    public boolean userLogin_Service(String userID, String password);

    /**
     * 修改用户密码
     * 
     * @param userID 用户ID
     * @param password 用户密码
     * @return true-修改成功 false-修改失败
     */
    public boolean changeEmployeePassword_Service(String userID, String password);
}
