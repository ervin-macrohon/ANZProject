package com.fdmgroup.application.repositories;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.fdmgroup.application.entities.Account;

@Component
public class ExtendedAccountRepository {
	@Resource
	private AccountRepository repo;

	public Account findByAccountNumber(long accountNumber) {
		List<Account> accounts = StreamSupport.stream(repo.findAll().spliterator(), false)
			.filter(a->{return a.getAccountNumber() == accountNumber;})
			.collect(Collectors.toList());
		if (accounts.isEmpty()) 
			return null;
		return accounts.get(0);
	}

}
