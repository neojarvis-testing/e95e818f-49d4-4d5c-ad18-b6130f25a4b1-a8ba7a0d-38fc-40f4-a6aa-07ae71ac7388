package com.examly.springappuser.controller;

import org.springframework.boot.autoconfigure.neo4j.Neo4jProperties.Authentication;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.examly.springappuser.model.LoginDTO;

@RestController
public class AuthController {

    public ResponseEntity<?> login(@RequestBody LoginDTO loginDTO)
    {
        Authentication authentication = authenticationManager.authenticate
    }

    
}
