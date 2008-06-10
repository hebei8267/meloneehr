package cn.hb.view.frame;

import org.richfaces.model.TreeNode;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import cn.hb.core.view.AbstractViewBean;
import cn.hb.services.ui.IMenuTreeService;
import cn.hb.view.domain.RichFacesTreeNodeBean;

/**
 * @author kaka
 * 
 * 程序主框架，主要负责菜单树部门
 */
@Component("Frm001_View")
@Scope("request")
// session
public class Frm001_View extends AbstractViewBean {
    private TreeNode<RichFacesTreeNodeBean> menuTreeData;
    private final String ACTION_PATH = "actionPath";
    private IMenuTreeService menuTreeService;

    /** 同步菜单树 */
    public void synch_Action() {
        // 空实现,Scope("request")时会自动再次调用create()
    }

    /** 菜单树结点选中 */
    public String nodeSelected_Action() {
        // 取得迁移页面Key
        String actionPath = (String) getRequestParameterMap().get(ACTION_PATH);
        return actionPath;
    }

    // ---------------------------------------------------------------------------
    // Override Method
    // ---------------------------------------------------------------------------

    @Override
    public void create() {
        init();
    }

    @Override
    public void destroy() {

    }

    @Override
    public void init() {
        menuTreeData = menuTreeService.getMenuTreeRootNode_Service(getUserInfoInSession().getUserId());
    }

    // ---------------------------------------------------------------------------
    // Get Set Method
    // ---------------------------------------------------------------------------
    public TreeNode<RichFacesTreeNodeBean> getMenuTreeData() {
        return menuTreeData;
    }

    public IMenuTreeService getMenuTreeService() {
        return menuTreeService;
    }

    public void setMenuTreeData(TreeNode<RichFacesTreeNodeBean> menuTreeData) {
        this.menuTreeData = menuTreeData;
    }

    public void setMenuTreeService(IMenuTreeService menuTreeService) {
        this.menuTreeService = menuTreeService;
    }

}
