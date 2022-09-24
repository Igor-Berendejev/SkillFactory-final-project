package com.example.bank.service;

import com.example.bank.types.TransactionMethod;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;

@Service
@AllArgsConstructor
public class TransferService {

    private final BalanceService balanceService;

    @Transactional
    public void makeTransfer(String balanceIdFrom, String balanceIdTo, BigDecimal amount) {
        balanceService.withdraw(balanceIdFrom, amount, TransactionMethod.TRANSFER);
        balanceService.addBalance(balanceIdTo, amount, TransactionMethod.TRANSFER);
    }
}
