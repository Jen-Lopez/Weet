package io.weet.demo.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class UserSignupController {
    
    @GetMapping("/signup")
    public String userLogin() {
        return "userSignup";
    }

    @PostMapping("/createUser")
    public String createUser() {
        return "redirect:/user";
    }

}
