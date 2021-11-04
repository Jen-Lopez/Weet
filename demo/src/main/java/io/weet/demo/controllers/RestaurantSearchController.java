package io.weet.demo.controllers;

import io.weet.demo.services.OpenMenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class RestaurantSearchController {

    @Autowired
    OpenMenuService openMenuService;

    @GetMapping("/search")
    public String RestaurantSearchForm() {
        return "restaurantSearch";
    }

    @GetMapping("/getRestaurants")
    public String RestaurantSearchResults(@RequestParam(name="zip") String zipcode, Model model) {
        model.addAttribute("results", openMenuService.getResults(zipcode));
        return "displayRestaurants";
    }
    
}
