package com.kilfat.database.service;

import com.kilfat.database.entity.FundsTransaction;
import com.kilfat.database.repository.FundsTransactionRepository;
import com.kilfat.exception.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class FundsTransactionService {

    private FundsTransactionRepository fundsTransactionRepository;

    @Autowired
    public FundsTransactionService(FundsTransactionRepository fundsTransactionRepository) {
        this.fundsTransactionRepository = fundsTransactionRepository;
    }

    @Transactional(readOnly = true)
    public FundsTransaction getFundsTransaction(Long id) {
        return fundsTransactionRepository.findById(id)
            .orElseThrow(() -> new EntityNotFoundException(FundsTransaction.class, id));
    }

    public FundsTransaction saveFundsTransaction(FundsTransaction fundsTransaction) {
        return fundsTransactionRepository.save(fundsTransaction);
    }

    public void deleteFundsTransaction(FundsTransaction fundsTransaction) {
        fundsTransactionRepository.delete(fundsTransaction);
    }
}
