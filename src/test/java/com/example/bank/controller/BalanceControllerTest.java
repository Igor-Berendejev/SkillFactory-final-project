package com.example.bank.controller;

import com.example.bank.service.BalanceService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;

import static org.hamcrest.Matchers.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = BalanceController.class)
public class BalanceControllerTest {

    @MockBean
    private BalanceService balanceService;

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void shouldReturnUserBalanceTest() throws Exception {
        BigDecimal balance = new BigDecimal(600);
        when(balanceService.getUserBalance(any(String.class), any(String.class)))
                .thenReturn(balance);

        mockMvc.perform(get("/v1/balance/1/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", is((balance.intValue()))));
    }

}
