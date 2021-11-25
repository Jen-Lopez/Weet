package io.weet.demo.models;
import java.util.List;

import org.springframework.data.annotation.Id;

public class User {

    @Id
    public String id;
    public String firstName;
    public String lastName;
    private String username;
    private String password;
    private List<Allergen> allergens;

    public User() {
        id = "-1";
        firstName = "";
        lastName = "";
        username = "";
        password = "";
    }

    public User(String firstName, String lastName, String username, String password) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.username = username;
        this.password = password;
    }

    public boolean checkPassword(String password) {
        return this.password.equals(password);
    }

    public String getName(){
        return firstName + lastName;
    }

    public String getUsername(){
        return username;
    }

    public List<Allergen> getAllergies(){
        return allergens;
    }

    public String toString() {
        return String.format(
                "Customer[id=%s, firstName='%s', lastName='%s']",
                id, firstName, lastName);
    }





}