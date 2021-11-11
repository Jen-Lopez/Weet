package io.weet.demo.controllers;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class RestaurantDetailsController {
    @GetMapping("/restaurant")
    public String resolveRestaurantDetails() {
        return "restaurantProfile";
    }
}
