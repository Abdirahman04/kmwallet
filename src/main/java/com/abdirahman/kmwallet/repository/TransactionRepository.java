package com.abdirahman.kmwallet.repository;

import com.abdirahman.kmwallet.model.entity.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface TransactionRepository extends JpaRepository<Transaction, String> {
    Optional<List<Transaction>> findByAccountId(String accountId);
    Optional<List<Transaction>> findByTargetAccount(String targetAccount);
    Optional<List<Transaction>> findByTransactionType(String transactionType);
}
