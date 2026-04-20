package com.siqiuma.api_service.service;

import com.siqiuma.api_service.dto.PublishTransactionRequest;
import com.siqiuma.api_service.model.TransactionEvent;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class TransactionPublishService {

    private final KafkaTemplate<String, TransactionEvent> kafkaTemplate;

    public TransactionPublishService(KafkaTemplate<String, TransactionEvent> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void publish(PublishTransactionRequest request) {
        TransactionEvent event = new TransactionEvent();
        event.setTransactionId(request.getTransactionId());
        event.setAccountId(request.getAccountId());
        event.setUserId(request.getUserId());
        event.setMerchantId(request.getMerchantId());
        event.setDeviceId(request.getDeviceId());
        event.setAmount(request.getAmount());
        event.setCurrency(request.getCurrency());
        event.setCountry(request.getCountry());
        event.setPaymentMethod(request.getPaymentMethod());
        event.setTransactionType(request.getTransactionType());
        event.setEventTime(request.getEventTime());

        kafkaTemplate.send("transaction-events", event.getTransactionId(), event);
    }
}