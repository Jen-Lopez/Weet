package io.weet.demo.controllers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import io.weet.demo.models.Restaurant;
import io.weet.demo.services.OpenMenuService;

@Controller
public class RestaurantDetailsController {
    
    @Autowired
    OpenMenuService openMenuService;

    Restaurant rest; 

    @GetMapping("/restaurant")
    public String RestaurantSearch(Model model) {
        if (rest != null) {
            model.addAttribute("restaurant", rest);
            model.addAttribute("yelp", rest.getYelpData());
        }
        rest = null;
        return "restaurantProfile";
    }

    @GetMapping("/getRestaurant")
    public String fetchRestDetails(@RequestParam("id") String restId, Model model) {
        rest = openMenuService.getRestaurantDetails(restId);
        return "redirect:/restaurant";
    }
}
