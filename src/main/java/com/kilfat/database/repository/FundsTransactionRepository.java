package com.kilfat.database.repository;

import com.kilfat.database.entity.Account;
import com.kilfat.database.entity.FundsTransaction;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FundsTransactionRepository extends CrudRepository<FundsTransaction, Long> {

    List<FundsTransaction> getFundsTransactionsByAccount(Account account);
}
