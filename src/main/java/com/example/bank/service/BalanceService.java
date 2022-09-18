package com.example.bank.service;

import com.example.bank.entity.Balance;
import com.example.bank.entity.BalanceDto;
import com.example.bank.entity.BalanceToDtoMapper;
import com.example.bank.entity.Transaction;
import com.example.bank.repository.BalanceRepository;
import com.example.bank.repository.TransactionRepository;
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

    public BigDecimal getUserBalance(String userId) {
        Balance userBalance = getBalanceByUserId(userId);
        return userBalance.getBalance();
    }

    @Transactional
    public BalanceDto addBalance(String userId, BigDecimal amount) {
        Balance userBalance = getBalanceByUserId(userId);
        transactionRepository.save(new Transaction(userBalance.getId(),
                LocalDateTime.now(),
                TransactionType.PUT,
                amount));
        return BalanceToDtoMapper.balanceToDto(balanceRepository.save(new Balance(userBalance.getId(),
                userBalance.getUserId(),
                userBalance.getBalance().add(amount))));
    }

    @Transactional
    public BalanceDto withdraw(String userId, BigDecimal amount) {
        Balance userBalance = getBalanceByUserId(userId);
        if (amount.compareTo(userBalance.getBalance()) > 0) throw new IllegalArgumentException("Not enough funds");
        transactionRepository.save(new Transaction(userBalance.getId(),
                LocalDateTime.now(),
                TransactionType.WITHDRAW,
                amount));
        return BalanceToDtoMapper.balanceToDto(balanceRepository.save(new Balance(userBalance.getId(),
                userBalance.getUserId(),
                userBalance.getBalance().subtract(amount))));
    }

    private Balance getBalanceByUserId(String userId) {
        Long id = Long.parseLong(userId);
        return balanceRepository.getBalanceByUserId(id).orElseThrow(() -> new IllegalArgumentException("No such user"));
    }
}
