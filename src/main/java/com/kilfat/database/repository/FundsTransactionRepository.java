package com.kilfat.database.repository;

import com.kilfat.database.entity.FundsTransaction;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FundsTransactionRepository extends CrudRepository<FundsTransaction, Long> {

}
