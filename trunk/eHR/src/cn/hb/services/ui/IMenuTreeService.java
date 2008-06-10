package cn.hb.services.ui;

import cn.hb.core.services.IService;
import cn.hb.view.domain.RichFacesTreeNodeBean;

/**
 * @author kaka
 *
 */
public interface IMenuTreeService extends IService {
    /**
     * 取得UI树的根节点，根据登录用户ID取得其能访问的数据
     * 
     * （默认根节点ID--"00000001"）
     * 
     * @param userID 登录用户ID
     * @return
     */
    public RichFacesTreeNodeBean getMenuTreeRootNode_Service(String userID);
}
