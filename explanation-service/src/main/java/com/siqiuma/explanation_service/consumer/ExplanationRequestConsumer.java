package com.siqiuma.explanation_service.consumer;

import com.siqiuma.explanation_service.model.ExplanationRequestEvent;
import com.siqiuma.explanation_service.service.ExplanationService;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class ExplanationRequestConsumer {

    private final ExplanationService explanationService;

    public ExplanationRequestConsumer(ExplanationService explanationService) {
        this.explanationService = explanationService;
    }

    @KafkaListener(topics = "explanation-requests", groupId = "explanation-group")
    public void consume(ExplanationRequestEvent event) {
        explanationService.generateAndSave(event.getTransactionId(), event.getMatchedRules());
    }
}