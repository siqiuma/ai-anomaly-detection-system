package com.siqiuma.detection_service.rule;
import java.util.List;

public class RuleEngineResult {

    private final int totalScore;
    private final List<String> matchedRules;

    public RuleEngineResult(int totalScore, List<String> matchedRules) {
        this.totalScore = totalScore;
        this.matchedRules = matchedRules;
    }

    public int getTotalScore() {
        return totalScore;
    }

    public List<String> getMatchedRules() {
        return matchedRules;
    }
}