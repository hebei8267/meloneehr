package cn.hb.view.common;

import org.richfaces.model.TreeNode;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import cn.hb.core.view.AbstractViewBean;
import cn.hb.view.domain.UIMenuTreeNodeBean;

/**
 * @author kaka
 * 
 * 籍贯信息树
 */
@Component("Com003_View")
@Scope("request")
public class Com003_View extends AbstractViewBean {
    private TreeNode<UIMenuTreeNodeBean>  npTreeData;
    @Override
    public void create() {
        // TODO Auto-generated method stub

    }

    @Override
    public void destroy() {
        // TODO Auto-generated method stub

    }

    @Override
    public void init() {
        // TODO Auto-generated method stub

    }

}
