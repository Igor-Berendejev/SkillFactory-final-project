package com.example.bank.service;

import com.example.bank.entity.Balance;
import com.example.bank.entity.BalanceDto;
import com.example.bank.entity.BalanceToDtoMapper;
import com.example.bank.entity.Transaction;
import com.example.bank.repository.BalanceRepository;
import com.example.bank.repository.TransactionRepository;
import com.example.bank.types.TransactionMethod;
import com.example.bank.types.TransactionType;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Slf4j
@Service
@AllArgsConstructor
public class BalanceService {

    private final BalanceRepository balanceRepository;

    private final TransactionRepository transactionRepository;

    public BigDecimal getUserBalance(String userId, String balanceId) {
        Balance balance = balanceRepository.findById(Long.parseLong(balanceId))
                .orElseThrow(() -> new IllegalArgumentException("No such balance"));
        if (!balance.getUserId().equals(Long.parseLong(userId)))
            throw new IllegalArgumentException("Balance " + balanceId + " does not belong to user " + userId);
        return balance.getBalance();
    }

    @Transactional
    public BalanceDto addBalance(String balanceId,
                                 BigDecimal amount,
                                 TransactionMethod transactionMethod) {
        Balance balance = balanceRepository.findById(Long.parseLong(balanceId))
                .orElseThrow(() -> new IllegalArgumentException("No such balance"));
        transactionRepository.save(new Transaction(balance.getId(),
                LocalDateTime.now(),
                TransactionType.PUT,
                amount,
                transactionMethod));
        return BalanceToDtoMapper.balanceToDto(balanceRepository.save(new Balance(balance.getId(),
                balance.getUserId(),
                balance.getBalance().add(amount))));
    }

    @Transactional
    public BalanceDto withdraw(String balanceId,
                               BigDecimal amount,
                               TransactionMethod transactionMethod) {
        Balance balance = balanceRepository.findById(Long.parseLong(balanceId))
                .orElseThrow(() -> new IllegalArgumentException("No such balance"));
        if (amount.compareTo(balance.getBalance()) > 0) throw new IllegalArgumentException("Not enough funds");
        transactionRepository.save(new Transaction(balance.getId(),
                LocalDateTime.now(),
                TransactionType.WITHDRAW,
                amount,
                transactionMethod));
        return BalanceToDtoMapper.balanceToDto(balanceRepository.save(new Balance(balance.getId(),
                balance.getUserId(),
                balance.getBalance().subtract(amount))));
    }

    private Balance getBalanceByUserId(String userId) {
        Long id = Long.parseLong(userId);
        return balanceRepository.getBalanceByUserId(id).orElseThrow(() -> new IllegalArgumentException("No such user"));
    }
}
