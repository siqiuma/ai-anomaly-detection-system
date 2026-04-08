package com.siqiuma.detection_service.rule;

public class RuleEngineResult {

    private final int totalScore;
    private final String matchedRule;

    public RuleEngineResult(int totalScore, String matchedRule) {
        this.totalScore = totalScore;
        this.matchedRule = matchedRule;
    }

    public int getTotalScore() {
        return totalScore;
    }

    public String getMatchedRule() {
        return matchedRule;
    }
}