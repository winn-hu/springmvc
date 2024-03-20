package com.blueStarWei.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Date;

@Controller
@RequestMapping("/user")
public class UserController {

    @RequestMapping("/register")
    public String register(String username, Date hiredate, Model model) {
        model.addAttribute("username", username);
        model.addAttribute("hiredate", hiredate);
        return "success";
    }
}
