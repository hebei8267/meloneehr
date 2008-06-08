package cn.hb.view.security;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import cn.hb.core.view.AbstractViewBean;
import cn.hb.entity.security.User;
import cn.hb.services.security.ISecurityService;
import cn.hb.view.domain.UserInfoSessionBean;

import static cn.hb.view.MsgID.ERROR_Input_New_Pwd;
import static cn.hb.view.MsgID.ERROR_Input_Old_Pwd;

/**
 * @author kaka
 * 
 * 修改密码
 */
@Component("Sec002_View")
@Scope("request")
public class Sec002_View extends AbstractViewBean {
    private String userId;
    private String userName;
    private String oldPassword;
    private String newPassword;
    private String newPassword2;
    private ISecurityService securityService;

    public String modPassword_Action() {
        if (!newPassword.equals(newPassword2)) {
            addErrorMessage(ERROR_Input_New_Pwd);
            return null;
        }

        // 用户登录
        User user = securityService.userLogin_Service(userId, oldPassword);
        if (user == null) {// 输入原密码错误
            addErrorMessage(ERROR_Input_Old_Pwd);
            return null;
        }

        user.setPassword(newPassword);
        securityService.modUserPassword_Service(user.getId(), user.getPassword());

        return "modPwdSucceed";
    }

    // ---------------------------------------------------------------------------
    // Override Method
    // ---------------------------------------------------------------------------
    @Override
    public void create() {
        UserInfoSessionBean userInfo = getUserInfoInSession();
        userId = userInfo.getUserId();
        userName = userInfo.getUserName();
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

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getOldPassword() {
        return oldPassword;
    }

    public void setOldPassword(String oldPassword) {
        this.oldPassword = oldPassword;
    }

    public String getNewPassword() {
        return newPassword;
    }

    public void setNewPassword(String newPassword) {
        this.newPassword = newPassword;
    }

    public String getNewPassword2() {
        return newPassword2;
    }

    public void setNewPassword2(String newPassword2) {
        this.newPassword2 = newPassword2;
    }

    public ISecurityService getSecurityService() {
        return securityService;
    }

    public void setSecurityService(ISecurityService securityService) {
        this.securityService = securityService;
    }

}
