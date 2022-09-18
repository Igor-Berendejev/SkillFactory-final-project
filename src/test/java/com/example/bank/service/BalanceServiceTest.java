package com.example.bank.service;

import com.example.bank.entity.Balance;
import com.example.bank.repository.BalanceRepository;
import com.example.bank.repository.TransactionRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class BalanceServiceTest {

    @Mock
    private BalanceRepository balanceRepository;

    @Mock
    TransactionRepository transactionRepository;

    @Captor
    ArgumentCaptor<Long> userIdCaptor;
    @Captor
    ArgumentCaptor<Balance> balanceArgumentCaptor;

    BalanceService service;

    @BeforeEach
    public void setUpBalanceService() {
        service = new BalanceService(balanceRepository, transactionRepository);
    }

    @Test
    @DisplayName("The test to make sure balance is returned by user ID")
    void returnsBalanceByUserIdTest() {
        Balance expectedBalance = new Balance(1l, 123l, BigDecimal.valueOf(666.15d));
        when(balanceRepository.getBalanceByUserId(anyLong())).thenReturn(Optional.of(expectedBalance));
        BigDecimal responseBalance = service.getUserBalance("123");
        assertEquals(BigDecimal.valueOf(666.15d), responseBalance);
    }

    @Test
    @DisplayName("Test that IllegalArgumentException with message \"No such user\" is thrown when the user is not found")
    void throwsExceptionWhenNoUserFoundTest() {
        when(balanceRepository.getBalanceByUserId(anyLong())).thenThrow(new IllegalArgumentException("No such user"));
        IllegalArgumentException expected = assertThrows(IllegalArgumentException.class, () -> service.getUserBalance("123"));
        assertTrue(expected.getMessage().equals("No such user"));
    }

    @Test
    @DisplayName("Test that the amount is added to the user balance")
    void shouldAddBalanceOnUserTest() {
        Balance oldBalance = new Balance(1l, 123l, BigDecimal.valueOf(50));
        Balance newBalance = new Balance(1l, 123l, BigDecimal.valueOf(150));
        when(balanceRepository.getBalanceByUserId(anyLong())).thenReturn(Optional.of(oldBalance));
        when(balanceRepository.save(newBalance)).thenReturn(newBalance);

        service.addBalance("123", new BigDecimal(100));

        verify(balanceRepository, Mockito.times(1)).getBalanceByUserId(userIdCaptor.capture());
        verify(balanceRepository, Mockito.times(1)).save(balanceArgumentCaptor.capture());

        assertEquals(123l, userIdCaptor.getValue());
        assertEquals(newBalance.getUserId(), balanceArgumentCaptor.getValue().getUserId());
        assertEquals(newBalance.getBalance(), balanceArgumentCaptor.getValue().getBalance());
    }
}