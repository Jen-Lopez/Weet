package io.weet.demo.controllers;

import io.weet.demo.models.Restaurant;
import io.weet.demo.services.OpenMenuService;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;

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

    ArrayList<String> allergens = new ArrayList<>();
    ArrayList<String> restrictions = new ArrayList<>();

    @PostConstruct
    public void loadRestrictions() {
        restrictions.add("Vegan");
        restrictions.add("Vegetarian");
    }

    @GetMapping("/search")
    public String RestaurantSearch(Model model) {
        List<Restaurant> results = new ArrayList<>(openMenuService.getResults().values());
        model.addAttribute("results", results);
        model.addAttribute("processed", madeFirstSearch);
        model.addAttribute("allergens", allergens);
        model.addAttribute("restrictions", restrictions);
        return "restaurantSearch";
    }

    @GetMapping("/getRestaurants")
    public String RestaurantSearchResults(@RequestParam(name = "city") String city, @RequestParam(name = "state") String state, @RequestParam(name = "nbhood") String nbhood, @RequestParam(name = "zip") String zip, @RequestParam(name = "lat") String latCoords, @RequestParam(name = "long") String longCoords, @RequestParam(name = "restriction") String query) {
        openMenuService.setSearch(query);
        openMenuService.setCoordinates("lat", Float.parseFloat(latCoords));
        openMenuService.setCoordinates("long", Float.parseFloat(longCoords));

        if (!city.isEmpty()) {
            openMenuService.setLocationDetails("city", city.replace(" ", "%20"));
        }
        if (!zip.isEmpty()) {
            openMenuService.setLocationDetails("zip", zip);
            openMenuService.setLocationDetails("city", "");
        }

        if (!nbhood.isEmpty() && zip.isEmpty()) {
            openMenuService.setLocationDetails("city", nbhood.replace(" ", "%20"));
        }
        openMenuService.setLocationDetails("state", state);

        openMenuService.fetchRestaurantsWrapper();
        madeFirstSearch = true;
        return "redirect:/search";
    }
}
