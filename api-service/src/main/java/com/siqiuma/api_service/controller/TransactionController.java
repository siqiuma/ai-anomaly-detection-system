package com.siqiuma.api_service.controller;

import com.siqiuma.api_service.model.Transaction;
import com.siqiuma.api_service.service.TransactionQueryService;
import com.siqiuma.api_service.dto.TransactionFullViewResponse;
import com.siqiuma.api_service.service.TransactionFullViewService;
import com.siqiuma.api_service.dto.PublishTransactionRequest;
import com.siqiuma.api_service.service.TransactionPublishService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/transactions")
public class TransactionController {

    private final TransactionQueryService transactionQueryService;
    private final TransactionFullViewService transactionFullViewService;
    private final TransactionPublishService transactionPublishService;

    public TransactionController(TransactionQueryService transactionQueryService,
                                 TransactionFullViewService transactionFullViewService,
                                 TransactionPublishService transactionPublishService) {
        this.transactionQueryService = transactionQueryService;
        this.transactionFullViewService = transactionFullViewService;
        this.transactionPublishService = transactionPublishService;
    }

    @GetMapping
    public List<Transaction> getAllTransactions() {
        return transactionQueryService.getAllTransactions();
    }

    @GetMapping("/{transactionId}")
    public ResponseEntity<Transaction> getByTransactionId(@PathVariable String transactionId) {
        return transactionQueryService.getByTransactionId(transactionId)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/{transactionId}/full-view")
    public ResponseEntity<TransactionFullViewResponse> getFullView(@PathVariable String transactionId) {
        try {
            return ResponseEntity.ok(transactionFullViewService.getFullView(transactionId));
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/publish")
    public ResponseEntity<String> publishTransaction(@RequestBody PublishTransactionRequest request) {
        transactionPublishService.publish(request);
        return ResponseEntity.ok("Transaction published: " + request.getTransactionId());
    }
}