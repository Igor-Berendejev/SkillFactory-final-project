package com.example.bank.service;

import com.example.bank.entity.Balance;
import com.example.bank.repository.BalanceRepository;
import com.example.bank.repository.TransactionRepository;
import com.example.bank.types.TransactionMethod;
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
    ArgumentCaptor<Long> balanceIdCaptor;
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
        when(balanceRepository.findById(anyLong())).thenReturn(Optional.of(expectedBalance));
        BigDecimal responseBalance = service.getUserBalance("123", "1");
        assertEquals(BigDecimal.valueOf(666.15d), responseBalance);
    }

    @Test
    @DisplayName("Test that IllegalArgumentException with message \"No such user\" is thrown when the user is not found")
    void throwsExceptionWhenNoUserFoundTest() {
        when(balanceRepository.findById(anyLong())).thenThrow(new IllegalArgumentException("No such user"));
        IllegalArgumentException expected = assertThrows(IllegalArgumentException.class, () -> service.getUserBalance("123", "1"));
        assertTrue(expected.getMessage().equals("No such user"));
    }

    @Test
    @DisplayName("Test that the amount is added to the user balance")
    void shouldAddBalanceOnUserTest() {
        Balance oldBalance = new Balance(1l, 123l, BigDecimal.valueOf(50));
        Balance newBalance = new Balance(1l, 123l, BigDecimal.valueOf(150));
        when(balanceRepository.findById(anyLong())).thenReturn(Optional.of(oldBalance));
        when(balanceRepository.save(newBalance)).thenReturn(newBalance);

        service.addBalance("1", new BigDecimal(100), TransactionMethod.ATM);

        verify(balanceRepository, Mockito.times(1)).findById(balanceIdCaptor.capture());
        verify(balanceRepository, Mockito.times(1)).save(balanceArgumentCaptor.capture());

        assertEquals(1l, balanceIdCaptor.getValue());
        assertEquals(newBalance.getUserId(), balanceArgumentCaptor.getValue().getUserId());
        assertEquals(newBalance.getBalance(), balanceArgumentCaptor.getValue().getBalance());
    }
}