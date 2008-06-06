package cn.hb.core.view;

import java.util.Locale;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

import cn.hb.core.util.FacesContextUtil;
import cn.hb.core.util.SpringContextUtils;
import cn.hb.view.domain.UserInfoSessionBean;

public abstract class AbstractViewBean {
    private final String USER_INFO = "userInfo";

    public AbstractViewBean() {

    }

    /** Bean生命周期中初始化方法 */
    @PostConstruct
    protected void _create() {
        create();
    }

    /** Bean生命周期中初始化方法 */
    public abstract void create();

    /** Bean生命周期中销毁方法 */
    @PreDestroy
    protected void _destroy() {
        destroy();
    }

    /** Bean生命周期中销毁方法 */
    public abstract void destroy();

    public abstract void init();

    /**
     * 添加错误消息
     * 
     * @param msgKey 消息内容Key
     */
    protected void addErrorMessage(String msgKey) {
        String msgStr = getMessage(msgKey);
        FacesMessage msg = new FacesMessage(msgStr);
        FacesContext.getCurrentInstance().addMessage("", msg);
    }

    /**
     * 取得消息资源
     * 
     * @param msgKey 消息Key
     * @return 消息
     */
    protected String getMessage(String msgKey) {
        return getMessage(msgKey, null, Locale.CHINA);
    }

    /**
     * 取得消息资源
     * 
     * @param msgKey 消息Key
     * @param args 消息Param
     * @return 消息
     */
    protected String getMessage(String msgKey, Object[] args) {
        return getMessage(msgKey, args, Locale.CHINA);
    }

    /**
     * 取得消息资源
     * 
     * @param msgKey 消息Key
     * @param args 消息Param
     * @param locale Locale
     * @return
     */
    protected String getMessage(String msgKey, Object[] args, Locale locale) {
        return SpringContextUtils.getMessage(msgKey, args, locale);
    }

    /**
     * 取得 HttpSession
     * 
     * @return HttpSession
     */
    private HttpSession getSession() {
        return FacesContextUtil.getSession();
    }

    /**
     * 保存登录用户信息
     * 
     * @param userInfo 登录用户信息
     * @return 执行结果
     */
    protected boolean saveUserInfo(UserInfoSessionBean userInfo) {
        HttpSession session = getSession();
        if (session != null) {
            session.setAttribute(USER_INFO, userInfo);
            return true;
        }
        return false;
    }

    /**
     * 删除登录用户信息
     * 
     * @param userInfo 登录用户信息
     * @return 执行结果
     */
    protected boolean removeUserInfo() {
        HttpSession session = getSession();
        if (session != null) {
            session.removeAttribute(USER_INFO);
            return true;
        }
        return false;
    }
}
