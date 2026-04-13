package com.siqiuma.detection_service.service;

import com.siqiuma.detection_service.model.AnomalyResult;
import com.siqiuma.detection_service.model.ExplanationRequestEvent;
import com.siqiuma.detection_service.model.Transaction;
import com.siqiuma.detection_service.publisher.ExplanationRequestPublisher;
import com.siqiuma.detection_service.repository.AnomalyResultRepository;
import com.siqiuma.detection_service.rule.DetectionRuleEngine;
import com.siqiuma.detection_service.rule.RuleEngineResult;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class AnomalyDetectionService {

    private final AnomalyResultRepository anomalyResultRepository;
    private final DetectionRuleEngine ruleEngine;
    private final ExplanationRequestPublisher explanationRequestPublisher;


    public AnomalyDetectionService(AnomalyResultRepository anomalyResultRepository, DetectionRuleEngine ruleEngine, ExplanationRequestPublisher explanationRequestPublisher) {
        this.anomalyResultRepository = anomalyResultRepository;
        this.ruleEngine = ruleEngine;
        this.explanationRequestPublisher = explanationRequestPublisher;
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

        ExplanationRequestEvent event = new ExplanationRequestEvent();
        event.setTransactionId(transaction.getTransactionId());
        event.setMatchedRules(matchedRulesList);

        explanationRequestPublisher.publish(event);    }
}