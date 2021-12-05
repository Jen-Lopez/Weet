package io.weet.demo.models;
import java.util.ArrayList;
import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document("user")
public class UserModel {

    @Id
    public String id;
    public String name;
    private String email;
    private String password;
    private List<Allergen> allergens;
    private List<DietaryRestriction> restrictions;

    public UserModel(String name, String email, String password, List<Allergen> allergens, List<DietaryRestriction> restrictions) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.allergens = allergens;
        this.restrictions = restrictions;
    }

    public String getName() {
        return name;
    }

    public void setName(String name){
        this.name = name;
    }

    public String getEmail() {
        return email;
    }
    
    public void setEmail(String email){
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public List<Allergen> getAllergies(){
        return allergens;
    }

    public List<DietaryRestriction> getRestrictions(){
        return restrictions;
    }

    public void setPassword(String pwd) {
        this.password = pwd;
    }
    
    public void setAllergens(ArrayList<Allergen> al){
        this.allergens = al;
    }

    public void setDiet(ArrayList<DietaryRestriction> diet){
        this.restrictions = diet;
    }

    public String toString() {
        return String.format(
                "User[id=%s, fullname='%s', email='%s']",
                id, name, email);
    }

}