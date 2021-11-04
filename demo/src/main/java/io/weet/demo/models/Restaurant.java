package io.weet.demo.models;

import java.util.ArrayList;

public class Restaurant {
    private String id;
    private String name;
    private String description;
    private String address;
    private String cuisine; 
    private ArrayList<Dish> menuItems = new ArrayList<>();
    
    public String getRestId() {
        return id;
    }

    public String getRestName() {
        return name;
    }

    public String getRestDescription() {
        return description;
    }

    public String getRestAddress() {
        return address;
    }

    public String getRestCuisine() {
        return cuisine;
    }

    public void setRestId(String id){
        this.id = id;
    }

    public void setRestName(String name) {
        this.name = name;
    }

    public void setRestDescription(String des) {
        this.description = des;
    }

    public void setRestAddress(String address) {
        this.address = address;
    }

    public void setRestCuisine(String cuisine) {
        this.cuisine = cuisine;
    }

    @Override
    public String toString() {
        return String.format("ID: %s\nRestaurant Name: %s\nAddress: %s\nCuisine Type: %s\nDescription: %s\n", id, name, address, cuisine, description);
    }

}
