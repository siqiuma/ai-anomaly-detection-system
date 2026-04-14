package com.siqiuma.explanation_service.client;

import org.springframework.stereotype.Component;

import java.util.List;
import java.util.StringJoiner;

//@Component
public class MockLlmClient implements LlmClient {

    @Override
    public String generateExplanation(String transactionId, List<String> matchedRules) {

        if (matchedRules == null || matchedRules.isEmpty()) {
            return "AI summary: No suspicious patterns were identified for transaction " + transactionId + ".";
        }

        StringJoiner joiner = new StringJoiner(", ");
        for (String rule : matchedRules) {
            joiner.add(rule);
        }

        return "AI summary: Transaction " + transactionId
                + " was flagged due to the following suspicious indicators: "
                + joiner + ".";
    }

    @Override
    public String getModelName() {
        return "mock-llm-v1";
    }
}