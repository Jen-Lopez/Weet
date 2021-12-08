package io.weet.demo.controllers;
import io.weet.demo.models.Allergen;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import io.weet.demo.models.UserModel;
import io.weet.demo.services.UserService;

@Controller

public class UserSettings {
    Authentication authentication;

    private UserModel user; 

    @Autowired
    UserService userService;


    
    @PostConstruct
    private void loadData(){
        authentication = SecurityContextHolder.getContext().getAuthentication();
        user = userService.getUser(authentication.getName());
    } 

    @GetMapping("/settings")
    public String userProfile(Model model) {
        model.addAttribute("name", user.getName());
        return "redirect:/settings";
    }
    /*
    @PostMapping("/allergenDelete")
    public String allergenDelete(@RequestParam(name = "name") String name) {
        if (allergenList.containsKey(name)) {
            allergenList.remove(name);
        }
        return "redirect:/settings";   
    } */
}
