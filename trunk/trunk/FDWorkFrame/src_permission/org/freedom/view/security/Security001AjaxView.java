package org.freedom.view.security;

import java.io.IOException;
import java.io.Writer;

import javax.servlet.http.HttpServletRequest;

import org.freedom.core.view.AbstractViewBean;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.ServletRequestBindingException;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class Security001AjaxView extends AbstractViewBean {

    private static final long serialVersionUID = -6277601235716651095L;

    @RequestMapping("/loginAction.ajax")
    public void loginAction(HttpServletRequest request, Writer writer) throws ServletRequestBindingException, IOException  {
        // 用户名
        String userId = ServletRequestUtils.getStringParameter(request, "userId");
        // 用户密码
        String password = ServletRequestUtils.getStringParameter(request, "password");

        System.out.println(userId);
        System.out.println(password);
       throw new  IOException("12123");
      //  writer.write("123");
    }

}
