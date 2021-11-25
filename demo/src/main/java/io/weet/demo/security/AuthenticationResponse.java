package io.weet.demo.security;

//output structure 
public class AuthenticationResponse {

    private final String jwt; //token; actual JWT value

    public String getJwt(){
        return jwt;
    }

    public AuthenticationResponse(String jwt){
        this.jwt = jwt;
    }
    
}
