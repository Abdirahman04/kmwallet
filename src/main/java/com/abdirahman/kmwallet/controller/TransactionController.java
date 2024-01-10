package com.abdirahman.kmwallet.controller;

import com.abdirahman.kmwallet.model.entity.Transaction;
import com.abdirahman.kmwallet.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/kmwallet/transaction")
public class TransactionController {
    TransactionService transactionService;

    @Autowired
    public TransactionController(TransactionService transactionService) {
        this.transactionService = transactionService;
    }

    public TransactionController() {
    }

    @GetMapping("/")
    public ResponseEntity<List<Transaction>> getAllTransactions() {
        List<Transaction> transactions = transactionService.getAllTransactions();
        return new ResponseEntity<>(transactions, HttpStatus.OK);
    }

    @GetMapping("/type/{type}")
    public ResponseEntity<List<Transaction>> getAllTransactionsByType(@PathVariable String type) {
        List<Transaction> transactions = transactionService.getAllTransactionsByType(type);
        return new ResponseEntity<>(transactions, HttpStatus.OK);
    }

    @GetMapping("/account/{id}")
    public ResponseEntity<List<Transaction>> getAllTransactionsByAccount(@PathVariable String id) {
        List<Transaction> transactions = transactionService.getAllTransactionsByAccount(id);
        return new ResponseEntity<>(transactions, HttpStatus.OK);
    }

    @GetMapping("/target/{id}")
    public ResponseEntity<List<Transaction>> getAllTransactionsByTarget(@PathVariable String id) {
        List<Transaction> transactions = transactionService.getAllTransactionsByTarget(id);
        return new ResponseEntity<>(transactions, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Transaction> getTransactionById(@PathVariable String id) {
        Transaction transaction = transactionService.getTransactionById(id);
        return  new ResponseEntity<>(transaction, HttpStatus.OK);
    }

    @PostMapping("/deposit/{id}")
    public ResponseEntity<String> deposit(@PathVariable String id, @RequestBody Transaction transaction) {
        transactionService.deposit(id, transaction);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping("/withdraw/{id}")
    public ResponseEntity<String> withdraw(@PathVariable String id, @RequestBody Transaction transaction) {
        transactionService.withdraw(id, transaction);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping("/transfer/{id}")
    public ResponseEntity<String> transfer(@PathVariable String id, @RequestBody Transaction transaction) {
        transactionService.transfer(id, transaction);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
