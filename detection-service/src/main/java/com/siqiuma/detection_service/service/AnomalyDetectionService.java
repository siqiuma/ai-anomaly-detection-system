package com.siqiuma.detection_service.service;

import com.siqiuma.detection_service.model.AnomalyResult;
import com.siqiuma.detection_service.model.Transaction;
import com.siqiuma.detection_service.repository.AnomalyResultRepository;
import com.siqiuma.detection_service.rule.DetectionRuleEngine;
import com.siqiuma.detection_service.rule.RuleEngineResult;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class AnomalyDetectionService {

    private final AnomalyResultRepository anomalyResultRepository;
    private final DetectionRuleEngine ruleEngine;
    private final ExplanationService explanationService;


    public AnomalyDetectionService(AnomalyResultRepository anomalyResultRepository, DetectionRuleEngine ruleEngine, ExplanationService explanationService) {
        this.anomalyResultRepository = anomalyResultRepository;
        this.ruleEngine = ruleEngine;
        this.explanationService = explanationService;
    }

    public void evaluateAndSave(Transaction transaction) {

        RuleEngineResult engineResult = ruleEngine.evaluate(transaction);
        List<String> matchedRulesList = engineResult.getMatchedRules();

        AnomalyResult result = new AnomalyResult();
        result.setTransactionId(transaction.getTransactionId());
        result.setAnomalyScore(engineResult.getTotalScore());

        int score = engineResult.getTotalScore();

        if (score >= 80) {
            result.setRiskLevel("HIGH");
            result.setFlagged(true);
        } else if (score > 0) {
            result.setRiskLevel("MEDIUM");
            result.setFlagged(true);
        } else {
            result.setRiskLevel("LOW");
            result.setFlagged(false);
        }

        String matchedRules = matchedRulesList.isEmpty()
                ? "NONE"
                : String.join(",", matchedRulesList);

        result.setMatchedRules(matchedRules);

        anomalyResultRepository.save(result);

        explanationService.generateAndSave(transaction, matchedRulesList);
    }
}