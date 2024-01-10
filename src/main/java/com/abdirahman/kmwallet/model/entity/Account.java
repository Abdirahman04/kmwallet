package com.abdirahman.kmwallet.model.entity;

import jakarta.persistence.*;
import org.hibernate.annotations.GenericGenerator;

import java.util.List;

@Entity
@Table(name = "accounts")
public class Account {
    @Id
    @GeneratedValue(generator = "custom-id-generator")
    @GenericGenerator(name = "custom-id-generator", strategy = "com.abdirahman.kmwallet.model.generator.CustomAccountIdGenerator")
    @Column(name = "account_id", nullable = false)
    private String accountId;

    @Column(nullable = false)
    @JoinColumn(name = "customer_id", referencedColumnName = "customer_id")
    private String customerId;

    @Column(name = "account_type", nullable = false)
    private String accountType;

    @Column(name = "balance", nullable = false)
    private double balance = 0;

    @OneToMany(mappedBy = "accountId", cascade = CascadeType.ALL)
    private List<Transaction> transactions;

    @OneToMany(mappedBy = "targetAccount", cascade = CascadeType.ALL)
    private List<Transaction> targetTransactions;

    public Account() {
    }

    public Account(String customerId, String accountType) {
        this.customerId = customerId;
        this.accountType = accountType;
    }

    public Account(String accountId, String customerId, String accountType) {
        this.accountId = accountId;
        this.customerId = customerId;
        this.accountType = accountType;
    }

    public String getAccountId() {
        return accountId;
    }

    public void setAccountId(String accountId) {
        this.accountId = accountId;
    }

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public String getAccountType() {
        return accountType;
    }

    public void setAccountType(String accountType) {
        this.accountType = accountType;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public List<Transaction> getTransactions() {
        return transactions;
    }

    public void setTransactions(List<Transaction> transactions) {
        this.transactions = transactions;
    }

    public List<Transaction> getTargetTransactions() {
        return targetTransactions;
    }

    public void setTargetTransactions(List<Transaction> targetTransactions) {
        this.targetTransactions = targetTransactions;
    }

    @Override
    public String toString() {
        return "Account{" +
                "accountId='" + accountId + '\'' +
                ", customerId='" + customerId + '\'' +
                ", accountType='" + accountType + '\'' +
                ", balance=" + balance +
                '}';
    }
}
