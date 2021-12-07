package io.weet.demo.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import io.weet.demo.models.Dish;
import io.weet.demo.models.Menu;
import io.weet.demo.models.Restaurant;
import io.weet.demo.models.YelpInfo;

import java.util.*;
import org.json.*;
import java.net.http.*;
import java.io.IOException;
import java.net.URI;

@Service
public class OpenMenuService {
    @Autowired
    YelpService yelpService;

    @Value("${API_KEY}")
    private String API_KEY;
    private Map<String, Float> coordinates = new HashMap<>();
    private Map<String, Object> locationDetails = new HashMap<>();
    private Map<String, Restaurant> restaurants = new HashMap<>();
    private ArrayList<String> search;

    public void setSearch(ArrayList<String> query) {
        this.search = query;
    }

    public void setLocationDetails(String key, Object value) { 
        this.locationDetails.put(key, value);
    }

    public void setCoordinates(String key, Float value) {
        this.coordinates.put(key, value);
    }

    public Map <String, Float> getCoordinates() {
        return coordinates;
    }

    public Map <String, Object> getLocationDetails() {
        return locationDetails;
    }

    public Map<String, Restaurant> getResults() {
        return restaurants;
    }

    public Restaurant getRestaurantDetails(String id) {
        return restaurants.get(id);
    }

    public void fetchRestaurantsWrapper() {
        try {
            restaurants.clear();
            for (String keyword : search) {
                System.out.println("IN FETCH ... " + keyword);
                keyword = keyword.toLowerCase().replace(" ", "%20");
                searchRestaurants(keyword, (String)locationDetails.getOrDefault("city", ""), (String)locationDetails.getOrDefault("state", ""), (String)locationDetails.getOrDefault("zip", ""));
            }
            locationDetails.clear();
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }

    private void searchRestaurants(String query, String city, String state, String zip) throws IOException, InterruptedException {
        String SEARCH_ENDPOINT = String.format("https://openmenu.com/api/v2/search.php?key=%s&s=%s&postal_code=%s&city=%s&state=%s&country=%s", API_KEY, query, zip, city, state, "US");
        System.out.println(String.format("The args are ---- city: %s state: %s zip: %s", city,state, zip));
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
            .uri(URI.create(SEARCH_ENDPOINT))
            .GET()
            .build();
        System.out.println("GETTING RELEVANT RESULTS....");
        HttpResponse<String> httpResponse = client.send(request, HttpResponse.BodyHandlers.ofString());
        JSONObject root = new JSONObject(httpResponse.body());

        JSONObject result = root.getJSONObject("response").getJSONObject("result");

        if (result.has("errors")) {
            System.out.println("No Results.");
            return;
        }

        JSONArray matchingItems =result.getJSONArray("items");

        for (int i = 0; i < matchingItems.length(); i++) {
            JSONObject currItem = matchingItems.getJSONObject(i);
            Dish newDish = new Dish();
            newDish.setRestID(currItem.getString("id"));
            newDish.setName(currItem.getString("menu_item_name"));
            newDish.setDescription(currItem.getString("menu_item_description"));
            
            if (!JSONObject.NULL.equals(currItem.get("menu_item_price"))){
                newDish.setPrice((String)currItem.get("menu_item_price"));
            }
                  
            if (!JSONObject.NULL.equals(currItem.get("image_url"))){
                newDish.setThumbnail((String)currItem.get("image_url"));
            }

            Restaurant rest;
            if (!restaurants.containsKey(newDish.getResParentID())) {
                rest = fetchRestaurantDetails(newDish.getResParentID());
            }
            else {
                rest = getRestaurantDetails(newDish.getResParentID());
            }

            rest.addMenuItemToUser(newDish);
            restaurants.put(rest.getRestId(), rest);
        }
    }

    private Restaurant fetchRestaurantDetails(String id) throws IOException, InterruptedException {
        String RESTAURANT_ENDPOINT = String.format("https://openmenu.com/api/v2/restaurant.php?key=%s&id=%s", API_KEY, id);
        HttpClient client = HttpClient.newHttpClient();
            HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(RESTAURANT_ENDPOINT))
                .GET()
                .build();
        System.out.println("GETTING RESTAURANT INFO");
        Restaurant newRestaurant = new Restaurant();
        HttpResponse<String> httpResponse = client.send(request, HttpResponse.BodyHandlers.ofString());
        JSONObject root = new JSONObject(httpResponse.body());
        JSONObject result = root.getJSONObject("response").getJSONObject("result");

        JSONObject restaurantInfo = result.getJSONObject("restaurant_info");
        JSONArray menuInfo = result.getJSONArray("menus");

        String mainCuisine = result.getJSONObject("environment_info").getString("cuisine_type_primary");
        
        newRestaurant.setRestId(id);
        newRestaurant.setRestCuisine(mainCuisine);
        newRestaurant.setRestName(restaurantInfo.getString("restaurant_name"));
        newRestaurant.setRestDescription(restaurantInfo.getString("brief_description"));
        newRestaurant.setRestAddress(restaurantInfo.getString("address_1"));
        newRestaurant.setCity(restaurantInfo.getString("city_town"));
        newRestaurant.setState(restaurantInfo.getString("state_province"));
        newRestaurant.setZip(restaurantInfo.getString("postal_code"));
        newRestaurant.setLatitude(restaurantInfo.getString("latitude"));
        newRestaurant.setLongitude(restaurantInfo.getString("longitude"));
        newRestaurant.setWebsite(restaurantInfo.getString("website_url"));
        newRestaurant.setPhone(restaurantInfo.getString("phone"));

        Menu menu = new Menu();

        for (int m = 0; m < menuInfo.length(); m++) {
            JSONArray menuGroups = menuInfo.getJSONObject(m).getJSONArray("menu_groups");
            for (int mg = 0; mg < menuGroups.length(); mg++) {
                JSONObject group = menuGroups.getJSONObject(mg);
                menu.addMenuGroup(group.getString("group_name"));
                JSONArray menuItems = group.getJSONArray("menu_items");
                ArrayList<Dish> groupDishes = new ArrayList<>();

                for (int mi = 0; mi < menuItems.length(); mi++) {
                    JSONObject item = menuItems.getJSONObject(mi);
                    Dish newDish = new Dish();
                    newDish.setName(item.getString("menu_item_name"));
                    newDish.setDescription(item.getString("menu_item_description"));

                    if (!JSONObject.NULL.equals(item.get("menu_item_price"))){
                        newDish.setPrice((String)item.get("menu_item_price"));
                    }

                    groupDishes.add(newDish);
                }
                menu.addGroupDishes(groupDishes);
            }
        }
        newRestaurant.setMenu(menu);

        YelpInfo data = yelpService.fetchReviewsWrapper(newRestaurant.getRestName().replace(" ", "%20"), newRestaurant.getLat(), newRestaurant.getLong());

        if (data.getRestName() != null) {
            if ((data.getRestName().toLowerCase()).contains((newRestaurant.getRestName()).toLowerCase())) {
                newRestaurant.setYelpData(data);
            }
        }

        return newRestaurant;
    }

}
