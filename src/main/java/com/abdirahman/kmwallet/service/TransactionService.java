package com.abdirahman.kmwallet.service;

import com.abdirahman.kmwallet.model.entity.Account;
import com.abdirahman.kmwallet.model.entity.Transaction;
import com.abdirahman.kmwallet.repository.AccountRepository;
import com.abdirahman.kmwallet.repository.TransactionRepository;
import com.abdirahman.kmwallet.validation.TransactionValidation;
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

    public List<Transaction> getAllTransactionsByAccount(String accountId) {
        Optional<List<Transaction>> transactionList = transactionRepository.findByAccountId(accountId);
        if (transactionList.isEmpty()) throw new RuntimeException("No account found by id {"+accountId+"}");
        else return transactionList.get();
    }

    public List<Transaction> getAllTransactionsByTarget(String targetAccount) {
        Optional<List<Transaction>> transactionList = transactionRepository.findByTargetAccount(targetAccount);
        if (transactionList.isEmpty()) throw new RuntimeException("No account found by id {"+targetAccount+"}");
        else return transactionList.get();
    }

    public List<Transaction> getAllTransactionsByType(String type) {
        Optional<List<Transaction>> transactionList = transactionRepository.findByTransactionType(type);
        if (transactionList.isEmpty()) throw new RuntimeException("No account found by type {"+type+"}");
        else return transactionList.get();
    }

    public Transaction addTransaction(Transaction transaction) { return transactionRepository.save(transaction);
    }

    public Transaction deposit(String accountId, Transaction transaction) {
        TransactionValidation.depositValidation(transaction);
        Optional<Account> accountOptional = accountRepository.findById(accountId);
        if (accountOptional.isEmpty()) throw new RuntimeException("No account found for id {"+accountId+"}");
        else {
            Account account = accountOptional.get();
            account.setBalance(account.getBalance() + transaction.getBalance());
            accountRepository.save(account);
            return addTransaction(transaction);
        }
    }

    public Transaction withdraw(String accountId, Transaction transaction) {
        Optional<Account> accountOptional = accountRepository.findById(accountId);
        if (accountOptional.isEmpty()) throw new RuntimeException("No account found for id {"+accountId+"}");
        else {
            Account account = accountOptional.get();
            TransactionValidation.withdrawValidation(account, transaction);
            account.setBalance(account.getBalance() - transaction.getBalance());
            accountRepository.save(account);
            return addTransaction(transaction);
        }
    }

    public Transaction transfer(String accountId, Transaction transaction) {
        Optional<Account> accountOptional = accountRepository.findById(accountId);
        if (accountOptional.isEmpty()) throw new RuntimeException("No account found for id {"+accountId+"}");
        else {
            Optional<Account> targetAccount = accountRepository.findById(transaction.getTargetAccount());
            if (targetAccount.isEmpty()) throw new RuntimeException("No account found for id {"+transaction.getTargetAccount()+"}");
            else {
                Account account = accountOptional.get();
                TransactionValidation.transferValidation(account, transaction);
                account.setBalance(account.getBalance() - transaction.getBalance());

                Account target = targetAccount.get();
                target.setBalance(target.getBalance() + transaction.getBalance());

                accountRepository.save(account);
                accountRepository.save(target);

                return addTransaction(transaction);
            }
        }
    }
}
