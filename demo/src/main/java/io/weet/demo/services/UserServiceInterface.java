package io.weet.demo.services;

import org.springframework.security.core.userdetails.UserDetailsService;

import io.weet.demo.models.UserModel;

public interface UserServiceInterface  extends UserDetailsService {
    UserModel saveUser(UserModel user);
    UserModel getUser(String email);
}
