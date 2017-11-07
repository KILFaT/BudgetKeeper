package com.kilfat.database.service.implementations;

import com.kilfat.database.entity.Account;
import com.kilfat.database.entity.User;
import com.kilfat.database.repository.AccountRepository;
import com.kilfat.database.service.interfaces.AccountService;
import com.kilfat.database.service.interfaces.UserService;
import com.kilfat.exception.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@PreAuthorize("hasAnyAuthority('ADMIN','USER')")
public class AccountServiceImpl implements AccountService {

    private AccountRepository accountRepository;
    private UserService userService;

    @Autowired
    public AccountServiceImpl(AccountRepository accountRepository, UserService userService) {
        this.accountRepository = accountRepository;
        this.userService = userService;
    }

    @Transactional(readOnly = true)
    public Account getAccount(Long id) {
        return accountRepository.findById(id).orElseThrow(() -> new EntityNotFoundException(
            String.format("%s with id=%s is not found!", "Account", id)));
    }

    public Account saveAccount(Account account) {
        User currentUser = userService.getUser(UserService.getCurrentUserName());
        account.setUser(currentUser);
        return accountRepository.save(account);
    }

    public void deleteAccount(Account account) {
        accountRepository.delete(account);
    }

    @Transactional(readOnly = true)
    public List<Account> getAccountsByUser(User user) {
        return accountRepository.findAccountsByUser(user);
    }

    public void deleteAccount(Long accountId) {
        accountRepository.deleteById(accountId);
    }
}
