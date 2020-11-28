package com.example.microsvc.sb.controller;

import com.example.microsvc.sb.dto.Balance;
import com.example.microsvc.sb.model.Transaction;
import com.example.microsvc.sb.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("rest")
public class TransactionController {

    @Autowired
    private TransactionService transactionService;

    @GetMapping(path = "/{id}")
    public Transaction getTransactionById(@PathVariable("id") int id) {
        return transactionService.getTransactionById(id).orElseThrow(() -> new ResponseStatusException(
                HttpStatus.NOT_FOUND, String.format("Transaction with id: %d not found", id)
        ));
    }

    @GetMapping(path = "/account/{id}")
    public List<Transaction> getTransactionsByAccount(@PathVariable("id") String id) {
        return transactionService.getTransactionsByAccount(id);
    }

    @GetMapping(path = "/account/{id}/{from}/{to}")
    public List<Transaction> getTransactionsByAccount(
            @PathVariable("id") String id,
            @PathVariable("from") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime from,
            @PathVariable("to") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime to) {
        return transactionService.getTransactionsByAccount(id, from, to);
    }

    @GetMapping(path = "/balance/{id}")
    public Balance getBalance(@PathVariable("id") String accountId) {
        return transactionService.getAccountBalance(accountId, LocalDateTime.now());
    }

    @GetMapping(path = "/balance/{id}/{time}")
    public Balance getBalance(
            @PathVariable("id") String accountId,
            @PathVariable("time") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime time) {
        return transactionService.getAccountBalance(accountId, time);
    }

    @PutMapping(path = "/")
    public ResponseEntity<Transaction> addTransaction(@RequestBody Transaction transaction) {
        if (transaction.getId() > 0) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Cannot modify an existing transaction!");
        }
        return new ResponseEntity<>(transactionService.addTransaction(transaction), HttpStatus.CREATED);
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<Void> removeTransaction(@PathVariable("id") int id) {
        Transaction transaction = transactionService.getTransactionById(id).orElseThrow(() -> new ResponseStatusException(
                HttpStatus.NOT_FOUND, String.format("Transaction with id: %d not found", id)
        ));
        transactionService.removeTransaction(transaction);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
