package com.siqiuma.explanation_service.client;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.siqiuma.explanation_service.config.LlmConfig;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.http.MediaType;
import reactor.core.publisher.Mono;


import java.time.Duration;
import java.util.List;
import java.util.HashMap;
import java.util.Map;


@Component
public class OpenAiLlmClient implements LlmClient {

    private final WebClient webClient;
    private final LlmConfig config;
    private final ObjectMapper objectMapper = new ObjectMapper();

    public OpenAiLlmClient(LlmConfig config) {
        this.config = config;

        this.webClient = WebClient.builder()
                .baseUrl("https://api.openai.com")
                .defaultHeader("Authorization", "Bearer " + config.getApiKey())
                .defaultHeader("Content-Type", "application/json")
                .build();
        System.out.println("LLM api key present? " + (config.getApiKey() != null && !config.getApiKey().isBlank()));
        System.out.println("LLM model: " + config.getModel());
        System.out.println("LLM timeoutMs: " + config.getTimeoutMs());
    }

    @Override
    public String generateExplanation(String transactionId, List<String> matchedRules) {
        String prompt = buildPrompt(transactionId, matchedRules);

        Map<String, Object> systemMsg = Map.of(
                "role", "system",
                "content", "You are a financial risk analyst. Explain suspicious transaction signals clearly and briefly."
        );

        Map<String, Object> userMsg = Map.of(
                "role", "user",
                "content", prompt
        );

        Map<String, Object> body = new HashMap<>();
        body.put("model", config.getModel());
        body.put("messages", List.of(systemMsg, userMsg));

        try {
            String response = webClient.post()
                    .uri("/v1/chat/completions")
                    .contentType(MediaType.APPLICATION_JSON)
                    .bodyValue(body)
                    .retrieve()
                    .onStatus(
                            status -> status.isError(),
                            clientResponse -> clientResponse.bodyToMono(String.class)
                                    .flatMap(errorBody -> Mono.error(
                                            new RuntimeException("OpenAI API error: " + errorBody)))
                    )
                    .bodyToMono(String.class)
                    .timeout(Duration.ofMillis(config.getTimeoutMs()))
                    .block();

            return extractContent(response);

        } catch (Exception e) {
            System.err.println("LLM generation failed: " + e.getMessage());
            e.printStackTrace();

            Throwable cause = e.getCause();
            while (cause != null) {
                System.err.println("Caused by: " + cause.getClass().getName() + " - " + cause.getMessage());
                cause = cause.getCause();
            }
            throw new RuntimeException("LLM call failed", e);
        }
    }

    private String buildPrompt(String transactionId, List<String> rules) {
        StringBuilder sb = new StringBuilder();
        sb.append("Transaction ID: ").append(transactionId).append("\n");
        sb.append("Triggered Rules:\n");

        if (rules == null || rules.isEmpty()) {
            sb.append("- NONE\n");
        } else {
            for (String rule : rules) {
                sb.append("- ").append(rule).append("\n");
            }
        }

        sb.append("\nExplain in 2-3 sentences why this transaction may be risky.");
        return sb.toString();
    }

    private String extractContent(String json) throws Exception {
        JsonNode root = objectMapper.readTree(json);
        return root.path("choices")
                .get(0)
                .path("message")
                .path("content")
                .asText();
    }

    @Override
    public String getModelName() {
        return config.getModel();
    }
}