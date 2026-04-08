package com.siqiuma.api_service.repository;

import com.siqiuma.api_service.model.AnomalyResult;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AnomalyResultRepository extends JpaRepository<AnomalyResult, Long> {
    List<AnomalyResult> findByTransactionId(String transactionId);
}