package cn.hb.view.common;

import org.richfaces.model.TreeNode;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import cn.hb.core.view.AbstractViewBean;
import cn.hb.services.common.ICommonService;
import cn.hb.view.domain.UINativeplaceTreeNodeBean;

/**
 * @author kaka
 * 
 * 籍贯信息树
 */
@Component("Com003_View")
@Scope("request")
public class Com003_View extends AbstractViewBean {
    private TreeNode<UINativeplaceTreeNodeBean> npTreeData;
    private ICommonService commonService;

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
        npTreeData = commonService.getNativeplaceTreeInfo_Service();
    }

    // ---------------------------------------------------------------------------
    // Get Set Method
    // ---------------------------------------------------------------------------
    public TreeNode<UINativeplaceTreeNodeBean> getNpTreeData() {
        return npTreeData;
    }

    public ICommonService getCommonService() {
        return commonService;
    }

    public void setNpTreeData(TreeNode<UINativeplaceTreeNodeBean> npTreeData) {
        this.npTreeData = npTreeData;
    }

    public void setCommonService(ICommonService commonService) {
        this.commonService = commonService;
    }

}
