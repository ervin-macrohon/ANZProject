package com.fdmgroup.application.services;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.mockito.Mockito.*;

import java.time.LocalDate;
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
import com.fdmgroup.application.factories.AccountsTableFactory;
import com.fdmgroup.application.repositories.AccountRepository;
import com.fdmgroup.application.tables.AccountsTableRow;

public class AccountOverviewServiceTest {
	@InjectMocks
	private AccountOverviewService service;
	@Mock
	private AccountRepository accRepo;
	@Mock
	private ObjectMapper mapper;
	@Mock
	private AccountsTableFactory accTableFactory;
	private final LocalDate time = LocalDate.of(2018, 11, 8);
	private ArrayList<Account> list = new ArrayList<>();
	@Mock
	private Account account1;
	@Mock
	private Account account2;
	@Mock
	private AccountsTableRow row1;
	@Mock
	private AccountsTableRow row2;
	@Mock
	List<AccountsTableRow> table;

	@Before
	public void setup() {
		MockitoAnnotations.initMocks(this);
		list.add(account1);
		list.add(account2);
	}

	@Test
	public void empty_list_of_accounts_returns_empty_json() throws JsonProcessingException {
		Iterable<Account> emptyList = new ArrayList<>();
		when(accRepo.findAll()).thenReturn(emptyList);
		when(mapper.writeValueAsString(emptyList)).thenReturn("[]");

		String accounts = service.getAccounts(time);
		assertEquals("[]", accounts);
	}

	@Test
	public void when_parser_cannot_parse_then_return_null() throws JsonProcessingException {
		JsonProcessingException exception = mock(JsonProcessingException.class);
		when(accRepo.findAll()).thenReturn(list);
		when(accTableFactory.createTable(list, time)).thenReturn(table);
		doThrow(exception).when(mapper).writeValueAsString(table);

		String accounts = service.getAccounts(time);

		verify(exception).printStackTrace();
		assertNull(accounts);
	}

	@Test
	public void not_null_list_of_accounts_calls_create_table_on_factory_and_converts_to_json()
			throws JsonProcessingException {
		when(accRepo.findAll()).thenReturn(list);
		when(accTableFactory.createTable(list, time)).thenReturn(table);
		when(mapper.writeValueAsString(table)).thenReturn("[{row1},{row2}]");

		String accounts = service.getAccounts(time);

		verify(accTableFactory).createTable(list, time);
		verify(mapper).writeValueAsString(table);
		assertEquals("[{row1},{row2}]", accounts);
	}
}
