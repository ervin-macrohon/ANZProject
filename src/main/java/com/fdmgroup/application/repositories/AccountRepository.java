package com.fdmgroup.application.repositories;

import org.springframework.data.repository.CrudRepository;

import com.fdmgroup.application.entities.Account;

public interface AccountRepository extends CrudRepository<Account, Long>{

}
