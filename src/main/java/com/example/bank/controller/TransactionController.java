package com.example.bank.controller;

import com.example.bank.service.TransactionService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.format.DateTimeParseException;

@AllArgsConstructor
@RestController
@RequestMapping("/v1/transactions")
public class TransactionController {

    private final TransactionService transactionService;


    @GetMapping("/{balanceId}")
    public ResponseEntity getOperationList(@PathVariable String balanceId,
                                           @RequestParam(name = "startDate", defaultValue = "01.01.1900") String startDate,
                                           @RequestParam(name = "endDate", defaultValue = "31.12.2500") String endDate) {
        try {
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .contentType(MediaType.APPLICATION_JSON)
                    .body(transactionService.findTransactions(balanceId, startDate, endDate));
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        } catch (DateTimeParseException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Date should be in format dd.mm.yyyy " + e.getMessage());
        }
    }
}
