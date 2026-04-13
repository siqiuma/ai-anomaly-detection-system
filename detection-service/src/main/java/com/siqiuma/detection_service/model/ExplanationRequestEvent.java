package com.siqiuma.detection_service.model;

import java.util.List;

public class ExplanationRequestEvent {

    private String transactionId;
    private List<String> matchedRules;

    public String getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(String transactionId) {
        this.transactionId = transactionId;
    }

    public List<String> getMatchedRules() {
        return matchedRules;
    }

    public void setMatchedRules(List<String> matchedRules) {
        this.matchedRules = matchedRules;
    }
}