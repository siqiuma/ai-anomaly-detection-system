package com.siqiuma.api_service.model;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.Instant;

@Entity
@Table(name = "transactions")
public class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "transaction_id", nullable = false, unique = true)
    private String transactionId;

    @Column(name = "account_id", nullable = false)
    private String accountId;

    @Column(name = "user_id")
    private String userId;

    @Column(name = "merchant_id")
    private String merchantId;

    @Column(name = "device_id")
    private String deviceId;

    @Column(nullable = false)
    private BigDecimal amount;

    @Column(nullable = false)
    private String currency;

    private String country;

    @Column(name = "payment_method")
    private String paymentMethod;

    @Column(name = "transaction_type")
    private String transactionType;

    @Column(name = "event_time", nullable = false)
    private Instant eventTime;

    @Column(name = "created_at", nullable = false)
    private Instant createdAt;

    public Long getId() {
        return id;
    }

    public String getTransactionId() {
        return transactionId;
    }

    public String getAccountId() {
        return accountId;
    }

    public String getUserId() {
        return userId;
    }

    public String getMerchantId() {
        return merchantId;
    }

    public String getDeviceId() {
        return deviceId;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public String getCurrency() {
        return currency;
    }

    public String getCountry() {
        return country;
    }

    public String getPaymentMethod() {
        return paymentMethod;
    }

    public String getTransactionType() {
        return transactionType;
    }

    public Instant getEventTime() {
        return eventTime;
    }

    public Instant getCreatedAt() {
        return createdAt;
    }
}