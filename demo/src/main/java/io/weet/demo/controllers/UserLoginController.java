package io.weet.demo.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;


@Controller
public class UserLoginController {

    @GetMapping("/login")
    public String userLogin() {
        return "userLogin";
    }

    @PostMapping("/checkLogin")
    public String validateLogin() {
        return "redirect:/user";
    }

}
