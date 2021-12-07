package io.weet.demo.services;

import java.io.*;
import java.util.*;
import java.nio.file.*;
import java.nio.charset.StandardCharsets;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;

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
            String content = Files.readString(Paths.get(new ClassPathResource("data/allergens.json").getURI()), StandardCharsets.US_ASCII);
            JSONObject root = new JSONObject(content);
            JSONArray allergens = root.getJSONArray("allergens");

            for (int i = 0; i < allergens.length(); i++) {
                ArrayList<String> keywords = new ArrayList<>();

                JSONObject currObj = allergens.getJSONObject(i);
                String allergen = currObj.getString("allergen");
                JSONArray keys = currObj.getJSONArray("keywords");
                for (int j = 0; j < keys.length(); j++) {
                    keywords.add(keys.getString(j));
                }
                allergenKeywords.put(allergen, keywords);
            }
        }
        catch (IOException e) {
            System.out.println("Unable to read file.");
        }

    }
}
