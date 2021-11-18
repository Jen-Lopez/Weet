package io.weet.demo.controllers;

import io.weet.demo.models.Restaurant;
import io.weet.demo.services.OpenMenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;

@Controller
public class UserLoginController {

    @GetMapping("/checkLogin")
    public String test() {
        return "userLogin";
    }

}
