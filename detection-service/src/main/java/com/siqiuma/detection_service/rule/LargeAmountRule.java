package com.siqiuma.detection_service.rule;

import com.siqiuma.detection_service.model.Transaction;
import org.springframework.stereotype.Component;

@Component
public class LargeAmountRule implements DetectionRule {

    @Override
    public String getName() {
        return "LARGE_AMOUNT";
    }

    @Override
    public RuleEvaluationResult evaluate(Transaction transaction) {

        if (transaction.getAmount().doubleValue() >= 1000.0) {
            return new RuleEvaluationResult(getName(), 80, true);
        }

        return new RuleEvaluationResult(getName(), 0, false);
    }
}