package com.fdmgroup.application.tables;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.mockito.Mockito.*;

import java.sql.Timestamp;
import java.time.LocalDate;

import org.junit.Test;

import com.fdmgroup.application.entities.Account;
import com.fdmgroup.application.entities.Currency;
import com.fdmgroup.application.entities.Transaction;

public class TransactionsTableRowTest {
	private final Transaction transaction = mock(Transaction.class);
	private final Account account = mock(Account.class);
	private final Currency currency = mock(Currency.class);
	
	@Test
	public void constructor_sets_default_values_on_null_transaction() {
		TransactionsTableRow row = new TransactionsTableRow(null);
		
		assertEquals(0, row.getAccountNumber());
		assertNull(row.getAccountName());
		assertNull(row.getValueDate());
		assertNull(row.getTransactionNarrative());
		assertNull(row.getCurrency());
		assertEquals(0, row.getCreditAmount(), 0.0);
		assertEquals(0, row.getDebitAmount(), 0.0);
		assertNull(row.getDebitCredit());
	}

	@Test
	public void constructor_sets_simple_fields_on_not_null_constructor() {
		stubAccount();
		
		TransactionsTableRow row = new TransactionsTableRow(transaction);
		
		assertEquals(123456789l, row.getAccountNumber());
		assertEquals("Currenct Account", row.getAccountName());
		assertEquals("Jan. 12, 2012", row.getValueDate());
		assertEquals("sample narrative", row.getTransactionNarrative());
		assertEquals("SGD", row.getCurrency());
	}
	
	@Test
	public void constructor_sets_correct_values_on_not_null_credit_transaction() {
		stubAccount();
		when(transaction.getCredit()).thenReturn(1000.0);
		when(transaction.getDebit()).thenReturn(0.0);
		
		TransactionsTableRow row = new TransactionsTableRow(transaction);
		assertEquals(1000, row.getCreditAmount(), 0.0);
		assertEquals(0, row.getDebitAmount(), 0.0);
		assertEquals("Credit", row.getDebitCredit());
	}
	@Test
	public void constructor_sets_correct_values_on_not_null_debit_transaction() {
		stubAccount();
		when(transaction.getCredit()).thenReturn(0.0);
		when(transaction.getDebit()).thenReturn(1000.0);
		
		TransactionsTableRow row = new TransactionsTableRow(transaction);

		assertEquals(0, row.getCreditAmount(), 0.0);
		assertEquals(1000, row.getDebitAmount(), 0.0);
		assertEquals("Debit", row.getDebitCredit());
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