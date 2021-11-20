package io.security;

//define input args to authenticate method; what user will send in post request
public class AuthenticationRequest {

    private String username;
    private String password;

    public AuthenticationRequest(String username, String password){
        this.username = username;
        this.password = password;
    }

    public AuthenticationRequest(){

    }
    
    public String getUsername(){
        return username;
    }

    public String getPassword(){
        return password;
    }
}
