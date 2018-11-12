package com.fdmgroup.application.factories;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.fdmgroup.application.entities.Account;
import com.fdmgroup.application.tables.AccountsTableRow;

public class AccountsTableFactoryTest {
	@InjectMocks
	private AccountsTableFactory tableFactory;
	@Mock
	private AccountsTableRowFactory rowFactory;
	private LocalDate date = LocalDate.of(2018, 11, 8);
	@Mock
	private AccountsTableRow row1;
	@Mock
	private AccountsTableRow row2;
	@Mock
	private Account account1;
	@Mock
	private Account account2;
	private final List<Account> list = new ArrayList<>();
	
	
	@Before
	public void setup() {
		MockitoAnnotations.initMocks(this);
	}
	
	@Test
	public void create_table_with_null_returns_empty_list() {
		List<AccountsTableRow> table = tableFactory.createTable(null, date);
		
		assertNotNull(table);
		assertTrue(table instanceof List);
		assertTrue(table.isEmpty());
	}
	
	@Test
	public void create_table_with_valid_account_list_returns_tablerows() {
		stubAccount(account1, row1);
		stubAccount(account2, row2);
		
		List<AccountsTableRow> table = tableFactory.createTable(list, date);
		
		assertEquals(2, table.size());
		verify(rowFactory).createTableRow(account1, date);
		verify(rowFactory).createTableRow(account2, date);
		assertSame(table.get(0), row1);
		assertSame(table.get(1), row2);
	}

	private void stubAccount(Account account, AccountsTableRow row) {
		list.add(account);
		when(rowFactory.createTableRow(account, date)).thenReturn(row);
	}
}
