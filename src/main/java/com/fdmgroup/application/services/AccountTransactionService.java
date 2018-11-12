package com.fdmgroup.application.services;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fdmgroup.application.entities.Transaction;
import com.fdmgroup.application.factories.TransactionsTableFactory;
import com.fdmgroup.application.repositories.ExtendedAccountRepository;
import com.fdmgroup.application.repositories.ExtendedTransactionRepository;
import com.fdmgroup.application.tables.TransactionsTableRow;
import com.fdmgroup.exceptions.ResourceNotFoundException;

@Service
public class AccountTransactionService implements IAccountTransactionService{
	@Resource
	private ExtendedTransactionRepository repo;
	@Resource
	private ObjectMapper mapper;
	@Resource
	private TransactionsTableFactory tableFactory;
	@Resource
	private ExtendedAccountRepository exAccRepo;
	
	@Override
	public String getTransactions(long accountNumber) throws ResourceNotFoundException {
		if (exAccRepo.findByAccountNumber(accountNumber) == null)
			throw new ResourceNotFoundException();
		List<Transaction> accTransactions = repo.findByAccountNum(accountNumber);
		List<TransactionsTableRow> table = tableFactory.createTable(accTransactions);
		try {
			return mapper.writeValueAsString(table);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
			return null;
		}
	}

}
