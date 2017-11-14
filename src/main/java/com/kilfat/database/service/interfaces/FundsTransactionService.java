package com.kilfat.database.service.interfaces;

import com.kilfat.database.entity.FundsTransaction;

import java.util.List;

public interface FundsTransactionService {

    List<FundsTransaction> getFundsTransactions();

    FundsTransaction getFundsTransaction(Long id);

    FundsTransaction saveFundsTransaction(FundsTransaction fundsTransaction);

    void deleteFundsTransaction(FundsTransaction fundsTransaction);

    void deleteFundsTransaction(Long transactionId);
}
