/**
 * com.sofmit.sim.wr.web Hello.java
 */
package com.sofmit.sim.wr.web;

import java.io.IOException;
import java.io.Writer;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import net.sf.json.JSONObject;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.ServletRequestBindingException;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author TianYe 2008-2-14
 */
@Controller
public class Hello {

    @RequestMapping("/hello.html")
    public String sayHello(Model model) {
        model.addAttribute("say", "hello!");

        Person p = new Person();
        p.setName("123123");
        p.setAge(120);

        model.addAttribute("person", p);

        model.addAttribute("city", "22");

        Map map = new LinkedHashMap();
        map.put("11", "111111111");
        map.put("22", "222222222");
        map.put("33", "333333333");
        map.put("44", "444444444");
        model.addAttribute("cityMap", map);
        return "index";
    }

    @RequestMapping("/hello1.html")
    public String sayHello1(HttpServletRequest request, Model model) throws ServletRequestBindingException {
        System.out.println(request.getParameterMap());

        Person p = new Person();
        p.setName(ServletRequestUtils.getStringParameter(request, "name"));
        p.setAge(ServletRequestUtils.getIntParameter(request, "age"));
        model.addAttribute("person", p);
        return "index";
    }

    @RequestMapping("/hello2.html")
    public void sayHello2(HttpServletRequest request, Writer writer) throws ServletRequestBindingException, IOException {
        String ss = ServletRequestUtils.getStringParameter(request, "query");
        System.out.println(ss);
        JsonBeanViewObject jsonObj = new JsonBeanViewObject();
        jsonObj.getDateList().add(ss + ":" + 1);
        jsonObj.getDateList().add(ss + ":" + 2);
        jsonObj.getDateList().add(ss + ":" + 3);

        JSONObject jSONObject = JSONObject.fromObject(jsonObj);
        writer.write(jSONObject.toString());

    }
}