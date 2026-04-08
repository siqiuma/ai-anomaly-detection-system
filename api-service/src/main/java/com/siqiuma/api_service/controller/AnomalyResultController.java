package com.siqiuma.api_service.controller;

import com.siqiuma.api_service.model.AnomalyResult;
import com.siqiuma.api_service.service.AnomalyResultQueryService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/anomaly-results")
public class AnomalyResultController {

    private final AnomalyResultQueryService anomalyResultQueryService;

    public AnomalyResultController(AnomalyResultQueryService anomalyResultQueryService) {
        this.anomalyResultQueryService = anomalyResultQueryService;
    }

    @GetMapping
    public List<AnomalyResult> getAllResults() {
        return anomalyResultQueryService.getAllResults();
    }

    @GetMapping("/{transactionId}")
    public List<AnomalyResult> getByTransactionId(@PathVariable String transactionId) {
        return anomalyResultQueryService.getByTransactionId(transactionId);
    }
}