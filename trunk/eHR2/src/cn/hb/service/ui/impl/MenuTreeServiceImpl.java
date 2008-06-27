package cn.hb.service.ui.impl;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import cn.hb.service.ui.IMenuTreeService;
import cn.hb.service.vo.ui.UIMenu_TreeNode;

/**
 * @author kaka
 * 
 * 菜单树服务
 */
@Component("menuTreeService")
@Scope("prototype")
public class MenuTreeServiceImpl implements IMenuTreeService {

    public UIMenu_TreeNode getMenuTreeByUserID_Service(String userID) {
        // TODO Auto-generated method stub
        return null;
    }

}
