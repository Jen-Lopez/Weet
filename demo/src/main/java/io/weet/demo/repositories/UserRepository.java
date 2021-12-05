package io.weet.demo.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import io.weet.demo.models.UserModel;

@Repository
public interface UserRepository extends MongoRepository<UserModel, String> {

    public UserModel findByEmail(String email);
    // public void addAllergies(User user, Allergen allergen);
        
    

    //public UserModel addAllergies(String name);
    //public UserModel deleteAllergies(String name);
    //public UserModel findByAllergies();


}