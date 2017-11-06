package com.kilfat.database.service.implementations;

import com.kilfat.database.entity.FundsTransaction;
import com.kilfat.database.repository.FundsTransactionRepository;
import com.kilfat.database.service.interfaces.FundsTransactionService;
import com.kilfat.exception.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@PreAuthorize("hasAnyAuthority('USER', 'ADMIN')")
public class FundsTransactionServiceImpl implements FundsTransactionService {

    private FundsTransactionRepository fundsTransactionRepository;

    @Autowired
    public FundsTransactionServiceImpl(FundsTransactionRepository fundsTransactionRepository) {
        this.fundsTransactionRepository = fundsTransactionRepository;
    }

    @Transactional(readOnly = true)
    public FundsTransaction getFundsTransaction(Long id) {
        return fundsTransactionRepository.findById(id)
            .orElseThrow(() -> new EntityNotFoundException(
                String.format("%s with id=%s is not found!", "Transaction", id)));
    }

    public FundsTransaction saveFundsTransaction(FundsTransaction fundsTransaction) {
        return fundsTransactionRepository.save(fundsTransaction);
    }

    public void deleteFundsTransaction(FundsTransaction fundsTransaction) {
        fundsTransactionRepository.delete(fundsTransaction);
    }

    public void deleteFundsTransaction(Long transactionId) {
        fundsTransactionRepository.deleteById(transactionId);
    }
}
