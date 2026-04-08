package com.siqiuma.api_service.controller;

import com.siqiuma.api_service.model.Transaction;
import com.siqiuma.api_service.service.TransactionQueryService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/transactions")
public class TransactionController {

    private final TransactionQueryService transactionQueryService;

    public TransactionController(TransactionQueryService transactionQueryService) {
        this.transactionQueryService = transactionQueryService;
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
}