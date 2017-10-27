package com.kilfat.database.service;

import com.kilfat.database.entity.Account;
import com.kilfat.database.entity.User;
import com.kilfat.database.repository.AccountRepository;
import com.kilfat.exception.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class AccountService {

    private AccountRepository accountRepository;

    @Autowired
    public AccountService(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    @Transactional(readOnly = true)
    public Account getAccount(Long id) {
        return accountRepository.findById(id).orElseThrow(() -> new EntityNotFoundException(Account.class, id));
    }

    public Account saveAccount(Account account) {
        return accountRepository.save(account);
    }

    public void deleteAccount(Account account) {
        accountRepository.delete(account);
    }

    @Transactional(readOnly = true)
    public List<Account> getAccountsByUser(User user) {
        return accountRepository.findAccountsByUser(user);
    }
}
