package com.siqiuma.explanation_service.dto;

import java.util.List;

public class ExplanationResponse {
    private String risk_summary;
    private List<String> reasons;

    public String getRisk_summary() {
        return risk_summary;
    }

    public void setRisk_summary(String risk_summary) {
        this.risk_summary = risk_summary;
    }

    public List<String> getReasons() {
        return reasons;
    }

    public void setReasons(List<String> reasons) {
        this.reasons = reasons;
    }
}