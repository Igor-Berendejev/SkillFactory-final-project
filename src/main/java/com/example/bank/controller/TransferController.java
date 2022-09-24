package com.example.bank.controller;

import com.example.bank.service.TransferService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;

@RestController
@RequestMapping("v1/transfer")
public class TransferController {

    @Autowired
    TransferService transferService;

    @PostMapping
    public ResponseEntity makeTransfer(@RequestParam String balanceIdFrom,
                                       @RequestParam String balanceIdTo,
                                       @RequestBody BigDecimal amount) {
        try {
            transferService.makeTransfer(balanceIdFrom, balanceIdTo, amount);
            return ResponseEntity.status(HttpStatus.OK).body("Transfer successful");
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }
}
