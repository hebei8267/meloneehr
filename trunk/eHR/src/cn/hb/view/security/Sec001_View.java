package cn.hb.view.security;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import cn.hb.core.view.AbstractViewBean;
import cn.hb.entity.security.User;
import cn.hb.services.security.ISecurityService;
import cn.hb.view.domain.UserInfoSessionBean;

import static cn.hb.view.MsgID.ERROR_LOGIN_FAILED;
import static cn.hb.view.MsgID.TIP_FIRST_LOGIN_FLAG;

/**
 * @author kaka
 * 
 * 用户登录
 */
@Component("Sec001_View")
@Scope("request")
public class Sec001_View extends AbstractViewBean {
    private String userId;
    private String password;
    private String loginFlag = Boolean.TRUE.toString();
    private ISecurityService securityService;

    public String login_Action() {
        if (userId.equals("")) {// 没输入用户名
            addErrorMessage(ERROR_LOGIN_FAILED);
            return null;
        }

        // 用户登录
        User user = securityService.userLogin_Service(userId, password);
        if (user == null) {// 输入用户名或密码错误
            addErrorMessage(ERROR_LOGIN_FAILED);
            return null;
        }

        if (user.getFirstLoginFlag().equals(Boolean.TRUE)) {// 登录成功--（第一次登录系统）
            addErrorMessage(TIP_FIRST_LOGIN_FLAG);

            saveUserInfoToSession(user);
            return "modPassword";
        } else {// 登录成功--画面迁移到指定页面
            if (loginFlag.equals(Boolean.TRUE.toString())) {
                saveUserInfoToSession(user);

                return "loginSucceed";
            } else {
                saveUserInfoToSession(user);

                return "modPassword";
            }
        }

    }

    /**
     * 初始化 登录用户信息
     */
    private void saveUserInfoToSession(User user) {

        UserInfoSessionBean userInfo = new UserInfoSessionBean();
        userInfo.setUserId(user.getId());
        userInfo.setUserName(user.getName());

        saveUserInfo(userInfo);
    }

    // ---------------------------------------------------------------------------
    // Override Method
    // ---------------------------------------------------------------------------
    @Override
    public void create() {

    }

    @Override
    public void destroy() {

    }

    @Override
    public void init() {

    }

    // ---------------------------------------------------------------------------
    // Get Set Method
    // ---------------------------------------------------------------------------

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getLoginFlag() {
        return loginFlag;
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
