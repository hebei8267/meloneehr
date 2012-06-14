/*
 * Copyright 2009 by hebei, All rights reserved.
 */
package org.freedom.view.action.ajax.security;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.freedom.core.domain.TreeNode;
import org.freedom.core.view.action.AbstractViewAction;
import org.freedom.core.view.vo.ajax.DataListBean;
import org.freedom.entity.dictionary.common.Country;
import org.freedom.entity.ui.MenuNode;
import org.freedom.services.dictionary.ICountryService;
import org.freedom.services.permit.IMenuNodeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.ServletRequestBindingException;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author 何贝
 * @since JDK1.5
 */
@Controller
public class TestAjaxViewAction extends AbstractViewAction {

    /**
     * 
     */
    private static final long serialVersionUID = -1337021160371414270L;
    @Autowired
    private IMenuNodeService menuNodeService;
    @Autowired
    private ICountryService countryService;

    @RequestMapping("/index1.ajax")
    public void getAllMenuInfoTreeAction(HttpServletRequest request, HttpServletResponse response) throws IOException,
            ServletRequestBindingException {
        System.out.println(ServletRequestUtils.getStringParameter(request, "aaa"));
        // 取得所有菜单结点树信息(根节点开始)不包含权限校验
        TreeNode rootNode = menuNodeService.getMenuNodeInfoTreeService(MenuNode.MENU_NODE_TREE_ROOT_ID);

        JSONArray jSONArray = JSONArray.fromObject(rootNode.getChildren());
        response.setContentType(RESPONSE_CONTENT_TYPE);
        response.getWriter().write(jSONArray.toString());
    }

    @RequestMapping("/index2.ajax")
    public void getAllCountryInfoListAction(HttpServletRequest request, HttpServletResponse response) throws IOException {
        List<Country> countryList = countryService.getAllCountryInfoListService();

        // Json对象格式化
        DataListBean<Country> dataList = new DataListBean<Country>();
        dataList.setDataList(countryList);

        JSONObject jSONObject = JSONObject.fromObject(dataList);
        response.setContentType(RESPONSE_CONTENT_TYPE);
        response.getWriter().write(jSONObject.toString());
    }

    public IMenuNodeService getMenuNodeService() {
        return menuNodeService;
    }

    public void setMenuNodeService(IMenuNodeService menuNodeService) {
        this.menuNodeService = menuNodeService;
    }

    public ICountryService getCountryService() {
        return countryService;
    }

    public void setCountryService(ICountryService countryService) {
        this.countryService = countryService;
    }

}
