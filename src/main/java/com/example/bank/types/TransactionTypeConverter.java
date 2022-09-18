package com.example.bank.types;

import javax.persistence.AttributeConverter;

public class TransactionTypeConverter implements AttributeConverter<TransactionType, Integer> {
    @Override
    public Integer convertToDatabaseColumn(TransactionType transactionType) {
        if (transactionType == null) return null;
        switch (transactionType) {
            case PUT -> {
                return 1;
            }
            case WITHDRAW -> {
                return 0;
            }
            default -> {
                return null;
            }
        }
    }

    @Override
    public TransactionType convertToEntityAttribute(Integer i) {
        if (i == null) return null;
        switch (i) {
            case 0 -> {
                return TransactionType.WITHDRAW;
            }
            case 1 -> {
                return TransactionType.PUT;
            }
            default -> throw new IllegalArgumentException("Illegal code for TransactionType");
        }
    }
}
