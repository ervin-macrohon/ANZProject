package com.fdmgroup.application.tables;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.mockito.Mockito.*;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

import org.junit.Test;

import com.fdmgroup.application.entities.Account;
import com.fdmgroup.application.entities.AccountType;
import com.fdmgroup.application.entities.Currency;
import com.fdmgroup.application.entities.Transaction;

public class AccountsTableRowTest {
	private Account account = mock(Account.class);
	private AccountType accountType = mock(AccountType.class);
	private final LocalDate date = LocalDate.of(2018, 11, 8);
	private Currency currency = mock(Currency.class);
	private Set<Transaction> transactions;
	
	@Test
	public void null_account_returns_default_values() {
		AccountsTableRow row = new AccountsTableRow(null, date);

		assertEquals(0, row.getAccountNumber());
		assertNull(row.getAccountName());
		assertNull(row.getAccountType());
		assertNull(row.getBalanceDate());
		assertNull(row.getCurrency());
		assertEquals(0, row.getOpeningAccountBalance(), 0.0);
	}

	@Test
	public void constructor_sets_fields_calculated_from_database() {
		when(account.getAccountNumber()).thenReturn(123456789l);
		when(account.getAccountName()).thenReturn("SGSAVINGS726");
		stubAccount();
		stubCurrency();
		stubTransactions();

		AccountsTableRow row = new AccountsTableRow(account, date);

		assertEquals(123456789, row.getAccountNumber());
		assertEquals("SGSAVINGS726", row.getAccountName());
		assertEquals("SAVINGS", row.getAccountType());
		assertEquals("08/11/2018", row.getBalanceDate());
		assertEquals("SGD", row.getCurrency());
		assertEquals(5, row.getOpeningAccountBalance(), 0.0);
	}

	private void stubTransactions() {
		transactions = new HashSet<Transaction>();
		when(account.getTransactions()).thenReturn(transactions);
		transactions.add(stubTransaction(50, 0, LocalDate.of(2018, 11, 8)));
		transactions.add(stubTransaction(0, 45, LocalDate.of(2018, 11, 7)));
		transactions.add(stubTransaction(0, 5, LocalDate.of(2018, 11, 9)));
	}

	private Transaction stubTransaction(double credit, double debit, LocalDate valueDate) {
		Transaction trans = mock(Transaction.class);
		when(trans.getCredit()).thenReturn(credit);
		when(trans.getDebit()).thenReturn(debit);
		when(trans.getValueDate()).thenReturn(Timestamp.valueOf(valueDate.atStartOfDay()));
		return trans;
	}

	private void stubCurrency() {
		when(account.getCurrency()).thenReturn(currency);
		when(currency.getSymbol()).thenReturn("SGD");
	}

	private void stubAccount() {
		when(account.getAccountType()).thenReturn(accountType);
		when(accountType.getType()).thenReturn("SAVINGS");
	}
}
