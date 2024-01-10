package com.abdirahman.kmwallet.controller;

import com.abdirahman.kmwallet.model.entity.Account;
import com.abdirahman.kmwallet.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/kmwallet")
public class AccountController {
    AccountService accountService;

    @Autowired
    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }

    public AccountController() {
    }

    @GetMapping("/account")
    public ResponseEntity<List<Account>> getAllAccounts() {
        List<Account> accounts = accountService.getAllAccounts();
        return new ResponseEntity<>(accounts, HttpStatus.OK);
    }

    @GetMapping("/customer/account/{id}")
    public ResponseEntity<List<Account>> getAllAccountsByCustomer(@PathVariable String id) {
        List<Account> accounts = accountService.getAccountByCustomer(id);
        return new ResponseEntity<>(accounts, HttpStatus.OK);
    }

    @GetMapping("/account/{id}")
    public ResponseEntity<Account> getSingleAccount(@PathVariable String id) {
        Account account = accountService.getAccount(id);
        return new ResponseEntity<>(account, HttpStatus.OK);
    }

    @PostMapping("/account")
    public ResponseEntity<String> addAccount(@RequestBody Account account) {
        accountService.addAccount(account);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping("/account/{id}")
    public ResponseEntity<String> updateAccount(@PathVariable String id, @RequestBody Account account) {
        accountService.updateAccount(id, account);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/account/{id}")
    public ResponseEntity<String> addAccount(@PathVariable String id) {
        accountService.deleteAccount(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
