package com.siqiuma.detection_service.rule;

import com.siqiuma.detection_service.model.Transaction;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class DetectionRuleEngine {

    private final List<DetectionRule> rules;

    public DetectionRuleEngine(List<DetectionRule> rules) {
        this.rules = rules;
    }

    public RuleEngineResult evaluate(Transaction transaction) {

        int totalScore = 0;
        String matchedRule = "NONE";

        for (DetectionRule rule : rules) {
            RuleEvaluationResult result = rule.evaluate(transaction);

            if (result.isTriggered()) {
                totalScore += result.getScore();
                matchedRule = result.getRuleName();
            }
        }

        return new RuleEngineResult(totalScore, matchedRule);
    }
}