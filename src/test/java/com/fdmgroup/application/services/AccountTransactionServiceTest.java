package com.fdmgroup.application.services;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fdmgroup.application.entities.Account;
import com.fdmgroup.application.entities.Transaction;
import com.fdmgroup.application.factories.TransactionsTableFactory;
import com.fdmgroup.application.repositories.ExtendedAccountRepository;
import com.fdmgroup.application.repositories.ExtendedTransactionRepository;
import com.fdmgroup.application.tables.TransactionsTableRow;
import com.fdmgroup.exceptions.ResourceNotFoundException;

public class AccountTransactionServiceTest {
	@InjectMocks
	private AccountTransactionService service;
	@Mock
	private ExtendedTransactionRepository exTransRepo;
	@Mock
	private ExtendedAccountRepository exAccRepo;
	@Mock
	private ObjectMapper mapper;
	@Mock
	private TransactionsTableFactory tableFactory;
	@Mock
	private List<Transaction> list;
	@Mock
	private List<TransactionsTableRow> table;
	@Mock
	private Account mockAccount;
	
	@Before
	public void setup() {
		MockitoAnnotations.initMocks(this);
	}
	
	@Test (expected=ResourceNotFoundException.class)
	public void account_that_does_not_exist_returns_resource_not_found() throws ResourceNotFoundException {
		when(exAccRepo.findByAccountNumber(123456789l)).thenReturn(null);
		
		service.getTransactions(123456789l);
	}
	
	@Test
	public void when_parser_cannot_parse_then_return_null() throws JsonProcessingException, ResourceNotFoundException {
		JsonProcessingException e = mock(JsonProcessingException.class);
		when(exAccRepo.findByAccountNumber(123456678)).thenReturn(mockAccount);
		doThrow(e).when(mapper).writeValueAsString(table);
		stubTable();
		
		String transactions = service.getTransactions(123456678);
		
		verify(e).printStackTrace();
		assertNull(transactions);
	}

	private void stubTable() {
		when(exTransRepo.findByAccountNum(123456678)).thenReturn(list);
		when(tableFactory.createTable(list)).thenReturn(table);
	}
	
	@Test
	public void returns_json_transactions_for_given_account() throws JsonProcessingException, ResourceNotFoundException {
		stubTable();
		when(exAccRepo.findByAccountNumber(123456678)).thenReturn(mockAccount);
		when(mapper.writeValueAsString(table)).thenReturn("[{json}]");
		
		String transactions = service.getTransactions(123456678);
		
		verify(exTransRepo).findByAccountNum(123456678);
		verify(tableFactory).createTable(list);
		verify(mapper).writeValueAsString(table);
		assertEquals("[{json}]", transactions);
	}
}
