package com.kilfat.database.repository;

import com.kilfat.database.entity.Account;
import com.kilfat.database.entity.User;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface AccountRepository extends CrudRepository<Account, Long> {

    List<Account> findAccountsByUser(User user);
}
