package io.weet.demo.models;

import java.util.ArrayList;

public class Restaurant {
    private String id;
    private String name;
    private String description;
    private String address;
    private String city;
    private String state;
    private String zip;
    private String cuisine; 
    private String website;
    private String phone;
    private String latitude;
    private String longitude;
    private YelpInfo yelpData;
    private Menu menu;
    private ArrayList<Dish> userMenuItems;

    public Restaurant() {
        userMenuItems = new ArrayList<>();
    }
    
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

    public String getCity() {
        return city;
    }

    public String getState() {
        return state;
    }

    public String getZip() {
        return zip;
    }

    public String getRestCuisine() {
        return cuisine;
    }

    public String getRestSite() {
        return website;
    }

    public String getPhone() {
        return phone;
    }

    public String getLat() {
        return latitude;
    }

    public String getLong() {
        return longitude;
    }

    public Menu getMenu() {
        return menu;
    }

    public YelpInfo getYelpData() {
        return yelpData;
    }

    public ArrayList<Dish> getUserItems() {
        return userMenuItems;
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

    public void setCity(String city) {
        this.city = city;
    }
    
    public void setState(String state) {
        this.state = state;
    }

    public void setZip(String zip) {
        this.zip = zip;
    }

    public void setRestCuisine(String cuisine) {
        this.cuisine = cuisine;
    }

    public void setWebsite(String site) {
        this.website = site;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setLatitude(String lat) {
        this.latitude = lat;
    }

    public void setLongitude(String lon) {
        this.longitude = lon;
    }

    public void setYelpData(YelpInfo data) {
        this.yelpData = data;
    }

    public void setMenu(Menu menu) {
        this.menu = menu;
    }

    public void addMenuItemToUser(Dish item) {
        userMenuItems.add(item);
    }

    @Override
    public String toString() {
        return String.format("ID: %s\nRestaurant Name: %s\nAddress: %s\nCuisine Type: %s\nDescription: %s\nMenuItems:\n%d", id, name, address, cuisine, description, userMenuItems.size());
    }

}
