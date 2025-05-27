package com.examly.springmutualfund.model;

import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
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
public class MutualFund {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long mutualFundId;
    private String fundName;
    // Connect with User -> FundManager
    private String fundManager;
    private double currentNAV;
    private double expenseRatio;
    private String prospectus;
    private LocalDateTime dateCreated;
    private LocalDateTime lastUpdated;
}
