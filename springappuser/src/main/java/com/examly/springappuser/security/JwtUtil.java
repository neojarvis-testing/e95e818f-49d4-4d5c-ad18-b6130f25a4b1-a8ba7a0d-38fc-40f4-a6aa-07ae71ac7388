package com.examly.springappuser.security;

import java.util.Date;

import org.springframework.stereotype.Component;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Component
public class JwtUtil {

    private static final String SECRET_KEY = "r8sJ0b3VJdj84nA7RmFqg4Xxv2hP1oZ3Xz7E0JqHq9Q=";

    public String generateToken(String email){
        return Jwts.builder()
        .setSubject(email)
        .setIssuedAt(new Date())
        .setExpiration(new Date(System.currentTimeMillis() + 1000*60*60*10))
        .signWith(SignatureAlgorithm.HS256, SECRET_KEY)
        .compact();
    }
    
}
