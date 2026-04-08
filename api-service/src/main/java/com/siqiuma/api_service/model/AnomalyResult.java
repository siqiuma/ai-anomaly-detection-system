package com.siqiuma.api_service.model;

import jakarta.persistence.*;
import java.time.Instant;

@Entity
@Table(name = "anomaly_results")
public class AnomalyResult {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "transaction_id", nullable = false)
    private String transactionId;

    @Column(name = "anomaly_score", nullable = false)
    private Integer anomalyScore;

    @Column(name = "risk_level", nullable = false)
    private String riskLevel;

    @Column(name = "is_flagged", nullable = false)
    private Boolean flagged;

    @Column(name = "matched_rule", nullable = false)
    private String matchedRule;

    @Column(name = "detected_at", nullable = false)
    private Instant detectedAt;

    public Long getId() {
        return id;
    }

    public String getTransactionId() {
        return transactionId;
    }

    public Integer getAnomalyScore() {
        return anomalyScore;
    }

    public String getRiskLevel() {
        return riskLevel;
    }

    public Boolean getFlagged() {
        return flagged;
    }

    public String getMatchedRule() {
        return matchedRule;
    }

    public Instant getDetectedAt() {
        return detectedAt;
    }
}