package com.examly.springappuser.controller;

import java.util.Map;

import org.springframework.http.ResponseEntity;
import com.examly.springappuser.security.JwtUtil;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.examly.springappuser.model.LoginDTO;
import com.examly.springappuser.model.User;
import com.examly.springappuser.repository.UserRepo;

@RestController
@RequestMapping("/api/users")
public class AuthController {

    private AuthenticationManager authenticationManager;
    private UserRepo userRepo;
    private PasswordEncoder passwordEncoder;
    private JwtUtil jwtUtil;

    public AuthenticationManager getAuthenticationManager() {
        return authenticationManager;
    }

    public void setAuthenticationManager(AuthenticationManager authenticationManager) {
        this.authenticationManager = authenticationManager;
    }

    public UserRepo getUserRepo() {
        return userRepo;
    }

    public void setUserRepo(UserRepo userRepo) {
        this.userRepo = userRepo;
    }

    public PasswordEncoder getPasswordEncoder() {
        return passwordEncoder;
    }

    public void setPasswordEncoder(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    public JwtUtil getJwtUtil() {
        return jwtUtil;
    }

    public void setJwtUtil(JwtUtil jwtUtil) {
        this.jwtUtil = jwtUtil;
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginDTO loginDTO)
    {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginDTO.getEmail(), loginDTO.getPassword()));
        String token = jwtUtil.generateToken(loginDTO.getEmail());
        return ResponseEntity.ok(Map.of("token",token));

    }

    @PostMapping("/register")
    public ResponseEntity<?> register (@RequestBody User user){
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        User savedUser = userRepo.save(user);
        return ResponseEntity.status(201).body(Map.of(
            "status","CREATED",
            "message","User Created",
            "userId", savedUser.getUserId()
        ));
    }

    
}
