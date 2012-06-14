/*
 * Copyright 2009 by hebei, All rights reserved.
 */
package org.freedom.view.action.normal.dictionary.common;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.freedom.sys.SysConstant;
import org.freedom.view.action.AbstractViewAction;
import org.freedom.view.module.dictionary.common.cs001.CountrySetting001ViewObject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 国家信息(数据字典)画面迁移
 * 
 * @author 何贝
 * @since JDK1.5
 */
@Controller
public class CountrySetting001NormalViewAction extends AbstractViewAction {

    /**
     * 
     */
    private static final long serialVersionUID = 389400591642540289L;

    @RequestMapping("/dictionary/common/countrySetting/001/showPageAction")
    public String showPageAction(HttpServletRequest request, Model model) {
        // 页面初始化
        model.addAttribute("CountrySetting001ViewObject", new CountrySetting001ViewObject());

        // 第一次请求时设置web程序名称
        if (StringUtils.isBlank(SysConstant.WEB_PROJECT_NAME)) {
            SysConstant.WEB_PROJECT_NAME = request.getContextPath();
        }

        return "WEB-INF/jsp/dictionary/common/countrySetting001";
    }
}
