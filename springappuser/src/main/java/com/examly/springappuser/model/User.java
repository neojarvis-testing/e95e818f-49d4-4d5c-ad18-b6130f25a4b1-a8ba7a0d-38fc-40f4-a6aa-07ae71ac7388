package com.examly.springappuser.model;

import java.math.BigDecimal;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int userId;
    private String username;
    private String email;
    private String mobileNumber;
    private String password;

    @Enumerated(EnumType.STRING)
    private UserRole userRole;

    private boolean isActive;
    private BigDecimal balance;

    @OneToMany(mappedBy = "investor", cascade = CascadeType.ALL)
    private List<Investment> investments;
    
}
