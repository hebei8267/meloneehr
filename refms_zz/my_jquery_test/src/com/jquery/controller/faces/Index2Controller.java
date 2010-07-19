package com.jquery.controller.faces;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.jquery.core.controller.AbstractController;

@Controller
public class Index2Controller extends AbstractController {
    @RequestMapping
    public String back() {
        System.out.println("back  back back");
        return "index2";
    }
}
