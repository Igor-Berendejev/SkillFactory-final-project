package com.example.bank.service;

import com.example.bank.entity.Transaction;
import com.example.bank.repository.TransactionRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@AllArgsConstructor
@Service
public class TransactionService {

    private final TransactionRepository transactionRepository;

    private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");

    public List<Transaction> findTransactions(String balanceId, String startDate, String endDate) {
        Long id = Long.parseLong(balanceId);
        LocalDateTime start = LocalDate.parse(startDate, formatter).atStartOfDay();
        LocalDateTime end = LocalDate.parse(endDate, formatter).atTime(23, 59);

        return transactionRepository.findAllByBalanceIdAndDateBetweenOrderByDateAsc(id, start, end);
    }

}
