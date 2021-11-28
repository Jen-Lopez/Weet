package io.weet.demo.models;

public class YelpReview {
    private String id;
    private String rating;
    private String text;
    private String timeCreated;
    private String url;
    private String reviewer;

    public String getReviewID() {
        return id;
    }

    public String getRate() {
        return rating;
    }

    public String getReview() {
        return text;
    }

    public String getTimeStamp() {
        return timeCreated;
    }

    public String getReviewUrl() {
        return url;
    }

    public String getReviewer() {
        return reviewer;
    }

    public void setID(String id) {
        this.id = id;
    }

    public void setRating(String rate) {
        this.rating = rate;
    } 

    public void setRateText(String text) {
        this.text = text;
    }

    public void setTimeStamp(String timestamp) {
        this.timeCreated = timestamp;
    }

    public void setReviewURL(String url) {
        this.url = url;
    }

    public void setReviewer(String reviewer) {
        this.reviewer = reviewer;
    }

    @Override
    public String toString() {
        return String.format("ID: %s, Rating: %s, Text: %s", id, rating, text);
    }

}
