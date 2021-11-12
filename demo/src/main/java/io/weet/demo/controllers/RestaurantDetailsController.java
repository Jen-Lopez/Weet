package io.weet.demo.controllers;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class RestaurantDetailsController {
    @GetMapping("/restaurant")
    // public String fetchRestDetails() {
    //     return "restaurantProfile";
    // }

    public String fetchRestDetails(@RequestParam("id") String restId, Model model) {
        model.addAttribute("restaurantID", restId);
        return "restaurantProfile";
    }
}
