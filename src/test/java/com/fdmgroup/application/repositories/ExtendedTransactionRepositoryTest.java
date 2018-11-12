package com.fdmgroup.application.repositories;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.*;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.fdmgroup.application.entities.Account;
import com.fdmgroup.application.entities.Transaction;

public class ExtendedTransactionRepositoryTest {
	@InjectMocks
	private ExtendedTransactionRepository exTransactionRepo;
	@Mock
	private TransactionRepository transRepo;
	private List<Transaction> dbTransactions;
	
	@Before
	public void setup() {
		MockitoAnnotations.initMocks(this);
		dbTransactions = new ArrayList<>();
		dbTransactions.add(stubTransaction(123456789l, LocalDate.of(2018, 11, 11)));
		dbTransactions.add(stubTransaction(123456799l, LocalDate.of(2018, 11, 10)));
		dbTransactions.add(stubTransaction(123456789l, LocalDate.of(2018, 11, 8)));
	}

	private Transaction stubTransaction(long accNumber, LocalDate date) {
		Transaction trans = mock(Transaction.class);
		Account account = mock(Account.class);
		when(trans.getAccount()).thenReturn(account);
		when(trans.getValueDate()).thenReturn(Timestamp.valueOf(date.atStartOfDay()));
		when(account.getAccountNumber()).thenReturn(accNumber);
		return trans;
	}

	@Test
	public void returns_only_transactions_with_matching_account_number_and_sorted_by_earliest_value_date() {
		when(transRepo.findAll()).thenReturn(dbTransactions);
		
		List<Transaction> transactions = exTransactionRepo.findByAccountNum(123456789l);
		
		assertEquals(2, transactions.size());
		assertEquals(Timestamp.valueOf(LocalDate.of(2018, 11, 8).atStartOfDay()), transactions.get(0).getValueDate());
		assertEquals(Timestamp.valueOf(LocalDate.of(2018, 11, 11).atStartOfDay()), transactions.get(1).getValueDate());
	}
	
	@Test
	public void empty_db_list_returns_empty_list_of_transactions() {
		when(transRepo.findAll()).thenReturn(new ArrayList<>());

		List<Transaction> transactions = exTransactionRepo.findByAccountNum(123456789l);
		
		assertNotNull(transactions);
		assertEquals(0, transactions.size());
	}

}
