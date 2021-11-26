package io.weet.demo.controllers;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import io.weet.demo.models.Allergen;
import io.weet.demo.models.UserModel;
import io.weet.demo.services.UserService;

@Controller
public class UserSignupController {
    
    @Autowired
    private UserService userService;
    
    @GetMapping("/signup")
    public String userLogin() {
        return "userSignup";
    }

    @PostMapping("/createUser")
    public String createUser(@RequestParam(name = "name") String name, @RequestParam(name = "email") String email, @RequestParam(name = "password") String pwd) {
        userService.saveUser(new UserModel(name, email, pwd, new ArrayList<Allergen>()));
        return "redirect:/signup?success";
    }

}
