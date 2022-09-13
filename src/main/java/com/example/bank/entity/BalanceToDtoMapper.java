package com.example.bank.entity;

import java.math.BigDecimal;

public class BalanceToDtoMapper {
    public static BalanceDto balanceToDto(Balance balance) {
        Long userId = balance.getUserId();
        BigDecimal amount  = balance.getBalance();
        return new BalanceDto(userId, amount);
    }
}
