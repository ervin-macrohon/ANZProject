package com.fdmgroup.application.repositories;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.fdmgroup.application.entities.Transaction;

@Component
public class ExtendedTransactionRepository {
	@Resource
	private TransactionRepository transRepo;

	public List<Transaction> findByAccountNum(long accountNumber) {
		List<Transaction> accTransactions = StreamSupport.stream(transRepo.findAll().spliterator(), false)
				.filter(t->{return t.getAccount().getAccountNumber() == accountNumber;})
				.collect(Collectors.toList());
		accTransactions.sort((t1, t2)->{return t1.getValueDate().compareTo(t2.getValueDate());});
		return accTransactions;
	}

}
