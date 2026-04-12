package com.siqiuma.api_service.controller;

import com.siqiuma.api_service.model.AnomalyExplanation;
import com.siqiuma.api_service.service.ExplanationQueryService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/explanations")
public class ExplanationController {

    private final ExplanationQueryService service;

    public ExplanationController(ExplanationQueryService service) {
        this.service = service;
    }

    @GetMapping("/{transactionId}")
    public List<AnomalyExplanation> getByTransactionId(@PathVariable String transactionId) {
        return service.getByTransactionId(transactionId);
    }
}