package com.siqiuma.detection_service.rule;

import com.siqiuma.detection_service.model.Transaction;

public interface DetectionRule {

    String getName();

    RuleEvaluationResult evaluate(Transaction transaction);
}