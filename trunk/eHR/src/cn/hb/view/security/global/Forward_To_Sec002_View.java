package cn.hb.view.security.global;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import cn.hb.core.view.AbstractViewBean;
import cn.hb.view.IForward;

/**
 * @author kaka
 * 
 * 迁移到【修改密码】页面
 */
@Component("Forward_To_Sec002_View")
@Scope("request")
public class Forward_To_Sec002_View extends AbstractViewBean implements IForward {
    @Override
    public String nextPage_Action() {
        return "toSec002";
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
}
