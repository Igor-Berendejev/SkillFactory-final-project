package com.example.bank.repository;

import com.example.bank.entity.Balance;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.jdbc.Sql;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DataJpaTest
@Testcontainers
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class BalanceRepositoryTest {

    @Container
    PostgreSQLContainer container = new PostgreSQLContainer("postgres:latest")
            .withDatabaseName("bank")
            .withUsername("postgres")
            .withPassword("postgres");

    @Autowired
    private BalanceRepository repository;

    @Test
    @Sql("classpath:test-data.sql")
    public void shouldSaveBalance(){
        Balance expectedBalance = new Balance(null, 123l, BigDecimal.valueOf(666d));
        Balance actualBalance = repository.save(expectedBalance);
        assertEquals(expectedBalance.getUserId(), actualBalance.getUserId());
        assertEquals(expectedBalance.getBalance(), actualBalance.getBalance());
    }

}