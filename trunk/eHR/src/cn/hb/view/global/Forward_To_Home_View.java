package cn.hb.view.global;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import cn.hb.core.view.AbstractViewBean;
import cn.hb.view.IForward;

/**
 * @author kaka
 * 
 * 用户注销
 */
@Component("Forward_To_Home_View")
@Scope("request")
public class Forward_To_Home_View extends AbstractViewBean implements IForward {

    @Override
    public void create() {
    }

    @Override
    public void destroy() {
    }

    @Override
    public void init() {
    }

    @Override
    public String nextPage_Action() {
        removeUserInfo();
        return "logout";
    }

}
