package io.weet.demo.models;

import java.util.ArrayList;

public class Dish {
    private String itemName;
    private String description;
    private double price;
    private String allergyInfo;
    private String allergens;

    // Bitmap for restrictions [vegetarian, vegan, kosher, halal]
    private int[] restriction;
    private ArrayList<String> menuItemImages;

    public Dish() {
        restriction = new int[4];
        menuItemImages = new ArrayList<>();
    }

    public String getItemName() {
        return itemName;
    }

    public String getItemDescription() {
        return description;
    }

    public double getPrice() {
        return price;
    }

    public String getAllergyInfo() {
        return allergyInfo;
    }

    public String getAllergens() {
        return allergens;
    }

    public int[] getRestrictions() {
        return restriction;
    }

    public ArrayList<String> getItemImages() {
        return menuItemImages;
    }

    public void setName(String name) {
        this.itemName = name;
    }

    public void setDescription(String des) {
        this.description = des;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public void setAllergyInfo(String info) {
        this.allergyInfo = info;
    }

    public void setAllergens(String allergens) {
        this.allergens = allergens;
    }

    public void setRestriction(int index, int value) {
        this.restriction[index] = value;
    }

    public void addImage(String image) {
        this.menuItemImages.add(image);
    }

}
