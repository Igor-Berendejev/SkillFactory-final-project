package com.example.bank.service;

import com.example.bank.entity.Balance;
import com.example.bank.entity.BalanceDto;
import com.example.bank.entity.BalanceToDtoMapper;
import com.example.bank.repository.BalanceRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.joda.time.DateTime;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Slf4j
@Service
@AllArgsConstructor
public class BalanceService {

    private final BalanceRepository balanceRepository;

    public BigDecimal getUserBalance(String userId) {
        Balance userBalance = getBalanceByUserId(userId);
        return userBalance.getBalance();
    }

    public BalanceDto addBalance(String userId, BigDecimal amount) {
        Balance userBalance = getBalanceByUserId(userId);
        return BalanceToDtoMapper.balanceToDto(balanceRepository.save(new Balance(userBalance.getId(),
                userBalance.getUserId(),
                userBalance.getBalance().add(amount))));
    }

    public BalanceDto withdraw(String userId, BigDecimal amount) {
        Balance userBalance = getBalanceByUserId(userId);
        if (amount.compareTo(userBalance.getBalance()) > 0) throw new IllegalArgumentException("Not enough funds");
        return BalanceToDtoMapper.balanceToDto(balanceRepository.save(new Balance(userBalance.getId(),
                userBalance.getUserId(),
                userBalance.getBalance().subtract(amount))));
    }

    private Balance getBalanceByUserId(String userId) {
        Long id = Long.parseLong(userId);
        return balanceRepository.getBalanceByUserId(id).orElseThrow(() -> new IllegalArgumentException("No such user"));
    }
}
