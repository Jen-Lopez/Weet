package io.weet.demo.models;
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
    private boolean add;

    public UserModel(String name, String email, String password, List<Allergen> allergens) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.allergens = allergens;
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

    public void setPassword(String pwd) {
        this.password = pwd;
    }

    public void addAllergy(String name){
        Allergen alg = new Allergen(name);
        add = allergens.add(alg); //adds allergy to allergy list with name specified in method signature
    }

    public void deleteAllergy(String name){
        for (int i = 0; i < allergens.size(); i++){
            if (allergens.get(i).getAllergen().equalsIgnoreCase(name)){
                allergens.remove(i); //just remove the allergy from Alelrgy list with the name specified in the method signature
                break;
            }
        }
    }

    public void setAllergens(List<Allergen> al){
        allergens = al;
    }

    public String toString() {
        return String.format(
                "User[id=%s, fullname='%s', email='%s']",
                id, name, email);
    }

}