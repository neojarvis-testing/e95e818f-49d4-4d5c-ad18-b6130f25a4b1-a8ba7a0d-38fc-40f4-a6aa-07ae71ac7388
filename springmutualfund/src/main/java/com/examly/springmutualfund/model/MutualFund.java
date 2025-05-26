package com.examly.springmutualfund;

@Entity
public class MutualFund {
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
