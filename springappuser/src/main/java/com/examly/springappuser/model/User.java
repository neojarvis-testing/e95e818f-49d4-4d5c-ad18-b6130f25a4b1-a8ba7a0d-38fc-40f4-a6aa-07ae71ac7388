package com.examly.springappuser.model;

import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;

@Entity
public class User {
    @Id
    private int userId;
    private String username;
    private String email;
    private String mobileNumber;
    private String password;

    @Enumerated(EnumType.STRING)
    private Role userRole;

    private boolean isActive;
    private double balance;
    private List<Investment> investments;
    
}
