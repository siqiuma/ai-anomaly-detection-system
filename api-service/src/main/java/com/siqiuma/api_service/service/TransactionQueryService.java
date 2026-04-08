package com.siqiuma.api_service.service;

import com.siqiuma.api_service.model.Transaction;
import com.siqiuma.api_service.repository.TransactionRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TransactionQueryService {

    private final TransactionRepository transactionRepository;

    public TransactionQueryService(TransactionRepository transactionRepository) {
        this.transactionRepository = transactionRepository;
    }

    public List<Transaction> getAllTransactions() {
        return transactionRepository.findAll();
    }

    public Optional<Transaction> getByTransactionId(String transactionId) {
        return transactionRepository.findByTransactionId(transactionId);
    }
}