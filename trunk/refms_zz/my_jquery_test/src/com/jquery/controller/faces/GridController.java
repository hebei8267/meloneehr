package com.jquery.controller.faces;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.ServletRequestBindingException;
import org.springframework.web.bind.annotation.RequestMapping;

import com.jquery.core.controller.AbstractController;

@Controller
public class GridController extends AbstractController {
    private String calendar;

    @RequestMapping
    public String show(HttpServletRequest request, Model model) throws ServletRequestBindingException {
       setCalendar("20100222");
        model.addAttribute("gridViewObject", this);
        return "grid";
    }

    @RequestMapping
    public String back(HttpServletRequest request, Model model) throws ServletRequestBindingException {
        return "index";
    }

    public String getCalendar() {
        return calendar;
    }

    public void setCalendar(String calendar) {
        this.calendar = calendar;
    }
}
