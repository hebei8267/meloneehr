package cn.hb.view;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import cn.hb.core.view.AbstractViewBean;

/**
 * @author kaka
 * 
 * 全局画面迁移
 */
@Component("Global_Forward_View")
@Scope("request")
public class Global_Forward_View extends AbstractViewBean {
    // 迁移到【用户登录】页面
    public String toSec001_Action() {
        return "toSec001";
    }

    // 迁移到【修改密码】页面
    public String toSec002_Action() {
        return "toSec002";
    }

    // 用户注销
    public String logout_Action() {
        removeUserInfo();
        return "toHome";
    }

    // 工作区
    public String toFrm001_Action() {
        return "toWorkArea";
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

}
