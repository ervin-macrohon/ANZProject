package com.fdmgroup.application.factories;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.List;

import org.junit.Test;

import com.fdmgroup.application.entities.Account;
import com.fdmgroup.application.entities.AccountType;
import com.fdmgroup.application.entities.Currency;
import com.fdmgroup.application.tables.AccountsTableRow;

public class AccountsTableRowFactoryTest {
	private final AccountsTableRowFactory factory = new AccountsTableRowFactory();

	@Test
	public void returns_not_null_AccountsTableRow_with_null() {
		LocalDate date = LocalDate.of(2018, 11, 8);
		AccountsTableRow row = factory.createTableRow(null, date);
		
		assertTrue(row instanceof AccountsTableRow);
		assertNotNull(row);
	}
	@Test
	public void returns_not_null_AccountsTable_with_not_null() {
		Account account = stubAccount();
		LocalDate date = LocalDate.of(2018, 11, 8);
		AccountsTableRow row = factory.createTableRow(account, date);
		
		assertTrue(row instanceof AccountsTableRow);
		assertNotNull(row);
	}
	private Account stubAccount() {
		Account account = mock(Account.class);
		AccountType mockType = mock(AccountType.class);
		Currency mockCurrency = mock(Currency.class);
		when(account.getAccountName()).thenReturn("name");
		when(account.getAccountNumber()).thenReturn(1230l);
		when(account.getAccountType()).thenReturn(mockType);
		when(mockType.getType()).thenReturn("SAVINGS");
		when(account.getCurrency()).thenReturn(mockCurrency);
		when(mockCurrency.getSymbol()).thenReturn("AUD");
		when(account.getTransactions()).thenReturn(new HashSet<>());
		
		return account;
	}
}
