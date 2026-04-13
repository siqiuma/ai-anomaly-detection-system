package com.siqiuma.explanation_service.model;

import jakarta.persistence.*;
import java.time.Instant;

@Entity
@Table(name = "anomaly_explanations")
public class AnomalyExplanation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "transaction_id", nullable = false)
    private String transactionId;

    @Column(name = "explanation_text", nullable = false, length = 2000)
    private String explanationText;

    @Column(name = "created_at", nullable = false)
    private Instant createdAt;

    @Column(name = "generation_method")
    private String generationMethod;

    @Column(name = "model_name")
    private String modelName;

    @PrePersist
    public void prePersist() {
        if (createdAt == null) {
            createdAt = Instant.now();
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

    public String getExplanationText() {
        return explanationText;
    }

    public void setExplanationText(String explanationText) {
        this.explanationText = explanationText;
    }

    public Instant getCreatedAt() {
        return createdAt;
    }

    public String getGenerationMethod() {
        return generationMethod;
    }

    public void setGenerationMethod(String generationMethod) {
        this.generationMethod = generationMethod;
    }

    public String getModelName() {
        return modelName;
    }

    public void setModelName(String modelName) {
        this.modelName = modelName;
    }
}