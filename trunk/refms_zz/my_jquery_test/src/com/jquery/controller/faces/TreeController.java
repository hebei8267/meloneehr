package com.jquery.controller.faces;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.ServletRequestBindingException;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.bind.annotation.RequestMapping;

import com.jquery.core.controller.AbstractController;

@Controller
public class TreeController extends AbstractController {

    @RequestMapping
    public String show(HttpServletRequest request) throws ServletRequestBindingException {
        String[] ckv = ServletRequestUtils.getStringParameters(request, "ckNameTest");
        for (int i = 0; i < ckv.length; i++) {
            System.out.println(ckv[i]);
        }
        return "tree";
    }
    @RequestMapping
    public String back(HttpServletRequest request) throws ServletRequestBindingException {
        return "index";
    }
}
