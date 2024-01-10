package com.abdirahman.kmwallet.controller;

import com.abdirahman.kmwallet.model.entity.Transaction;
import com.abdirahman.kmwallet.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/kmwallet")
public class TransactionController {
    TransactionService transactionService;

    @Autowired
    public TransactionController(TransactionService transactionService) {
        this.transactionService = transactionService;
    }

    public TransactionController() {
    }

    @GetMapping("/transaction")
    public ResponseEntity<List<Transaction>> getAllTransactions() {
        List<Transaction> transactions = transactionService.getAllTransactions();
        return new ResponseEntity<>(transactions, HttpStatus.OK);
    }

    @GetMapping("/transaction/{type}")
    public ResponseEntity<List<Transaction>> getAllTransactionsByType(@PathVariable String type) {
        List<Transaction> transactions = transactionService.getAllTransactionsByType(type);
        return new ResponseEntity<>(transactions, HttpStatus.OK);
    }

    @GetMapping("/transaction/account/{id}")
    public ResponseEntity<List<Transaction>> getAllTransactionsByAccount(@PathVariable String id) {
        List<Transaction> transactions = transactionService.getAllTransactionsByAccount(id);
        return new ResponseEntity<>(transactions, HttpStatus.OK);
    }

    @GetMapping("/transaction/target/{id}")
    public ResponseEntity<List<Transaction>> getAllTransactionsByTarget(@PathVariable String id) {
        List<Transaction> transactions = transactionService.getAllTransactionsByTarget(id);
        return new ResponseEntity<>(transactions, HttpStatus.OK);
    }
}
