package io.weet.demo.controllers;

import io.weet.demo.models.Allergen;
import io.weet.demo.models.DietaryRestriction;
import io.weet.demo.models.Restaurant;
import io.weet.demo.models.UserModel;
import io.weet.demo.services.AllergenService;
import io.weet.demo.services.OpenMenuService;
import io.weet.demo.services.UserService;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class RestaurantSearchController {
    
    Authentication authentication;
    private UserModel user; 

    @Autowired
    UserService userService;

    @Autowired
    OpenMenuService openMenuService;

    @Autowired
    AllergenService allergenService;

    boolean madeFirstSearch;

    @PostConstruct
    public void loadAllergens() {
        allergenService.readAllergens();
    }

    @GetMapping("/search")
    public String RestaurantSearch(Model model) {
        List<Restaurant> results = new ArrayList<>(openMenuService.getResults().values());
        model.addAttribute("results", results);
        model.addAttribute("processed", madeFirstSearch);
        return "restaurantSearch";
    }

    @GetMapping("/getRestaurants")
    public String RestaurantSearchResults(@RequestParam(name = "city") String city, @RequestParam(name = "state") String state, @RequestParam(name = "nbhood") String nbhood, @RequestParam(name = "zip") String zip, @RequestParam(name = "lat") String latCoords, @RequestParam(name = "long") String longCoords) {
        authentication = SecurityContextHolder.getContext().getAuthentication();
        user = userService.getUser(authentication.getName());

        List<Allergen> allergens = user.getAllergies();
        List<DietaryRestriction> restrictions = user.getRestrictions();
        
        openMenuService.clearQueries();

        for (Allergen allergen : allergens) {
            openMenuService.addQueries(allergen.getAllergen(), allergenService.getKeywords(allergen.getAllergen()));
        }

        for (DietaryRestriction restriction : restrictions) {
            openMenuService.addQueries(restriction.getDietaryRestriction(), null);
        }

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
