package com.fdmgroup.application.factories;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
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
		List<Account> list = new ArrayList<>();
		Account account1 = mock(Account.class);
		list.add(account1);
		when(rowFactory.createTableRow(account1, date)).thenReturn(row1);
		Account account2 = mock(Account.class);
		list.add(account2);
		when(rowFactory.createTableRow(account2, date)).thenReturn(row2);
		
		List<AccountsTableRow> table = tableFactory.createTable(list, date);
		
		assertEquals(2, table.size());
		verify(rowFactory).createTableRow(account1, date);
		verify(rowFactory).createTableRow(account2, date);
		assertNotNull(table.get(0));
		assertNotNull(table.get(1));
	}
}
