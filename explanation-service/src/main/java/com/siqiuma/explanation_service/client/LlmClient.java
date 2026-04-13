package com.siqiuma.explanation_service.client;

import java.util.List;

public interface LlmClient {

    String generateExplanation(String transactionId, List<String> matchedRules);

    String getModelName();
}