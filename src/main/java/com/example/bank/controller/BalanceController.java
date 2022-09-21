package com.example.bank.controller;

import com.example.bank.entity.BalanceDto;
import com.example.bank.service.BalanceService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;

@RequiredArgsConstructor
@RestController
@RequestMapping("/v1/balance")
public class BalanceController {

    private final BalanceService balanceService;

    @GetMapping("/{userId}/{balanceId}")
    public BigDecimal getUserBalance(@PathVariable String userId, @PathVariable String balanceId) {
        return balanceService.getUserBalance(userId, balanceId);
    }

    @PostMapping("/put/{balanceId}")
    public ResponseEntity<String> addBalance(@PathVariable String balanceId, @RequestBody BigDecimal amount) {
        BalanceDto balanceDto;
        try {
            balanceDto = balanceService.addBalance(balanceId, amount);
            return new ResponseEntity<>(balanceDto.toString(), HttpStatus.OK);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/withdraw/{balanceId}")
    public ResponseEntity<String> withdraw(@PathVariable String balanceId, @RequestBody BigDecimal amount) {
        BalanceDto balanceDto;
        try {
            balanceDto = balanceService.withdraw(balanceId, amount);
            return new ResponseEntity<>(balanceDto.toString(), HttpStatus.OK);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
}
