/*
 * Copyright 2008 by hebei, All rights reserved.
 */
package org.freedom.view.action.normal.security;

import java.util.ArrayList;
import java.util.List;

import org.freedom.core.view.action.AbstractViewAction;
import org.freedom.core.view.vo.jsp.LabelValueBean;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author 何贝
 * @since JDK1.5
 */
@Controller
public class test extends AbstractViewAction {
    /**
     * 
     */
    private static final long serialVersionUID = 7967645717246833999L;
    private String input;
    private String password;
    private String nodeType;
    private List<LabelValueBean> nodeTypeList = new ArrayList<LabelValueBean>();
    private String calendar;

    public test() {
        nodeTypeList.add(new LabelValueBean("<空>", ""));
        nodeTypeList.add(new LabelValueBean("1", "11"));
        nodeTypeList.add(new LabelValueBean("2", "22"));
        nodeTypeList.add(new LabelValueBean("3", "33"));
    }

    /**
     * 默认画面迁移到用户登录页面
     * 
     * @return
     */
    @RequestMapping("/index.faces")
    public String showPage_Action(Model model) {
        // 用户登录页面初始化
        model.addAttribute("testViewObject", this);

        return "WEB-INF/jsp/security/test";
    }

    public String getInput() {
        return input;
    }

    public void setInput(String input) {
        this.input = input;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<LabelValueBean> getNodeTypeList() {
        return nodeTypeList;
    }

    public void setNodeTypeList(List<LabelValueBean> nodeTypeList) {
        this.nodeTypeList = nodeTypeList;
    }

    public String getNodeType() {
        return nodeType;
    }

    public void setNodeType(String nodeType) {
        this.nodeType = nodeType;
    }

    public String getCalendar() {
        return calendar;
    }

    public void setCalendar(String calendar) {
        this.calendar = calendar;
    }
}
