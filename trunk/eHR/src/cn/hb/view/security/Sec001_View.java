package cn.hb.view.security;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import cn.hb.core.view.AbstractViewBean;

/**
 * @author kaka
 * 
 */
@Component("Sec001_View")
@Scope("request")
public class Sec001_View extends AbstractViewBean {
    private String username;
    private String password;
    private String loginFlag;

    public String login_Action() {
        if (username.endsWith("0000001")) {
            if (loginFlag.equals(Boolean.TRUE.toString())) {
                return "";
            } else {
                return "";
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

}
