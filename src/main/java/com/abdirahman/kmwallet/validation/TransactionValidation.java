package com.abdirahman.kmwallet.validation;

import com.abdirahman.kmwallet.model.entity.Account;
import com.abdirahman.kmwallet.model.entity.Transaction;

public class TransactionValidation {
    public static void depositValidation(Transaction transaction) {
        if (!transaction.getTransactionType().equals("deposit")) throw new RuntimeException("type should be deposit");
        if (transaction.getBalance() <= 10) throw new RuntimeException("deposit should be more than 10");
    }

    public static void withdrawValidation(Account account, Transaction transaction) {
        if (!transaction.getTransactionType().equals("withdraw")) throw new RuntimeException("type should be withdraw");
        if (transaction.getBalance() <= 10) throw new RuntimeException("withdraw should be more than 10");
        if (account.getBalance() < transaction.getBalance()) throw new RuntimeException("not enough money to withdraw");
    }

    public static void transferValidation(Account account, Transaction transaction) {
        if (!transaction.getTransactionType().equals("transfer")) throw new RuntimeException("type should be transfer");
        if (transaction.getBalance() <= 10) throw new RuntimeException("transfer should be more than 10");
        if (account.getBalance() < transaction.getBalance()) throw  new RuntimeException("not enough funds to transfer");
    }
}
