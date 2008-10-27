package org.freedom.view.action.jsp.security;

import org.freedom.core.view.action.AbstractViewAction;
import org.freedom.view.vo.security.FD000S001ViewObject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
@Controller
public class bbbbbbbbb extends AbstractViewAction{
    /**
     * 默认画面迁移到用户登录页面
     * 
     * @return
     */
    @RequestMapping("/bbbbbbbbb.faces")
    public String showPageAction(Model model) {
        // 用户登录页面初始化
        FD000S001ViewObject outPutObj = new FD000S001ViewObject();

        model.addAttribute("FD000S001ViewObject", outPutObj);

        return "WEB-INF/jsp/security/bbbb";
    }
}
