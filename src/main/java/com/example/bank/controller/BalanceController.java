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

    @GetMapping("/{userId}")
    public BigDecimal getUserBalance(@PathVariable String userId) {
        return balanceService.getUserBalance(userId);
    }

    @PostMapping("/put/{userId}")
    public ResponseEntity<String> addBalance(@PathVariable String userId, @RequestBody BigDecimal amount) {
        BalanceDto balanceDto;
        try {
            balanceDto = balanceService.addBalance(userId, amount);
            return new ResponseEntity<>(balanceDto.toString(), HttpStatus.OK);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/withdraw/{userId}")
    public ResponseEntity<String> withdraw(@PathVariable String userId, @RequestBody BigDecimal amount) {
        BalanceDto balanceDto;
        try {
            balanceDto = balanceService.withdraw(userId, amount);
            return new ResponseEntity<>(balanceDto.toString(), HttpStatus.OK);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
}
