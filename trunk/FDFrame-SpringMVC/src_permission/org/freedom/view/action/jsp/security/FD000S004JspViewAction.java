/*
 * Copyright 2008 by hebei, All rights reserved.
 */
package org.freedom.view.action.jsp.security;

import java.util.List;

import org.freedom.core.view.action.AbstractViewAction;
import org.freedom.entity.ui.MenuNodeType;
import org.freedom.services.ui.IMenuNodeService;
import org.freedom.view.vo.security.FD000S004ViewObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 菜单树管理界面JspViewAction
 * 
 * @author 何贝
 * @since JDK1.5
 */
@Controller
public class FD000S004JspViewAction extends AbstractViewAction {

    private static final long serialVersionUID = 8782877423568374052L;
    @Autowired
    private IMenuNodeService menuNodeService;

    /**
     * 显示菜单树管理界面
     * 
     * @return
     */
    @RequestMapping("/FD000S004JspViewAction_ShowPageAction.faces")
    public String showPageAction(Model model) {
        // 取得菜单树结点类型列表
        List<MenuNodeType> _menuNodeTypeList = menuNodeService.getMenuNodeTypeList_Service();

        // 用户登录页面初始化
        FD000S004ViewObject outPutObj = new FD000S004ViewObject(_menuNodeTypeList);

        model.addAttribute("FD000S004ViewObject", outPutObj);

        return "WEB-INF/jsp/security/FD000S004";
    }

    public IMenuNodeService getMenuNodeService() {
        return menuNodeService;
    }

    public void setMenuNodeService(IMenuNodeService menuNodeService) {
        this.menuNodeService = menuNodeService;
    }
}
