package com.fdmgroup.application.factories;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.*;

import java.sql.Timestamp;
import java.time.LocalDate;

import org.junit.Test;

import com.fdmgroup.application.entities.Account;
import com.fdmgroup.application.entities.Currency;
import com.fdmgroup.application.entities.Transaction;
import com.fdmgroup.application.tables.TransactionsTableRow;

public class TransactionsTableRowFactoryTest {
	private final TransactionsTableRowFactory factory = new TransactionsTableRowFactory();
	private final Transaction transaction = mock(Transaction.class);
	private final Account account = mock(Account.class);
	private final Currency currency = mock(Currency.class);
	
	@Test
	public void returns_not_null_transactions_table_row_with_null_transaction() {
		TransactionsTableRow row = factory.createTableRow(null);
		
		assertNotNull(row);
		assertTrue(row instanceof TransactionsTableRow);
	}
	
	@Test
	public void returns_not_null_transactions_table_row() {
		stubAccount();
		
		TransactionsTableRow row = factory.createTableRow(transaction);
		
		assertNotNull(row);
		assertTrue(row instanceof TransactionsTableRow);
	}

	private void stubAccount() {
		when(transaction.getAccount()).thenReturn(account);
		when(account.getAccountNumber()).thenReturn(123456789l);
		when(account.getAccountName()).thenReturn("Currenct Account");
		when(transaction.getCurrency()).thenReturn(currency);
		when(currency.getSymbol()).thenReturn("SGD");
		when(transaction.getValueDate()).thenReturn(Timestamp.valueOf(LocalDate.of(2012, 1, 12).atStartOfDay()));
		when(transaction.getTransactionNarrative()).thenReturn("sample narrative");
	}
}
