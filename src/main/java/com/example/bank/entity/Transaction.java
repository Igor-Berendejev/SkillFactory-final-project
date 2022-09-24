package com.example.bank.entity;

import com.example.bank.types.TransactionMethod;
import com.example.bank.types.TransactionType;
import com.example.bank.types.TransactionTypeConverter;
import com.sun.istack.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
@Entity
@Table(name = "TRANSACTIONS")
public class Transaction {

    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "BALANCE_ID")
    private Long balanceId;

    @Column(name = "T_DATE")
    @NotNull
    private LocalDateTime date;

    @Column(name = "T_TYPE")
    @NotNull
    @Convert(converter = TransactionTypeConverter.class)
    private TransactionType transactionType;

    @Column(name = "AMOUNT")
    @NotNull
    private BigDecimal amount;

    @Column(name = "T_METHOD")
    @NotNull
    @Enumerated(EnumType.STRING)
    private TransactionMethod transactionMethod;

    public Transaction(Long balanceId, LocalDateTime date, TransactionType transactionType, BigDecimal amount, TransactionMethod transactionMethod) {
        this.balanceId = balanceId;
        this.date = date;
        this.transactionType = transactionType;
        this.amount = amount;
        this.transactionMethod = transactionMethod;
    }

    @Override
    public String toString() {
        return "amount " + amount +
                " " + transactionType +
                " on " + date;
    }
}
