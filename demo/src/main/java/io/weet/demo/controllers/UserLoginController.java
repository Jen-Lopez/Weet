package io.weet.demo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import io.weet.demo.security.AuthenticationRequest;
import io.weet.demo.security.AuthenticationResponse;
import io.weet.demo.security.JwtUtil;
import io.weet.demo.security.MyUserDetailsService;


@Controller
public class UserLoginController {

    @GetMapping("/login")
    public String userLogin() {
        return "userLogin";
    }

    @PostMapping("/checkLogin")
    public String validateLogin() {
        return "redirect:/user";
    }

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private MyUserDetailsService userDetailsService;

    @Autowired
    private JwtUtil jwtTokenUtil;

    @RequestMapping(value = "/authenticate", method = RequestMethod.POST) //create response entity out of AuthenticationResponse
    public ResponseEntity<?> createAuthenticationRequest(@RequestBody AuthenticationRequest authenticationRequest) throws Exception{
        
        try{
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authenticationRequest.getUsername(), authenticationRequest.getPassword()));

        }//if authentication fails, catch it with exception
        catch(BadCredentialsException e){
            throw new Exception("Incorrect usernamoe or password", e);
        }

        final UserDetails userDetails = userDetailsService.loadUserByUsername(authenticationRequest.getUsername());
        
        final String jwt = jwtTokenUtil.generateToken(userDetails); //creates token from userDetails, which is made of username

        return ResponseEntity.ok(new AuthenticationResponse(jwt)); //will return 200 status code; payload will be Authentication response

    }

}
