package com.siqiuma.detection_service.rule;

public class RuleEvaluationResult {

    private final String ruleName;
    private final int score;
    private final boolean triggered;

    public RuleEvaluationResult(String ruleName, int score, boolean triggered) {
        this.ruleName = ruleName;
        this.score = score;
        this.triggered = triggered;
    }

    public String getRuleName() {
        return ruleName;
    }

    public int getScore() {
        return score;
    }

    public boolean isTriggered() {
        return triggered;
    }
}