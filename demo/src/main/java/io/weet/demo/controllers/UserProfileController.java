package io.weet.demo.controllers;
import io.weet.demo.models.Allergen;
import io.weet.demo.models.DietaryRestriction;
import io.weet.demo.models.UserModel;
import io.weet.demo.services.UserService;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller

public class UserProfileController {
    Authentication authentication;

    private Map<String, Allergen> allergenList = new HashMap<>();
    private Map<String, DietaryRestriction> dietaryRestrictionList = new HashMap<>();
    private UserModel user; 

    @Autowired
    UserService userService;
    
    @PostConstruct
    private void loadData(){
    }

    @GetMapping("/user")
    public String userProfile(Model model) {
        authentication = SecurityContextHolder.getContext().getAuthentication();
        user = userService.getUser(authentication.getName());
        // System.out.println(user);

        model.addAttribute("allergenList", new ArrayList<>(allergenList.values()));
        model.addAttribute("dietaryRestrictionList", new ArrayList<>(dietaryRestrictionList.values()));
        model.addAttribute("userName", user.getName());
        return "userProfile";
    }
    
    @PostMapping("/allergenDelete")
    public String allergenDelete(@RequestParam(name = "name") String name) {
        if (allergenList.containsKey(name)) {
            allergenList.remove(name);
        }
        return "redirect:/user";   
    }

    @PostMapping("/addAllergen")
    public String addAllergen(@RequestParam(name = "name") String name) {
        allergenList.put(name, new Allergen(name));
        userService.loadUserByUsername(user.getName());
        return "redirect:/user";   
    }
    @PostMapping("/dietaryRestrictionDelete")
    public String dietaryRestrictionDelete(@RequestParam(name = "name") String name) {
        if (dietaryRestrictionList.containsKey(name)) {
            dietaryRestrictionList.remove(name);
        }
        return "redirect:/user";   
    }

    @PostMapping("/addDietaryRestriction")
    public String addDietaryRestriction(@RequestParam(name = "name") String name) {
        dietaryRestrictionList.put(name, new DietaryRestriction(name));
        return "redirect:/user";   
    }
}
