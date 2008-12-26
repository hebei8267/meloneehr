/*
 * Copyright 2008 by hebei, All rights reserved.
 */
package org.freedom.view.action.normal.security;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.freedom.core.domain.TreeNode;
import org.freedom.core.domain.UserInfoSessionBean;
import org.freedom.core.view.action.AbstractViewAction;
import org.freedom.services.permit.IMenuNodeService;
import org.freedom.view.action.vobj.security.s003.Security003ViewObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author 何贝
 * @since JDK1.5
 */
@Controller
public class Security003NormalViewAction extends AbstractViewAction {

    /**
     * 
     */
    private static final long serialVersionUID = 4326596961570318806L;
    @Autowired
    private IMenuNodeService menuNodeService;

    @RequestMapping("/security/003/showPageAction")
    public String showPageAction(HttpServletRequest request, Model model) {
        // 取得登录用户信息
        UserInfoSessionBean userInfo = getUserInfoInSession(request);

        List<TreeNode> nodeList = menuNodeService.getAllAreaMenuNodeService(userInfo.getUserId(), 
                                                                            userInfo.getRoleId());

        Security003ViewObject vo = new Security003ViewObject();
        vo.getAreaMenuNodeList().addAll(nodeList);
        
        model.addAttribute("Security003ViewObject", vo);
        
        return "WEB-INF/jsp/security/security003";
    }

    public IMenuNodeService getMenuNodeService() {
        return menuNodeService;
    }

    public void setMenuNodeService(IMenuNodeService menuNodeService) {
        this.menuNodeService = menuNodeService;
    }
}
