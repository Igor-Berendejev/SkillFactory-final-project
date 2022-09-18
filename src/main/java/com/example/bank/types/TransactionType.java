package com.example.bank.types;

public enum TransactionType {
    WITHDRAW(0),
    PUT(1);

    private int code;

    TransactionType(int code) {
        this.code = code;
    }

    public int getCode() {
        return code;
    }
}
