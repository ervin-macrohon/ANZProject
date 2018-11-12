package com.fdmgroup.application.repositories;

import org.springframework.data.repository.CrudRepository;

import com.fdmgroup.application.entities.Transaction;

public interface TransactionRepository extends CrudRepository<Transaction, Long> {

}
