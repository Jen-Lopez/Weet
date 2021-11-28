package io.weet.demo.models;

import java.util.*;

public class YelpInfo {
    private String restName;
    private String id;
    private String imageURL;
    private String priceRange;
    private String yelpURL;
    private ArrayList<YelpReview> listofReviews = new ArrayList<>();

    public String getRestName() {
        return restName;
    }

    public ArrayList<YelpReview> getReviews() {
        return listofReviews;
    }

    public String getYelpID() {
        return id;
    }
    
    public String getImageURL() {
        return imageURL;
    }

    public String getPriceRange() {
        return priceRange;
    }

    public String getYelpURL() {
        return yelpURL;
    }

    public void setName(String name) {
        this.restName = name;
    }

    public void setID(String id) {
        this.id = id;
    }

    public void setImage(String img) {
        this.imageURL = img;
    }

    public void setPrice(String price) {
        this.priceRange = price;
    }

    public void setYelpURL(String url) {
        this.yelpURL = url;
    }

    public void setReviews(ArrayList<YelpReview> reviews) {
        this.listofReviews = reviews;
    }

    @Override
    public String toString() {
        return String.format("Restaurant Name: %s, ID: %s, # of reviews: %d", restName, id, listofReviews.size());
    }
}
