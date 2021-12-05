package io.weet.demo.controllers;
import io.weet.demo.models.Allergen;
import io.weet.demo.models.DietaryRestriction;
import io.weet.demo.models.UserModel;
import io.weet.demo.services.UserService;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
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

    @GetMapping("/user")
    public String userProfile(Model model) {
        authentication = SecurityContextHolder.getContext().getAuthentication();
        user = userService.getUser(authentication.getName());
        loadUserData(user);

        model.addAttribute("allergenList", new ArrayList<>(allergenList.values()));
        model.addAttribute("dietaryRestrictionList", new ArrayList<>(dietaryRestrictionList.values()));
        model.addAttribute("userName", user.getName());
        return "userProfile";
    }
    
    @PostMapping("/allergenDelete")
    public String allergenDelete(@RequestParam(name = "name") String name) {
        if (allergenList.containsKey(name)) {
            allergenList.remove(name);
            userService.updateAllergens(user, new ArrayList<>(allergenList.values()));
        }
        return "redirect:/user";   
    }

    @PostMapping("/addAllergen")
    public String addAllergen(@RequestParam(name = "name") String name) {
        allergenList.put(name, new Allergen(name));
        userService.updateAllergens(user, new ArrayList<>(allergenList.values()));
        return "redirect:/user";
    }

    @PostMapping("/dietaryRestrictionDelete")
    public String dietaryRestrictionDelete(@RequestParam(name = "name") String name) {
        if (dietaryRestrictionList.containsKey(name)) {
            dietaryRestrictionList.remove(name);
            userService.updateDietaryRestrictions(user, new ArrayList<>(dietaryRestrictionList.values()));
        }
        return "redirect:/user";   
    }

    @PostMapping("/addDietaryRestriction")
    public String addDietaryRestriction(@RequestParam(name = "name") String name) {
        dietaryRestrictionList.put(name, new DietaryRestriction(name));
        userService.updateDietaryRestrictions(user, new ArrayList<>(dietaryRestrictionList.values()));
        return "redirect:/user";   
    }

    public void loadUserData(UserModel user) {
        for (Allergen all : user.getAllergies()) {
            allergenList.put(all.getAllergen(), all);
        }

        for (DietaryRestriction dr : user.getRestrictions()) {
            dietaryRestrictionList.put(dr.getDietaryRestriction(), dr);
        }
    }
}
