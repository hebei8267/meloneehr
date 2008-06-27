package cn.hb.service;

import cn.hb.core.service.IService;
import cn.hb.service.vo.ui.UIMenu_TreeNode;

/**
 * @author kaka
 * 
 */
public interface IMenuTreeService extends IService {
    /**
     * 取得UI树的根节点，根据登录用户ID取得其能访问的数据
     * 
     * @param userID 登录用户ID
     * @return
     */
    public UIMenu_TreeNode getMenuTreeByUserID_Service(String userID);
}
