package com.examly.springmutualfund.model;

import java.time.LocalDateTime;

import jakarta.persistence.Entity;
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
public class Transaction {

    @Id
    private long transactionId;

    private User investorId;

    private int mutualFundId;
    private String transactionType;
    private double amount;
    private double units;
    private LocalDateTime transactionDate;
    private String status;
    

    
}
