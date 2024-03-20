package com.blueStarWei.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class ForwardController {

    @RequestMapping("/find")
    public String find(int id) {
        System.out.println("find : " + id);
        return "redirect:/update?id=3";
    }

    @RequestMapping("/update")
    @ResponseBody
    public String update(int id) {
        System.out.println("update : " + id);
        return "success";
    }
}
