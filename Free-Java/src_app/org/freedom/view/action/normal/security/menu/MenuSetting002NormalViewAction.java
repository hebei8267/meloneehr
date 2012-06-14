/*
 * Copyright 2009 by hebei, All rights reserved.
 */
package org.freedom.view.action.normal.security.menu;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.freedom.entity.ui.MenuNode;
import org.freedom.entity.ui.MenuNodeType;
import org.freedom.services.permit.IMenuNodeService;
import org.freedom.sys.SysConstant;
import org.freedom.view.action.AbstractViewAction;
import org.freedom.view.module.security.menu.ms002.MenuSetting002ViewObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.ServletRequestBindingException;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 菜单树设定-添加菜单节点
 * 
 * @author 何贝
 * @since JDK1.5
 */
@Controller
public class MenuSetting002NormalViewAction extends AbstractViewAction {

    /**
     * 
     */
    private static final long serialVersionUID = 8679678044706654792L;
    @Autowired
    private IMenuNodeService menuNodeService;

    /**
     * 菜单树设定-添加菜单节点
     * 
     * @param request
     * @param model
     * @return
     * @throws ServletRequestBindingException
     */
    @RequestMapping("/security/menu/menuSetting/002/showPageAction")
    public String showPageAction(HttpServletRequest request, Model model) throws ServletRequestBindingException {
        // 取得菜单节点类型列表
        List<MenuNodeType> _menuNodeTypeList = menuNodeService.getMenuNodeTypeInfoListService();
        MenuSetting002ViewObject vObj = new MenuSetting002ViewObject();
        // 取得request里面的参数
        String pNodeID = ServletRequestUtils.getStringParameter(request, "nodeID");
        String pNodeTxt = ServletRequestUtils.getStringParameter(request, "nodeTxt");
        String parentNodeTypeID = ServletRequestUtils.getStringParameter(request, "nodeTypeID");

        if (StringUtils.isEmpty(pNodeID)) {
            vObj.setParentNodeID(MenuNode.MENU_NODE_TREE_ROOT_ID);
        } else {
            vObj.setParentNodeID(pNodeID);
        }
        if (StringUtils.isEmpty(pNodeTxt)) {
            vObj.setParentNodeTxt(SysConstant.MENU_NODE_TREE_ROOT_NAME);
        } else {
            vObj.setParentNodeTxt(pNodeTxt);
        }
        if (StringUtils.isEmpty(parentNodeTypeID)) {
            vObj.setParentNodeTypeID(MenuNodeType.ROOT_NODE_TYPE);
        } else {
            vObj.setParentNodeTypeID(parentNodeTypeID);
        }
        if (_menuNodeTypeList != null) {
            if (MenuNodeType.ROOT_NODE_TYPE.equals(vObj.getParentNodeTypeID())) {
                if (_menuNodeTypeList.size() >= 1) {
                    _menuNodeTypeList.remove(3);
                    _menuNodeTypeList.remove(2);
                    _menuNodeTypeList.remove(0);
                }
            } else {
                if (_menuNodeTypeList.size() >= 2) {
                    _menuNodeTypeList.remove(1);
                    _menuNodeTypeList.remove(0);
                }
            }
            vObj.setNodeTypeInfoList(_menuNodeTypeList);
        }
        model.addAttribute("MenuSetting002ViewObject", vObj);
        return "WEB-INF/jsp/security/menu/menuSetting002";
    }

    public IMenuNodeService getMenuNodeService() {
        return menuNodeService;
    }

    public void setMenuNodeService(IMenuNodeService menuNodeService) {
        this.menuNodeService = menuNodeService;
    }

}
