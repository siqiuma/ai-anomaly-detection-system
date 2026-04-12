package com.siqiuma.detection_service.service;

import com.siqiuma.detection_service.model.AnomalyExplanation;
import com.siqiuma.detection_service.model.Transaction;
import com.siqiuma.detection_service.repository.AnomalyExplanationRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ExplanationService {

    private final AnomalyExplanationRepository repository;

    public ExplanationService(AnomalyExplanationRepository repository) {
        this.repository = repository;
    }

    public void generateAndSave(Transaction transaction, List<String> matchedRules) {

        StringBuilder explanation = new StringBuilder();

        explanation.append("This transaction is evaluated based on the following factors:\n");

        if (matchedRules == null || matchedRules.isEmpty()) {
            explanation.append("- No suspicious patterns detected\n");
        } else {
            for (String rule : matchedRules) {
                switch (rule) {
                    case "LARGE_AMOUNT":
                        explanation.append("- The transaction amount exceeds the threshold (>= 1000)\n");
                        break;
                    case "SUSPICIOUS_COUNTRY":
                        explanation.append("- The transaction originates from a high-risk country\n");
                        break;
                    default:
                        break;
                }
            }
        }

        AnomalyExplanation entity = new AnomalyExplanation();
        entity.setTransactionId(transaction.getTransactionId());
        entity.setExplanationText(explanation.toString());

        repository.save(entity);
    }
}