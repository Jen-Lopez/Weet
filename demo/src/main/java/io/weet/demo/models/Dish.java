package io.weet.demo.models;

public class Dish {
    private String restID;
    private String itemName;
    private String description;
    private String price;

    private String thumbnail;

    public String getResParentID() {
        return restID;
    }

    public String getItemName() {
        return itemName;
    }

    public String getItemDescription() {
        return description;
    }

    public String getPrice() {
        return price;
    }

    public String getThumbnail() {
        return thumbnail;
    }

    public void setRestID(String restID) {
        this.restID = restID;
    }

    public void setName(String name) {
        this.itemName = name;
    }

    public void setDescription(String des) {
        this.description = des;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public void setThumbnail(String img) {
        this.thumbnail = img;
    }

    @Override
    public String toString() {
        return String.format("Menu Item: %s\nDescription:%s\n", itemName, description);
    }

}
