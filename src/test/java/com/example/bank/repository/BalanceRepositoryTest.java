package com.example.bank.repository;

import com.example.bank.entity.Balance;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.jdbc.Sql;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class BalanceRepositoryTest extends AbstractRepositoryTest{

    @Autowired
    private BalanceRepository repository;

    @Test
    @Sql("classpath:balance-test-data.sql")
    public void shouldSaveBalance(){
        Balance expectedBalance = new Balance(null, 123l, BigDecimal.valueOf(666d));
        Balance actualBalance = repository.save(expectedBalance);
        assertEquals(expectedBalance.getUserId(), actualBalance.getUserId());
        assertEquals(expectedBalance.getBalance(), actualBalance.getBalance());
    }

}