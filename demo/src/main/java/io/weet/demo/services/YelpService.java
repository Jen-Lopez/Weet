package io.weet.demo.services;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import io.weet.demo.models.YelpInfo;
import io.weet.demo.models.YelpReview;

@Service
public class YelpService {

    @Value("${YELP_API_KEY}")
    private String YELP_API_KEY;

    public YelpInfo fetchReviewsWrapper(String rest, String lat, String lon) {
        try {
            return searchYelp(rest, lat, lon);
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    
        return null;
    }

    private YelpInfo searchYelp(String restName, String lat, String lon) throws IOException, InterruptedException {
        String BUSINESS_SEARCH = String.format("https://api.yelp.com/v3/businesses/search?term=%s&latitude=%s&longitude=%s&limit=1", restName, lat, lon);
        
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
            .uri(URI.create(BUSINESS_SEARCH))
            .header("Authorization", String.format("Bearer %s", YELP_API_KEY))
            .GET()
            .build();
        System.out.println("GETTING RELEVANT YELP RESULTS....");
        HttpResponse<String> httpResponse = client.send(request, HttpResponse.BodyHandlers.ofString());
        
        JSONObject root = new JSONObject(httpResponse.body());
        JSONObject result = root.getJSONArray("businesses").getJSONObject(0);

        YelpInfo yelpData = new YelpInfo();
        yelpData.setID(result.getString("id"));
        yelpData.setName(result.getString("name"));

        if (result.has("rating")){
            yelpData.setRate(String.valueOf(result.getNumber("rating")));
        }

        if (result.has("price")){
            yelpData.setPrice(result.getString("price"));
        }

        if (result.has("image_url")){
            yelpData.setImage(result.getString("image_url"));
        }

        yelpData.setYelpURL(result.getString("url"));

        ArrayList<YelpReview> reviews = hydrateReviews(yelpData.getYelpID());
        yelpData.setReviews(reviews);

        return yelpData;
    }

    private ArrayList<YelpReview> hydrateReviews(String id) throws IOException, InterruptedException {
        
        String REVIEWS = String.format("https://api.yelp.com/v3/businesses/%s/reviews", id);
        ArrayList<YelpReview> reviews = new ArrayList<>();

        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
            .uri(URI.create(REVIEWS))
            .header("Authorization", String.format("Bearer %s", YELP_API_KEY))
            .GET()
            .build();
        System.out.println("GETTING RELEVANT YELP REVIEWS....");
        HttpResponse<String> httpResponse = client.send(request, HttpResponse.BodyHandlers.ofString());
        
        JSONObject root = new JSONObject(httpResponse.body());
        JSONArray result = root.getJSONArray("reviews");

        for (int i = 0; i < result.length(); i++) {
            YelpReview review = new YelpReview();
            JSONObject jsonReview = result.getJSONObject(i);

            review.setID(jsonReview.getString("id"));
            review.setReviewURL(jsonReview.getString("url"));
            review.setRateText(jsonReview.getString("text"));
            review.setRating(String.valueOf(jsonReview.getNumber("rating")));
            review.setTimeStamp(jsonReview.getString("time_created"));
            review.setReviewer(jsonReview.getJSONObject("user").getString("name"));

            reviews.add(review);
        }

        return reviews;
    }
}
