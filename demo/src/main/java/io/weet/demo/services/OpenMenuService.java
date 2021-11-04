package io.weet.demo.services;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import io.weet.demo.models.Restaurant;
import java.util.*;
import org.json.*;
import java.net.http.*;
import java.io.IOException;
import java.net.URI;

@Service
public class OpenMenuService {

    @Value("${API_KEY}")
    private String API_KEY;
    private List<Restaurant> restaurants = new ArrayList<>();

    public List<Restaurant> getResults(String zip) {
        try {
            fetchRestaurants(zip);
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
        return restaurants;
    }

    public void fetchRestaurants(String zip) throws IOException, InterruptedException {
        List <Restaurant> newRestaurants = new ArrayList<>();

        String LOCATION_ENDPOINT = String.format("https://openmenu.com/api/v2/location.php?key=%s&postal_code=%s&city=%s&country=%s", API_KEY, zip ,"", "US");
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
            .uri(URI.create(LOCATION_ENDPOINT))
            .GET()
            .build();

            HttpResponse<String> httpResponse = client.send(request, HttpResponse.BodyHandlers.ofString());
            JSONObject root = new JSONObject(httpResponse.body());
            JSONArray restaurants = root.getJSONObject("response").getJSONObject("result").getJSONArray("restaurants");

            for (int i = 0; i < restaurants.length(); i++) {
                JSONObject jsonRest = restaurants.getJSONObject(i);
                Restaurant newRest = new Restaurant();
                newRest.setRestId(jsonRest.getString("id"));
                newRest.setRestName(jsonRest.getString("restaurant_name"));
                newRest.setRestAddress(jsonRest.getString("address_1"));
                newRest.setRestDescription(jsonRest.getString("brief_description"));
                newRest.setRestCuisine(jsonRest.getString("cuisine_type_primary"));
                newRestaurants.add(newRest);
            }

            this.restaurants = newRestaurants;
    }

    public void fetchRestaurantDetails(String restId) {

    }
}
