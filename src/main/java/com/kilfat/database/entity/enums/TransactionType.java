package com.kilfat.database.entity.enums;

import com.kilfat.exception.TypeNotFoundException;

public enum TransactionType {
    INCOMES, COSTS;

    public static TransactionType findType(String text) {
        if (text == null) {
            throw new TypeNotFoundException(String.format("Transaction type must not be empty!"));
        }
        for (TransactionType transactionType : TransactionType.values()) {
            if (transactionType.toString().equalsIgnoreCase(text)) {
                return transactionType;
            }
        }
        throw new TypeNotFoundException(String.format("Transaction type=%s is not found!", text));
    }
}
