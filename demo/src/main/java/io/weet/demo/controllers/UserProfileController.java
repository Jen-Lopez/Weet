package io.weet.demo.controllers;
import io.weet.demo.models.Allergen;
import io.weet.demo.models.DietaryRestriction;
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
    private Map<String, DietaryRestriction> dietaryRestrictionList = new HashMap<>();

    // fetch this from DB
    @PostConstruct
    private void loadData(){
        Allergen emp1= new Allergen("Peanuts");
        Allergen emp2= new Allergen("Eggs");

        allergenList.put(emp1.getAllergen(), emp1);
        allergenList.put(emp2.getAllergen(), emp2);
        DietaryRestriction ex1 = new DietaryRestriction("Vegan");
        DietaryRestriction ex2 = new DietaryRestriction("Gluten Free");
        dietaryRestrictionList.put(ex1.getDietaryRestriction(), ex1);
        dietaryRestrictionList.put(ex2.getDietaryRestriction(), ex2);
    }

    @GetMapping("/user")
    public String userProfile(Model model) {
        model.addAttribute("allergenList", new ArrayList<>(allergenList.values()));
        model.addAttribute("dietaryRestrictionList", new ArrayList<>(dietaryRestrictionList.values()));

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
