package com.siqiuma.explanation_service.service;

import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class TemplateExplanationGenerator {

    public String generate(List<String> matchedRules) {

        StringBuilder explanation = new StringBuilder();
        explanation.append("This transaction is evaluated based on the following factors:\n");

        if (matchedRules == null || matchedRules.isEmpty()) {
            explanation.append("- No suspicious patterns detected\n");
        } else {
            for (String rule : matchedRules) {
                switch (rule) {
                    case "LARGE_AMOUNT":
                        explanation.append("- The transaction amount exceeds the threshold (>= 1000)\n");
                        break;
                    case "SUSPICIOUS_COUNTRY":
                        explanation.append("- The transaction originates from a high-risk country\n");
                        break;
                    default:
                        explanation.append("- Triggered rule: ").append(rule).append("\n");
                        break;
                }
            }
        }

        return explanation.toString();
    }
}