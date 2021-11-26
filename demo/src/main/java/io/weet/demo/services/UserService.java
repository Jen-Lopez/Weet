package io.weet.demo.services;

import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import io.weet.demo.models.UserModel;
import io.weet.demo.repositories.UserRepository;

@Service
public class UserService implements UserServiceInterface {

    private final Set<GrantedAuthority> authorities = new HashSet<>();

    @Autowired
    UserRepository userRepository;

    @Autowired
    BCryptPasswordEncoder passwordEncoder;

    @Override
    public UserModel saveUser(UserModel user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepository.save(user);
    }

    @Override
    public UserModel getUser(String email) {
        UserModel user = userRepository.findByEmail(email);
        return user;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    
        UserModel user = userRepository.findByEmail(username);

        if (user == null) {
            throw new UsernameNotFoundException("Invalid email and/or password");
        }
        authorities.add(new SimpleGrantedAuthority("USER"));
        return new User(user.getEmail(), user.getPassword(), authorities);
    }

}
