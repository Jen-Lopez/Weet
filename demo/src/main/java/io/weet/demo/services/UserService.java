package io.weet.demo.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import io.weet.demo.models.Allergen;
import io.weet.demo.models.User;

@Service @Transactional
public class UserService implements UserServiceInterface{

	@Autowired
	private UserRepository repository;
    
    @Override
    public User saveUser(User user) {
        // TODO Auto-generated method stub
        return repository.save(user);
    }

    @Override
    public void addAllergies(User user, Allergen allergen) {
        // TODO Auto-generated method stub
        // repository.addAllergies(user, allergen);  
    }

    @Override
    public User getUser(String username) {
        // TODO Auto-generated method stub
        User user = repository.findByFirstName(username);
        return user;
    }

    @Override
    public List<User> getUsers() {
        // TODO Auto-generated method stub
        return repository.findAll();
    }
    
}
