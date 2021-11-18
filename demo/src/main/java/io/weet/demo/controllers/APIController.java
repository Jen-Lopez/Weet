package io.weet.demo.controllers;

import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import io.weet.demo.models.Restaurant;
import io.weet.demo.services.OpenMenuService;

@RestController
public class APIController {

    @Autowired
    OpenMenuService openMenuService;

    @GetMapping("/getMap")
    public Map<String, Object> getMapData() {
        Map<String, Object> results = new HashMap<>();
        results.put("location", openMenuService.getCoordinates());
        ArrayList<Map<String, Object>> rests = new ArrayList<>();
        for (Restaurant r : openMenuService.getResults().values()) {
            Map<String, Object> currRest = new HashMap<>(); 
            currRest.put("name", r.getRestName());
            currRest.put("lat", Float.parseFloat(r.getLat()));
            currRest.put("long", Float.parseFloat(r.getLong()));
            rests.add(currRest);
        }

        results.put("all", rests);
        return results;
    }

}
