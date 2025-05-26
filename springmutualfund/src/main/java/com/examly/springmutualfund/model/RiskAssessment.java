package com.examly.springmutualfund.model;

import java.time.LocalDateTime;

import jakarta.persistence.Entity;
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
public class RiskAssessment {
    private long riskAssessmentId;
    private MutualFund mutualFundId;
    private String riskLevel;
    private String analysisDetails;
    private LocalDateTime assesmentDate;
}
