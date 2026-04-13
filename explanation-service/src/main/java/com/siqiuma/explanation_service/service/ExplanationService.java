package com.siqiuma.explanation_service.service;

import com.siqiuma.explanation_service.client.LlmClient;
import com.siqiuma.explanation_service.model.AnomalyExplanation;
import com.siqiuma.explanation_service.repository.AnomalyExplanationRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ExplanationService {

    private final AnomalyExplanationRepository repository;
    private final LlmClient llmClient;
    private final TemplateExplanationGenerator templateExplanationGenerator;

    public ExplanationService(AnomalyExplanationRepository repository,
                              LlmClient llmClient,
                              TemplateExplanationGenerator templateExplanationGenerator) {
        this.repository = repository;
        this.llmClient = llmClient;
        this.templateExplanationGenerator = templateExplanationGenerator;
    }

    public void generateAndSave(String transactionId, List<String> matchedRules) {

        String explanationText;
        String generationMethod;
        String modelName;

        try {
            explanationText = llmClient.generateExplanation(transactionId, matchedRules);
            generationMethod = "LLM";
            modelName = llmClient.getModelName();
        } catch (Exception e) {
            explanationText = templateExplanationGenerator.generate(matchedRules);
            generationMethod = "TEMPLATE";
            modelName = "fallback-template";
        }

        AnomalyExplanation entity = new AnomalyExplanation();
        entity.setTransactionId(transactionId);
        entity.setExplanationText(explanationText);
        entity.setGenerationMethod(generationMethod);
        entity.setModelName(modelName);

        repository.save(entity);
    }
}