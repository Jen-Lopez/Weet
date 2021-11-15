package io.weet.demo.controllers;

import io.weet.demo.models.Restaurant;
import io.weet.demo.services.OpenMenuService;

import java.util.ArrayList;
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

    boolean madeFirstSearch;

    @GetMapping("/search")
    public String RestaurantSearch(Model model) {
        List<Restaurant> results = new ArrayList<>(openMenuService.getResults().values());
        model.addAttribute("results", results);
        model.addAttribute("processed", madeFirstSearch);
        return "restaurantSearch";
    }

    @GetMapping("/getRestaurants")
    public String RestaurantSearchResults(@RequestParam(name = "location") String city) {
        city = city.replace(" ", "%20");
        openMenuService.fetchRestaurantsWrapper("vegan", "", city, "NY");
        madeFirstSearch = true;
        return "redirect:/search";
    }
}
