package com.siqiuma.detection_service.service;

import com.siqiuma.detection_service.model.AnomalyResult;
import com.siqiuma.detection_service.model.Transaction;
import com.siqiuma.detection_service.repository.AnomalyResultRepository;
import com.siqiuma.detection_service.rule.DetectionRuleEngine;
import com.siqiuma.detection_service.rule.RuleEngineResult;
import org.springframework.stereotype.Service;

@Service
public class AnomalyDetectionService {

    private final AnomalyResultRepository anomalyResultRepository;
    private final DetectionRuleEngine ruleEngine;

    public AnomalyDetectionService(AnomalyResultRepository anomalyResultRepository, DetectionRuleEngine ruleEngine) {
        this.anomalyResultRepository = anomalyResultRepository;
        this.ruleEngine = ruleEngine;
    }

    public void evaluateAndSave(Transaction transaction) {

        RuleEngineResult engineResult = ruleEngine.evaluate(transaction);

        AnomalyResult result = new AnomalyResult();
        result.setTransactionId(transaction.getTransactionId());
        result.setAnomalyScore(engineResult.getTotalScore());

        if (engineResult.getTotalScore() >= 80) {
            result.setRiskLevel("HIGH");
            result.setFlagged(true);
        } else {
            result.setRiskLevel("LOW");
            result.setFlagged(false);
        }

        result.setMatchedRule(engineResult.getMatchedRule());

        anomalyResultRepository.save(result);
    }
}