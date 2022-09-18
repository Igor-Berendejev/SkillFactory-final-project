package com.example.bank.repository;

import com.example.bank.entity.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface TransactionRepository extends JpaRepository<Transaction, Long> {
    List<Transaction> findAllByBalanceIdAndDateBetweenOrderByDateAsc(Long balanceId, LocalDateTime startDate, LocalDateTime endDate);
}
