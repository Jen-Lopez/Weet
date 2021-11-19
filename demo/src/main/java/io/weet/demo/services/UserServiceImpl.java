package io.weet.demo.services;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import io.weet.demo.models.Allergen;
import io.weet.demo.models.User;

@Service @Transactional
public class UserServiceImpl implements UserService{

    private final UserRepository userRepo;

    @Override
    public User saveUser(User user) {
        // TODO Auto-generated method stub
        return userRepo.save(user);
    }

    @Override
    public void addAllergies(User user, Allergen allergen) {
        // TODO Auto-generated method stub
        userRepo.addAllergies(user, allergen);  
        
        
    }

    @Override
    public User getUser(String username) {
        // TODO Auto-generated method stub
        User user = userRepo.findByFirstName(username);
        return user;
    }

    @Override
    public List<User> getUsers() {
        // TODO Auto-generated method stub
        return userRepo.findAll();
    }
    
}
