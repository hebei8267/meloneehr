/*
 * Copyright 2009 by hebei, All rights reserved.
 */
package org.freedom.view.action.ajax.security.menu;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.apache.commons.beanutils.BeanUtils;
import org.freedom.core.view.action.AbstractViewAction;
import org.freedom.core.view.vo.ajax.JosnViewObject;
import org.freedom.entity.ui.MenuNode;
import org.freedom.services.permit.IMenuNodeService;
import org.freedom.view.action.SecurityMesssageID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.ServletRequestBindingException;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 添加菜单
 * 
 * @author 何贝
 * @since JDK1.5
 */
@Controller
public class MenuSetting002AjaxViewAction extends AbstractViewAction {

    /**
     * 
     */
    private static final long serialVersionUID = 7869210591206824710L;
    @Autowired
    private IMenuNodeService menuNodeService;

    /**
     * 添加菜单
     * 
     * @param request
     * @param response
     * @throws ServletRequestBindingException
     * @throws InvocationTargetException
     * @throws IllegalAccessException
     * @throws IOException
     */
    @RequestMapping("/security/menu/menuSetting/002/addMenuNodeInfoAction.ajax")
    public void addMenuNodeInfoAction(HttpServletRequest request, HttpServletResponse response)
            throws ServletRequestBindingException, IllegalAccessException, InvocationTargetException, IOException {
        // 取得request里面的参数
        boolean inheritFlg = ServletRequestUtils.getBooleanParameter(request, "inheritFlg");
        MenuNode menuNode = new MenuNode();
        BeanUtils.populate(menuNode, request.getParameterMap());

        int _result = menuNodeService.addMenuNodeInfoService(menuNode, inheritFlg);
        JosnViewObject outObj = new JosnViewObject();
        if (_result != 0) {
            outObj.setProcessResult(false);
            outObj.setResultMsg(getMessage(request, SecurityMesssageID.ERROR_DATA_NO_SYNCHRONIZATION));
        }
        JSONObject jSONObject = JSONObject.fromObject(outObj);
        response.setContentType(RESPONSE_CONTENT_TYPE);
        response.getWriter().write(jSONObject.toString());
    }

    public IMenuNodeService getMenuNodeService() {
        return menuNodeService;
    }

    public void setMenuNodeService(IMenuNodeService menuNodeService) {
        this.menuNodeService = menuNodeService;
    }

}
