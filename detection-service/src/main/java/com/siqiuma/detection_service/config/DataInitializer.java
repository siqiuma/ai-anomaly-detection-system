package com.siqiuma.detection_service.config;

import com.siqiuma.detection_service.model.Transaction;
import com.siqiuma.detection_service.repository.TransactionRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.time.Instant;

//@Component
public class DataInitializer implements CommandLineRunner {

    private final TransactionRepository transactionRepository;

    public DataInitializer(TransactionRepository transactionRepository) {
        this.transactionRepository = transactionRepository;
    }

    @Override
    public void run(String...args) {
        String transactionId = "txn_100001";

        if (transactionRepository.findByTransactionId(transactionId).isPresent()) {
            System.out.println("Test transaction already exists, skipping insert.");
            return;
        }

        Transaction transaction = new Transaction();
        transaction.setTransactionId(transactionId);
        transaction.setAccountId("acc_2001");
        transaction.setUserId("user_3001");
        transaction.setMerchantId("m_4001");
        transaction.setDeviceId("dev_5001");
        transaction.setAmount(new BigDecimal("1280.50"));
        transaction.setCurrency("USD");
        transaction.setCountry("US");
        transaction.setPaymentMethod("CREDIT_CARD");
        transaction.setTransactionType("PURCHASE");
        transaction.setEventTime(Instant.now());

        transactionRepository.save(transaction);

        System.out.println("Inserted test transaction: "+transactionId);
    }
}