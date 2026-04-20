package com.siqiuma.api_service.dto;

import com.siqiuma.api_service.model.AnomalyExplanation;
import com.siqiuma.api_service.model.AnomalyResult;
import com.siqiuma.api_service.model.Transaction;

import java.util.List;

public class TransactionFullViewResponse {

    private Transaction transaction;
    private List<AnomalyResult> anomalyResults;
    private List<AnomalyExplanation> explanations;

    public Transaction getTransaction() {
        return transaction;
    }

    public void setTransaction(Transaction transaction) {
        this.transaction = transaction;
    }

    public List<AnomalyResult> getAnomalyResults() {
        return anomalyResults;
    }

    public void setAnomalyResults(List<AnomalyResult> anomalyResults) {
        this.anomalyResults = anomalyResults;
    }

    public List<AnomalyExplanation> getExplanations() {
        return explanations;
    }

    public void setExplanations(List<AnomalyExplanation> explanations) {
        this.explanations = explanations;
    }
}