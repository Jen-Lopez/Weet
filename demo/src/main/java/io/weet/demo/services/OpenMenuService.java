package io.weet.demo.services;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import io.weet.demo.models.Dish;
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
    private Map<String, Restaurant> restaurants = new HashMap<>();

    public Map<String, Restaurant> getResults() {
        return restaurants;
    }

    public Restaurant getRestaurantDetails(String id) {
        return restaurants.get(id);
    }

    public void fetchRestaurantsWrapper(String zip, String city) {
        try {
            restaurants.clear();
            fetchRestaurants(zip, city);
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }

    private void fetchRestaurants(String zip, String city) throws IOException, InterruptedException {
        Map <String, Restaurant> newRestaurants = new HashMap<>();
        String LOCATION_ENDPOINT = String.format("https://openmenu.com/api/v2/location.php?key=%s&postal_code=%s&city=%s&state=%s&country=%s", API_KEY, zip, city, "NY", "US");
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
            .uri(URI.create(LOCATION_ENDPOINT))
            .GET()
            .build();
            System.out.println("GETTING RESTAURANTS");
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
                newRest.setLatitude(jsonRest.getString("latitude"));
                newRest.setLongitude(jsonRest.getString("longitude"));
                newRest.setWebsite(jsonRest.getString("website_url"));

                fetchRestaurantDetails(newRest);
                newRestaurants.put(newRest.getRestId(), newRest);                
            }

            this.restaurants = newRestaurants;
    }

    private void fetchRestaurantDetails(Restaurant rest) throws IOException, InterruptedException {
        String RESTAURANT_ENDPOINT = String.format("https://openmenu.com/api/v2/restaurant.php?key=%s&id=%s", API_KEY, rest.getRestId());
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
            .uri(URI.create(RESTAURANT_ENDPOINT))
            .GET()
            .build();
        System.out.println("GETTING RESTAURANT INFO");
        HttpResponse<String> httpResponse = client.send(request, HttpResponse.BodyHandlers.ofString());
        JSONObject root = new JSONObject(httpResponse.body());
        JSONObject result = root.getJSONObject("response").getJSONObject("result");

        JSONArray menus = result.getJSONArray("menus");
        String restPhoneNumber = result.getJSONObject("restaurant_info").getString("phone");
        rest.setPhone(restPhoneNumber);

        for (int m = 0; m < menus.length(); m++) {
            JSONArray menuGroups = menus.getJSONObject(m).getJSONArray("menu_groups");
            for (int mg = 0; mg < menuGroups.length(); mg++) {
                JSONArray menuItems = menuGroups.getJSONObject(mg).getJSONArray("menu_items");
                for (int mi = 0; mi < menuItems.length(); mi++) {
                    JSONObject menuItem = menuItems.getJSONObject(mi);
                    Dish newDish = new Dish();

                    newDish.setName(menuItem.getString("menu_item_name"));  
                    newDish.setDescription(menuItem.getString("menu_item_description"));

                    newDish.setAllergyInfo(menuItem.getString("menu_item_allergy_information"));
                    newDish.setAllergens(menuItem.getString("menu_item_allergy_information_allergens"));
                    
                    if (!JSONObject.NULL.equals(menuItem.get("vegetarian"))) {
                        newDish.setRestriction(0, Integer.parseInt((String)menuItem.get("vegetarian")));
                    }
                    if (!JSONObject.NULL.equals(menuItem.get("vegan"))) {
                        newDish.setRestriction(0, Integer.parseInt((String)menuItem.get("vegetarian")));
                    }
                    if (!JSONObject.NULL.equals(menuItem.get("kosher"))) {
                        newDish.setRestriction(0, Integer.parseInt((String)menuItem.get("vegetarian")));
                    }
                    if (!JSONObject.NULL.equals(menuItem.get("halal"))) {
                        newDish.setRestriction(0, Integer.parseInt((String)menuItem.get("vegetarian")));
                    }


                    JSONArray images = menuItem.getJSONArray("menu_item_images");
                    for (int i = 0; i < images.length(); i++) {
                        newDish.addImage(images.getString(i));
                    }

                    rest.addMenuItem(newDish);
                }
            }
        }

    }
}
