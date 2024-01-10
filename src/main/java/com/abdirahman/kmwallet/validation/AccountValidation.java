package com.abdirahman.kmwallet.validation;

import com.abdirahman.kmwallet.model.entity.Account;

public class AccountValidation {
    public static void validateAccount(Account account) {
        if (!account.getAccountType().equals("savings") &&
                !account.getAccountType().equals("checking") &&
                !account.getAccountType().equals("investment") &&
                !account.getAccountType().equals("student")) throw new RuntimeException("not a valid account type");
    }
}
