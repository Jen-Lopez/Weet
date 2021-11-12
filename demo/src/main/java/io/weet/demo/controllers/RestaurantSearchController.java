package io.weet.demo.controllers;

import io.weet.demo.models.Restaurant;
import io.weet.demo.services.OpenMenuService;

import java.util.List;

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
    public String RestaurantSearch(Model model) {
        List<Restaurant> results = openMenuService.getResults();
        model.addAttribute("results", results);
        return "restaurantSearch";
    }

    @GetMapping("/getRestaurants")
    public String RestaurantSearchResults(@RequestParam(name = "zip") String zipcode) {
        openMenuService.fetchRestaurantsWrapper(zipcode, "");
        return "redirect:/search";
    }

}
