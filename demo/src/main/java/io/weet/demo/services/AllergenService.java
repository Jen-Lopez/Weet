package io.weet.demo.services;

import java.io.*;
import java.util.*;
import org.springframework.stereotype.Service;
import org.json.simple.parser.*;
import org.json.simple.*;

@Service
public class AllergenService {
    Map <String, ArrayList<String>> allergenKeywords = new HashMap<>();

    public boolean keywordDoesExist(String key) {
        key = key.toLowerCase();
        return allergenKeywords.containsKey(key);
    }

    public ArrayList<String> getKeywords (String allergen) {
        allergen = allergen.toLowerCase();
        return allergenKeywords.get(allergen);
    }

    public void readAllergens() {
        try {
            InputStream in = getClass().getClassLoader().getResourceAsStream("data/allergens.json");
            JSONParser parser = new JSONParser();
            JSONObject root = (JSONObject) parser.parse(new InputStreamReader(in, "UTF-8"));

            JSONArray allergens = (JSONArray) root.get("allergens");

            for (int i = 0; i < allergens.size(); i++) {
                ArrayList<String> keywords = new ArrayList<>();

                JSONObject currObj = (JSONObject) allergens.get(i);
                String allergen = currObj.get("allergen").toString();
                JSONArray keys = (JSONArray) currObj.get("keywords");

                for (int j = 0; j < keys.size(); j++) {
                    keywords.add(keys.get(j).toString());
                }

                allergenKeywords.put(allergen, keywords);
            }

        }
        catch (IOException | ParseException e) {
            System.out.println("Unable to read file.");
        }

    }
}
