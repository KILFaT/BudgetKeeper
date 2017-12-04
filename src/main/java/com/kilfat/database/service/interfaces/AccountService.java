package com.kilfat.database.service.interfaces;

import com.kilfat.database.entity.Account;
import com.kilfat.database.entity.User;
import com.kilfat.web.model.AccountDTO;

import java.util.List;

public interface AccountService {

    Account getAccount(Long id);

    Account saveAccount(Account account);

    void deleteAccount(Account account);

    List<AccountDTO> getAccountsByUser(User user);

    void deleteAccount(Long accountId);
}
