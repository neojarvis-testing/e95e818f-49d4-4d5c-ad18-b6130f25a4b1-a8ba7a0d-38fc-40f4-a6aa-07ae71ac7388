package com.examly.springmutualfund.model;

import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
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
