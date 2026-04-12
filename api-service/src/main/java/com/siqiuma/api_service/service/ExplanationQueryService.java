package com.siqiuma.api_service.service;

import com.siqiuma.api_service.model.AnomalyExplanation;
import com.siqiuma.api_service.repository.AnomalyExplanationRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ExplanationQueryService {

    private final AnomalyExplanationRepository anomalyExplanationRepository;

    public ExplanationQueryService(AnomalyExplanationRepository anomalyExplanationRepository) {
        this.anomalyExplanationRepository = anomalyExplanationRepository;
    }

    public List<AnomalyExplanation> getAllResults() {
        return anomalyExplanationRepository.findAll();
    }

    public List<AnomalyExplanation> getByTransactionId(String transactionId) {
        return anomalyExplanationRepository.findByTransactionId(transactionId);
    }
}