package com.kilfat.database.service.implementations;

import com.kilfat.database.entity.Account;
import com.kilfat.database.entity.Category;
import com.kilfat.database.entity.FundsTransaction;
import com.kilfat.database.repository.FundsTransactionRepository;
import com.kilfat.database.service.interfaces.AccountService;
import com.kilfat.database.service.interfaces.CategoryService;
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

    private CategoryService categoryService;
    private AccountService accountService;
    private FundsTransactionRepository fundsTransactionRepository;

    @Autowired
    public FundsTransactionServiceImpl(FundsTransactionRepository fundsTransactionRepository,
                                       AccountService accountService,
                                       CategoryService categoryService) {
        this.fundsTransactionRepository = fundsTransactionRepository;
        this.accountService = accountService;
        this.categoryService = categoryService;
    }

    @Transactional(readOnly = true)
    public FundsTransaction getFundsTransaction(Long id) {
        return fundsTransactionRepository.findById(id)
            .orElseThrow(() -> new EntityNotFoundException(
                String.format("%s with id=%s is not found!", "Transaction", id)));
    }

    //    TODO: add hasPermission check!
    //    @PreAuthorize("hasPermission()")
    public FundsTransaction saveFundsTransaction(FundsTransaction fundsTransaction) {
        Account account = accountService.getAccount(fundsTransaction.getAccount().getId());
        switch (fundsTransaction.getTransactionType()) {
            case COSTS:
                account.decreaseAmount(fundsTransaction.getAmount());
                break;
            case INCOMES:
                account.increaseAmount(fundsTransaction.getAmount());
                break;
        }
        account = accountService.saveAccount(account);
        fundsTransaction.setAccount(account);
        Category category = categoryService.getCategory(fundsTransaction.getCategory().getId());
        fundsTransaction.setCategory(category);
        return fundsTransactionRepository.save(fundsTransaction);
    }

    public void deleteFundsTransaction(FundsTransaction fundsTransaction) {
        restoreAccountAmount(fundsTransaction);
        fundsTransactionRepository.delete(fundsTransaction);
    }

    public void deleteFundsTransaction(Long transactionId) {
        FundsTransaction transactionToDelete = getFundsTransaction(transactionId);
        restoreAccountAmount(transactionToDelete);
        fundsTransactionRepository.deleteById(transactionId);
    }

    /**
     * Restore account amount after transaction delete.
     */
    private void restoreAccountAmount(FundsTransaction transactionToDelete) {
        switch (transactionToDelete.getTransactionType()) {
            case COSTS:
                transactionToDelete.getAccount().increaseAmount(transactionToDelete.getAmount());
                break;
            case INCOMES:
                transactionToDelete.getAccount().decreaseAmount(transactionToDelete.getAmount());
                break;
        }
        accountService.saveAccount(transactionToDelete.getAccount());
    }
}
