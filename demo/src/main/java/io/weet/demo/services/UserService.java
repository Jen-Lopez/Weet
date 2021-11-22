package io.weet.demo.services;

import java.util.List;

import io.weet.demo.models.Allergen;
import io.weet.demo.models.User;

public interface UserService {
    User saveUser(User user);
    void addAllergies(User user, Allergen allergen);
    User getUser(String username); //fetches user from Database based on name
    List<User>getUsers(); //fetches all users; may not need
}
