package io.security;
import io.javabrains.springsecurityjwt.util;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import java.sql.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Date;

import com.mongodb.Function;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

@Service
public class JwtUtil {
    
    //private static String token;
    //private static final String TOKEN2 = token;
    //private static final String EXTRACT_CLAIM = extractClaim(TOKEN2, Claims:: getSubject);
    private String SECRET_KEY = "secret";

    //takes in token and returns username for that token
    public String extractUsername(String token){
        return extractClaim(token, Claims::getSubject);
    }

    //takes in token and returns date for that token
    public Date extractExpiration(String token){
        return (Date) extractClaim(token, Claims:: getExpiration);
    }

    public <T> T extractClaim(String token, Function<Claims, T> claimsResolver){
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }

    private Claims extractAllClaims(String token){
        return Jwts.parser().setSigningKey(SECRET_KEY).parseClaimsJws(token).getBody();
    }

    //see if token is expired
    private Boolean isTokenExpired(String token){
        Date date = new Date(0); // check this
        return extractExpiration(token).before(date);
    }
    //create JWT based off of UserDetails
    public String generateToken(UserDetails userDetails){
        Map<String, Object> claims = new HashMap<>(); //currently empty map of claims
        return createToken(claims, userDetails.getUsername());
    }
    //creates token based off of claims and username; used in generateToken
    private String createToken(Map<String, Object> claims, String subject){
        return Jwts.builder().setClaims(claims).setSubject(subject).setIssuedAt(new Date(System.currentTimeMillis()))
            .setExpiration(new Date(System.currentTimeMillis() + 1000*60*60*10)).signWith(SignatureAlgorithm.HS256, SECRET_KEY).compact();
    }

    //validate tokens
    public Boolean validateToken(String token, UserDetails userDetails){
        final String username = extractUsername(token);
        return (username.equals(userDetails.getUsername()) &&!isTokenExpired(token));
    }




}
