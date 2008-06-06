package cn.hb.view.security;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import cn.hb.core.view.AbstractViewBean;
import cn.hb.entity.security.User;
import cn.hb.services.security.ISecurityService;

/**
 * @author kaka
 * 
 */
@Component("Sec001_View")
@Scope("request")
public class Sec001_View extends AbstractViewBean {
    private String username;
    private String password;
    private String loginFlag = Boolean.TRUE.toString();
    private ISecurityService securityService;

    public String login_Action() {
        if (!username.equals("")) {
            // 用户登录
            User user = securityService.userLogin_Service(username, password);

            if (user != null) {
                if (user.getFirstLoginFlag().equals(Boolean.TRUE)) {// 第一次登录系统
                    return "modPassword";
                } else {
                    if (loginFlag.equals(Boolean.TRUE.toString())) {
                        return "loginSucceed";
                    } else {
                        return "modPassword";
                    }
                }
            }
        }
        return null;
    }

    @Override
    public void create() {

    }

    @Override
    public void destroy() {

    }

    @Override
    public void init() {

    }

    // -------------------------------------

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getLoginFlag() {
        return loginFlag;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setLoginFlag(String loginFlag) {
        this.loginFlag = loginFlag;
    }

    public ISecurityService getSecurityService() {
        return securityService;
    }

    public void setSecurityService(ISecurityService securityService) {
        this.securityService = securityService;
    }

}
