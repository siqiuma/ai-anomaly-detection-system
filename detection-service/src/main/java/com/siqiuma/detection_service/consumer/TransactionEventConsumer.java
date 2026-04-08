package com.siqiuma.detection_service.consumer;

import com.siqiuma.detection_service.model.TransactionEvent;
import com.siqiuma.detection_service.service.TransactionIngestionService;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class TransactionEventConsumer {

    private final TransactionIngestionService transactionIngestionService;

    public TransactionEventConsumer(TransactionIngestionService transactionIngestionService) {
        this.transactionIngestionService = transactionIngestionService;
    }

    @KafkaListener(topics = "transaction-events", groupId = "detection-group")
    public void consume(TransactionEvent event) {
        System.out.println("Received transaction event: " + event.getTransactionId());
        transactionIngestionService.ingest(event);
    }
}