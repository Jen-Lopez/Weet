package io.weet.demo.controllers;

import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import io.weet.demo.services.OpenMenuService;

@RestController
public class APIController {

    @Autowired
    OpenMenuService openMenuService;
    
    @GetMapping("/getMap")
    public Map<String, Float> getCoordinates() {
        return openMenuService.getCoordinates();
    }
}

/**
 * {location: {lat, lng}, all: [{lat, lng} , {}, {}]}
 * 
 */
