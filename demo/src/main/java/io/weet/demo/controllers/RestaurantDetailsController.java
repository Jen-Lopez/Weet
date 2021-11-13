package io.weet.demo.controllers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import io.weet.demo.services.OpenMenuService;

@Controller
public class RestaurantDetailsController {
    
    @Autowired
    OpenMenuService openMenuService;
    
    @GetMapping("/restaurant")
    public String fetchRestDetails(@RequestParam("id") String restId, Model model) {
        if (!restId.isEmpty()) {
            System.out.println(openMenuService.getRestaurantDetails(restId));
            model.addAttribute("restaurant", openMenuService.getRestaurantDetails(restId));
        }
        return "restaurantProfile";
    }
}
