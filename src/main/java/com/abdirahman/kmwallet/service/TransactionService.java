package com.abdirahman.kmwallet.service;

import com.abdirahman.kmwallet.model.entity.Account;
import com.abdirahman.kmwallet.model.entity.Transaction;
import com.abdirahman.kmwallet.repository.AccountRepository;
import com.abdirahman.kmwallet.repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class TransactionService {
    TransactionRepository transactionRepository;
    AccountRepository accountRepository;

    @Autowired
    public TransactionService(TransactionRepository transactionRepository, AccountRepository accountRepository) {
        this.transactionRepository = transactionRepository;
        this.accountRepository = accountRepository;
    }

    public TransactionService() {
    }

    public List<Transaction> getAllTransactions() {
        return transactionRepository.findAll();
    }

    public Transaction getTransactionById(String id) {
        Optional<Transaction> transaction = transactionRepository.findById(id);
        if (transaction.isEmpty()) throw new RuntimeException("No transaction found of id { " + id + " }");
        else return transaction.get();
    }

    public List<Transaction> getAllTransactionsByAccount(String id) {
        Optional<List<Transaction>> transactionList = transactionRepository.findByAccountId(id);
        if (transactionList.isEmpty()) throw new RuntimeException("No account found by id {"+id+"}");
        else return transactionList.get();
    }

    public List<Transaction> getAllTransactionsByTarget(String id) {
        Optional<List<Transaction>> transactionList = transactionRepository.findByTargetAccount(id);
        if (transactionList.isEmpty()) throw new RuntimeException("No account found by id {"+id+"}");
        else return transactionList.get();
    }

    public List<Transaction> getAllTransactionsByType(String type) {
        Optional<List<Transaction>> transactionList = transactionRepository.findByTransactionType(type);
        if (transactionList.isEmpty()) throw new RuntimeException("No account found by type {"+type+"}");
        else return transactionList.get();
    }

    public void addTransaction(Transaction transaction) {
        transactionRepository.save(transaction);
    }

    public void deposit(String accountId, Transaction transaction) {
        Optional<Account> accountOptional = accountRepository.findById(accountId);
        if (accountOptional.isEmpty()) throw new RuntimeException("No account found for id {"+accountId+"}");
        else {
            addTransaction(transaction);
            Account account = accountOptional.get();
            account.setBalance(account.getBalance() + transaction.getBalance());
            accountRepository.save(account);
        }
    }

    public void withdraw(String accountId, Transaction transaction) {
        Optional<Account> accountOptional = accountRepository.findById(accountId);
        if (accountOptional.isEmpty()) throw new RuntimeException("No account found for id {"+accountId+"}");
        else {
            addTransaction(transaction);
            Account account = accountOptional.get();
            account.setBalance(account.getBalance() - transaction.getBalance());
            accountRepository.save(account);
        }
    }

    public void transfer(String accountId, Transaction transaction) {
        Optional<Account> accountOptional = accountRepository.findById(accountId);
        if (accountOptional.isEmpty()) throw new RuntimeException("No account found for id {"+accountId+"}");
        else {
            Optional<Account> targetAccount = accountRepository.findById(transaction.getTargetAccount());
            if (targetAccount.isEmpty()) throw new RuntimeException("No account found for id {"+transaction.getTargetAccount()+"}");
            else {
                addTransaction(transaction);

                Account account = accountOptional.get();
                account.setBalance(account.getBalance() - transaction.getBalance());

                Account target = targetAccount.get();
                target.setBalance(target.getBalance() + transaction.getBalance());

                accountRepository.save(account);
                accountRepository.save(target);
            }
        }
    }
}
