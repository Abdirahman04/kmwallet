package com.abdirahman.kmwallet.model.entity;

import jakarta.persistence.*;
import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "transactions")
public class Transaction {
    @Id
    @GeneratedValue(generator = "custom-id-generator")
    @GenericGenerator(name = "custom-id-generator", strategy = "com.abdirahman.kmwallet.model.generator.CustomTransactionIdGenerator")
    @Column(name = "transaction_id", nullable = false)
    private String transactionId;

    @Column(nullable = false)
    @JoinColumn(name = "account_id", referencedColumnName = "account_id")
    private String accountId;

    @Column(name = "transaction_type", nullable = false)
    private String transactionType;

    @JoinColumn(name = "target_account", referencedColumnName = "account_id")
    private String targetAccount;

    @Column(name = "balance", nullable = false)
    private double balance = 0.0;

    public Transaction() {
    }

    public Transaction(String accountId, String transactionType, double balance) {
        this.accountId = accountId;
        this.transactionType = transactionType;
        this.balance = balance;
    }

    public Transaction(String accountId, String transactionType, String targetAccount, double balance) {
        this.accountId = accountId;
        this.transactionType = transactionType;
        this.targetAccount = targetAccount;
        this.balance = balance;
    }

    public String getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(String transactionId) {
        this.transactionId = transactionId;
    }

    public String getAccountId() {
        return accountId;
    }

    public void setAccountId(String accountId) {
        this.accountId = accountId;
    }

    public String getTransactionType() {
        return transactionType;
    }

    public void setTransactionType(String transactionType) {
        this.transactionType = transactionType;
    }

    public String getTargetAccount() {
        return targetAccount;
    }

    public void setTargetAccount(String targetAccount) {
        this.targetAccount = targetAccount;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    @Override
    public String toString() {
        return "Transaction{" +
                "transactionId='" + transactionId + '\'' +
                ", accountId='" + accountId + '\'' +
                ", transactionType='" + transactionType + '\'' +
                ", targetAccount='" + targetAccount + '\'' +
                ", balance=" + balance +
                '}';
    }
}
