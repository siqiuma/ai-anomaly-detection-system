package com.siqiuma.detection_service.service;

import com.siqiuma.detection_service.model.Transaction;
import com.siqiuma.detection_service.model.TransactionEvent;
import com.siqiuma.detection_service.repository.TransactionRepository;
import org.springframework.stereotype.Service;

@Service
public class TransactionIngestionService {

    private final TransactionRepository transactionRepository;
    private final AnomalyDetectionService anomalyDetectionService;

    public TransactionIngestionService(TransactionRepository transactionRepository, AnomalyDetectionService anomalyDetectionService) {
        this.transactionRepository = transactionRepository;
        this.anomalyDetectionService = anomalyDetectionService;
    }

    public void ingest(TransactionEvent event) {
        if (transactionRepository.findByTransactionId(event.getTransactionId()).isPresent()) {
            System.out.println("Transaction already exists, skipping: " + event.getTransactionId());
            return;
        }

        Transaction transaction = new Transaction();
        transaction.setTransactionId(event.getTransactionId());
        transaction.setAccountId(event.getAccountId());
        transaction.setUserId(event.getUserId());
        transaction.setMerchantId(event.getMerchantId());
        transaction.setDeviceId(event.getDeviceId());
        transaction.setAmount(event.getAmount());
        transaction.setCurrency(event.getCurrency());
        transaction.setCountry(event.getCountry());
        transaction.setPaymentMethod(event.getPaymentMethod());
        transaction.setTransactionType(event.getTransactionType());
        transaction.setEventTime(event.getEventTime());

        Transaction savedTransaction = transactionRepository.save(transaction);

        System.out.println("Saved transaction from Kafka: " + event.getTransactionId());

        anomalyDetectionService.evaluateAndSave(savedTransaction);

        System.out.println("Saved anomaly result for transaction: " + event.getTransactionId());

    }
}