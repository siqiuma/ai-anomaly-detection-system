package com.siqiuma.api_service.service;

import com.siqiuma.api_service.model.AnomalyResult;
import com.siqiuma.api_service.repository.AnomalyResultRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AnomalyResultQueryService {

    private final AnomalyResultRepository anomalyResultRepository;

    public AnomalyResultQueryService(AnomalyResultRepository anomalyResultRepository) {
        this.anomalyResultRepository = anomalyResultRepository;
    }

    public List<AnomalyResult> getAllResults() {
        return anomalyResultRepository.findAll();
    }

    public List<AnomalyResult> getByTransactionId(String transactionId) {
        return anomalyResultRepository.findByTransactionId(transactionId);
    }
}