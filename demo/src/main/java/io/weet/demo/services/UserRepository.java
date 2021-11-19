package io.weet.demo.services;
import java.util.List;
import org.springframework.data.mongodb.repository.MongoRepository;

import io.weet.demo.models.Allergen;
import io.weet.demo.models.User;

public interface UserRepository extends MongoRepository<User, String> {

    public User findByFirstName(String firstName);
    public List<User> findByLastName(String lastName);
    public List<User> findByusername(String username);
    public void addAllergies(User user, Allergen allergen);

}