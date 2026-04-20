package com.siqiuma.api_service.service;

import com.siqiuma.api_service.dto.TransactionFullViewResponse;
import com.siqiuma.api_service.model.Transaction;
import org.springframework.stereotype.Service;

@Service
public class TransactionFullViewService {

    private final TransactionQueryService transactionQueryService;
    private final AnomalyResultQueryService anomalyResultQueryService;
    private final ExplanationQueryService explanationQueryService;

    public TransactionFullViewService(TransactionQueryService transactionQueryService,
                                      AnomalyResultQueryService anomalyResultQueryService,
                                      ExplanationQueryService explanationQueryService) {
        this.transactionQueryService = transactionQueryService;
        this.anomalyResultQueryService = anomalyResultQueryService;
        this.explanationQueryService = explanationQueryService;
    }

    public TransactionFullViewResponse getFullView(String transactionId) {
        Transaction transaction = transactionQueryService.getByTransactionId(transactionId)
                .orElseThrow(() -> new RuntimeException("Transaction not found: " + transactionId));

        TransactionFullViewResponse response = new TransactionFullViewResponse();
        response.setTransaction(transaction);
        response.setAnomalyResults(anomalyResultQueryService.getByTransactionId(transactionId));
        response.setExplanations(explanationQueryService.getByTransactionId(transactionId));

        return response;
    }
}