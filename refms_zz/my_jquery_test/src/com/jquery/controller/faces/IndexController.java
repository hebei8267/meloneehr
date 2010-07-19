package com.jquery.controller.faces;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.jquery.core.controller.AbstractController;

@Controller
public class IndexController extends AbstractController {
    /**
     * 默认主页
     * 
     * @return
     */
    @RequestMapping
    public String index() {
        System.out.println("22222222");
        return "index";
    }

    @RequestMapping
    public String show1() {
        System.out.println("show1");
        return "index";
    }

    @RequestMapping
    public String show2() {
        System.out.println("show2");
        return "index";
    }

    @RequestMapping
    public String toIndex2() {
        System.out.println("toIndex2");

        return "index2";
    }
}
