package io.weet.demo.controllers;
import io.weet.demo.models.Allergen;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller

public class UserProfileController {
    private Map<String, Allergen> allergenList = new HashMap<>();

    // fetch this from DB
    @PostConstruct
    private void loadData(){
        Allergen emp1= new Allergen("Peanuts");
        Allergen emp2= new Allergen("Eggs");

        allergenList.put(emp1.getAllergen(), emp1);
        allergenList.put(emp2.getAllergen(), emp2);
    }

    @GetMapping("/user")
    public String userProfile(Model model) {
        model.addAttribute("allergenList", new ArrayList<>(allergenList.values()));
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
        return "redirect:/user";   
    }
}
