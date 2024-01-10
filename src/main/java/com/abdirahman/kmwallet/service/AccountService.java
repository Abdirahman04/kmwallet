package com.abdirahman.kmwallet.service;

import com.abdirahman.kmwallet.model.entity.Account;
import com.abdirahman.kmwallet.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class AccountService {
    AccountRepository accountRepository;

    @Autowired
    public AccountService(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    public AccountService() {
    }

    public void addAccount(Account account) {
        accountRepository.save(account);
    }

    public List<Account> getAllAccounts() {
        return accountRepository.findAll();
    }

    public Account getAccount(String id) {
        Optional<Account> account = accountRepository.findById(id);
        if (account.isEmpty()) throw new RuntimeException("No account with id { " + id + " } found");
        else return account.get();
    }

    public List<Account> getAccountByCustomer(String id) {
        Optional<List<Account>> accountList = accountRepository.findByCustomerId(id);
        if (accountList.isEmpty()) throw new RuntimeException("No accounts found for id { " + id + " }");
        else return accountList.get();
    }

    public void updateAccount(String id, Account account) {
        Optional<Account> accountOptional = accountRepository.findById(id);
        if (accountOptional.isEmpty()) throw new RuntimeException("No account found for id {"+id+"}");
        else accountRepository.save(account);
    }

    public void deleteAccount(String id) {
        accountRepository.deleteById(id);
    }
}
