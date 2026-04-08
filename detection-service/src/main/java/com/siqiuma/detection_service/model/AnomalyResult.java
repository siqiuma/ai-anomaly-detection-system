package com.siqiuma.detection_service.model;

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

    @PrePersist
    public void prePersist() {
        if (detectedAt == null) {
            detectedAt = Instant.now();
        }
    }

    public Long getId() {
        return id;
    }

    public String getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(String transactionId) {
        this.transactionId = transactionId;
    }

    public Integer getAnomalyScore() {
        return anomalyScore;
    }

    public void setAnomalyScore(Integer anomalyScore) {
        this.anomalyScore = anomalyScore;
    }

    public String getRiskLevel() {
        return riskLevel;
    }

    public void setRiskLevel(String riskLevel) {
        this.riskLevel = riskLevel;
    }

    public Boolean getFlagged() {
        return flagged;
    }

    public void setFlagged(Boolean flagged) {
        this.flagged = flagged;
    }

    public String getMatchedRule() {
        return matchedRule;
    }

    public void setMatchedRule(String matchedRule) {
        this.matchedRule = matchedRule;
    }

    public Instant getDetectedAt() {
        return detectedAt;
    }
}