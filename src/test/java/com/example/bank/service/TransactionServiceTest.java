package com.example.bank.service;

import com.example.bank.entity.Transaction;
import com.example.bank.repository.TransactionRepository;
import com.example.bank.types.TransactionType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;

@ExtendWith(MockitoExtension.class)
public class TransactionServiceTest {

    @Mock
    TransactionRepository transactionRepository;

    private TransactionService transactionService;

    private List<Transaction> transactions;

    @BeforeEach
    void setupService() {
        transactionService = new TransactionService(transactionRepository);
    }

    @BeforeEach
    void prepareTransactions() {
        transactions = new ArrayList<>();
        Transaction transaction1 = new Transaction(1l, LocalDateTime.of(2022, 2, 1, 10, 0), TransactionType.PUT, new BigDecimal(50l));
        Transaction transaction2 = new Transaction(2l, LocalDateTime.of(2022, 2, 5, 10, 0), TransactionType.WITHDRAW, new BigDecimal(50l));
        Transaction transaction3 = new Transaction(3l, LocalDateTime.of(2022, 2, 10, 10, 0), TransactionType.PUT, new BigDecimal(50l));
        Transaction transaction4 = new Transaction(4l, LocalDateTime.of(2022, 2, 15, 10, 0), TransactionType.WITHDRAW, new BigDecimal(50l));

        transactions.add(transaction1);
        transactions.add(transaction2);
        transactions.add(transaction3);
        transactions.add(transaction4);
    }

    @Test
    @DisplayName("Test that the service returns correct number of transactions")
    void shouldReturnCorrectTransactionsTest() {
        Mockito.when(transactionRepository.findAllByBalanceIdAndDateBetweenOrderByDateAsc(Mockito.anyLong(),
                        Mockito.any(LocalDateTime.class),
                        Mockito.any(LocalDateTime.class)))
                .thenReturn(transactions);

        List<Transaction> actual = transactionService.findTransactions("1", "15.09.2022", "19.09.2022");
        assertEquals(4, actual.size());
    }

    @Test
    @DisplayName("Test that DateTimeParseException is thrown when the date is given in the wrong format")
    void shouldThrowDateTimeParseExceptionTest(){
        Mockito.lenient().when(transactionRepository.findAllByBalanceIdAndDateBetweenOrderByDateAsc(Mockito.anyLong(),
                Mockito.any(LocalDateTime.class),
                Mockito.any(LocalDateTime.class))).thenThrow(DateTimeParseException.class);

        assertThrows(DateTimeParseException.class, () -> transactionService.findTransactions("1", "2022.15.09", "2022.19.09"));
    }
}
