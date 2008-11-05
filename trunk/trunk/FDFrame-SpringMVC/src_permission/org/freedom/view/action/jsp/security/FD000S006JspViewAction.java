/*
 * Copyright 2008 by hebei, All rights reserved.
 */
package org.freedom.view.action.jsp.security;

import java.util.List;

import org.freedom.core.view.action.AbstractViewAction;
import org.freedom.entity.ui.MenuNodeType;
import org.freedom.services.ui.IMenuNodeService;
import org.freedom.view.vo.security.s006.FD000S006ViewObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 添加菜单树节点界面JspViewAction
 * 
 * @author 何贝
 * @since JDK1.5
 */
@Controller
public class FD000S006JspViewAction extends AbstractViewAction {

    private static final long serialVersionUID = 5069003359615514373L;
    @Autowired
    private IMenuNodeService menuNodeService;

    /**
     * 显示界面
     * 
     * @return
     */
    @RequestMapping("/FD000S006JspViewAction_ShowPageAction.faces")
    public String showPage_Action(Model model) {
        // 取得菜单树结点类型列表
        List<MenuNodeType> _menuNodeTypeList = menuNodeService.getMenuNodeTypeList_Service();

        // 页面初始化
        FD000S006ViewObject outPutObj = new FD000S006ViewObject(_menuNodeTypeList);

        model.addAttribute("FD000S006ViewObject", outPutObj);

        return "WEB-INF/jsp/security/FD000S006";
    }

    public IMenuNodeService getMenuNodeService() {
        return menuNodeService;
    }

    public void setMenuNodeService(IMenuNodeService menuNodeService) {
        this.menuNodeService = menuNodeService;
    }
}
