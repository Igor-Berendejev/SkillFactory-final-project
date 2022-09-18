package com.example.bank.repository;

import com.example.bank.entity.Transaction;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.jdbc.Sql;

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class TransactionRepositoryTest extends AbstractRepositoryTest {

    @Autowired
    TransactionRepository repository;

    private List<Transaction> transactions;

    @Test
    @Sql("classpath:transaction-test-data.sql")
    @DisplayName("Test that repository returns correct number of transactions for a period")
    void shouldFindTransactionsWithinPeriodTest() {
        List<Transaction> actual = repository.findAllByBalanceIdAndDateBetweenOrderByDateAsc(1l,
                LocalDateTime.of(1950, 2, 1, 0, 0),
                LocalDateTime.of(1950, 2, 15, 23, 59));

        assertEquals(4, actual.size());

        actual = repository.findAllByBalanceIdAndDateBetweenOrderByDateAsc(1l,
                LocalDateTime.of(1950, 2, 7, 0, 0),
                LocalDateTime.of(1950, 2, 20, 23, 59));

        assertEquals(2, actual.size());

        actual = repository.findAllByBalanceIdAndDateBetweenOrderByDateAsc(1l,
                LocalDateTime.of(1950, 2, 16, 0, 0),
                LocalDateTime.of(1950, 2, 20, 23, 59));

        assertTrue(actual.isEmpty());
    }

    @Test
    @Sql("classpath:transaction-test-data.sql")
    @DisplayName("Test that the repository returns empty list of transactions" +
            " if there are no transactions for the balance id")
    void shouldReturnEmptyListIfNoTransactionsForBalanceId() {
        List<Transaction> actual = repository.findAllByBalanceIdAndDateBetweenOrderByDateAsc(2l,
                LocalDateTime.of(1950, 2, 1, 0, 0),
                LocalDateTime.of(1950, 2, 15, 23, 59));

        assertTrue(actual.isEmpty());
    }
}
