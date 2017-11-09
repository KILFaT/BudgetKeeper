package com.kilfat.database.entity.enums;

import com.kilfat.exception.TypeNotFoundException;

public enum AccountType {
    BANK_CARD, SAVINGS_ACCOUNT, CASH;

    public static AccountType findType(String text) {
        if (text == null) {
            throw new TypeNotFoundException(String.format("Account type must not be empty!"));
        }
        for (AccountType accountType : AccountType.values()) {
            if (accountType.toString().equalsIgnoreCase(text)) {
                return accountType;
            }
        }
        throw new TypeNotFoundException(String.format("Account type=%s is not found!", text));
    }
}
