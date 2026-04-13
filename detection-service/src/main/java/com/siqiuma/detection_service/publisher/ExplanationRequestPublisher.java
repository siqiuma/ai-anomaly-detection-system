package com.siqiuma.detection_service.publisher;

import com.siqiuma.detection_service.model.ExplanationRequestEvent;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
public class ExplanationRequestPublisher {

    private final KafkaTemplate<String, ExplanationRequestEvent> kafkaTemplate;

    public ExplanationRequestPublisher(KafkaTemplate<String, ExplanationRequestEvent> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void publish(ExplanationRequestEvent event) {
        kafkaTemplate.send("explanation-requests", event.getTransactionId(), event);
    }
}