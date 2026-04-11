package com.siqiuma.detection_service.rule;

import com.siqiuma.detection_service.model.Transaction;
import org.springframework.stereotype.Component;

import java.util.Set;

@Component
public class SuspiciousCountryRule implements DetectionRule {

    private static final Set<String> HIGH_RISK_COUNTRIES = Set.of("RU", "IR", "KP");

    @Override
    public String getName() {
        return "SUSPICIOUS_COUNTRY";
    }

    @Override
    public RuleEvaluationResult evaluate(Transaction transaction) {
        if (transaction.getCountry() != null &&
                HIGH_RISK_COUNTRIES.contains(transaction.getCountry().toUpperCase())) {
            return new RuleEvaluationResult(getName(), 30, true);
        }

        return new RuleEvaluationResult(getName(), 0, false);
    }
}