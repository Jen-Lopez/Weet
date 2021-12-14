package io.weet.demo.controllers;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class Home {
    @GetMapping("/")
    public String Welcome() {
        return "home";
    }

    @GetMapping("/error")
    public String error() {
        return "error";
    }
}
