package com.example.bank.controller;

import com.example.bank.entity.Transaction;
import com.example.bank.service.TransactionService;
import com.example.bank.types.TransactionMethod;
import com.example.bank.types.TransactionType;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;
import java.util.List;

import static org.hamcrest.Matchers.is;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(controllers = TransactionController.class)
class TransactionControllerTest {

    @MockBean
    TransactionService transactionService;

    @Autowired
    MockMvc mockMvc;

    @Test
    public void shouldReturnTransactionsTest() throws Exception {
        Transaction transaction1 = new Transaction(1l,
                LocalDateTime.now(),
                TransactionType.WITHDRAW,
                BigDecimal.valueOf(123l),
                TransactionMethod.TRANSFER);
        Transaction transaction2 = new Transaction(1l,
                LocalDateTime.now(),
                TransactionType.PUT,
                BigDecimal.valueOf(321l),
                TransactionMethod.TRANSFER);
        Transaction transaction3 = new Transaction(1l,
                LocalDateTime.now(),
                TransactionType.PUT,
                BigDecimal.valueOf(666l),
                TransactionMethod.ATM);
        List<Transaction> expectedList = List.of(transaction1, transaction2, transaction3);

        when(transactionService.findTransactions(anyString(), anyString(), anyString()))
                .thenReturn(expectedList);

        mockMvc.perform(get("/v1/transactions/{balanceId}", 1))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.size()", is(3)))
                .andExpect(jsonPath("$[0].balanceId", is(1)))
                .andExpect(jsonPath("$[0].transactionType", is("WITHDRAW")))
                .andExpect(jsonPath("$[0].amount", is(123)))
                .andExpect(jsonPath("$[0].transactionMethod", is("TRANSFER")))
                .andExpect(jsonPath("$[2].balanceId", is(1)))
                .andExpect(jsonPath("$[2].transactionType", is("PUT")))
                .andExpect(jsonPath("$[2].amount", is(666)))
                .andExpect(jsonPath("$[2].transactionMethod", is("ATM")));
    }

    @Test
    public void shouldThrowExceptionTest() throws Exception {
        when(transactionService.findTransactions(anyString(), anyString(), anyString()))
                .thenThrow(DateTimeParseException.class);

        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        params.add("startDate", "2022.09.20");
        params.add("endDate", "2022.09.25");

        mockMvc.perform(get("/v1/transactions/{balanceId}", 1)
                        .params(params))
                .andExpect(status().isBadRequest())
                .andExpect(content().string("Date should be in format dd.mm.yyyy null"));
    }

}