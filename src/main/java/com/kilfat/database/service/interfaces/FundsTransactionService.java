package com.kilfat.database.service.interfaces;

import com.kilfat.database.entity.FundsTransaction;

public interface FundsTransactionService {

    FundsTransaction getFundsTransaction(Long id);

    FundsTransaction saveFundsTransaction(FundsTransaction fundsTransaction);

    void deleteFundsTransaction(FundsTransaction fundsTransaction);

    void deleteFundsTransaction(Long transactionId);
}
