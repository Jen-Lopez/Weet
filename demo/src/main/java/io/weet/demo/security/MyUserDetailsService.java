package io.weet.demo.security;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import io.weet.demo.models.User;
import io.weet.demo.services.UserRepository;

@Service
public class MyUserDetailsService implements UserDetailsService{

    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByusername(username).get(0);

        if (user == null) {
            throw new UsernameNotFoundException(username);
        }

        return new MyUserPrincipal(user); 
        
    }
}
