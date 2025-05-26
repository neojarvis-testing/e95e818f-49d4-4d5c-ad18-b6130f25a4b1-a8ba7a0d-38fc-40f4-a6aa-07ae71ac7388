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
public class Investment {

    @Id
    private Long investmentId;
    // Linked to Investor -> User
    private Long investorId;
    
    // Linked to MutualFund
    private int mutualFundId;

    private double amountInvested;

    private double unitsPurchased;

    private double currentValue;

    private boolean isRecurring;

    private LocalDateTime purchaseDate;
    
}
