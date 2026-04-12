package com.siqiuma.api_service.repository;

import com.siqiuma.api_service.model.AnomalyExplanation;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AnomalyExplanationRepository extends JpaRepository<AnomalyExplanation, Long> {
    List<AnomalyExplanation> findByTransactionId(String transactionId);
}