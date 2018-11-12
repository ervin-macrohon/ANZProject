package com.fdmgroup.application.controllers;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.fdmgroup.application.services.IAccountOverviewService;
import com.fdmgroup.application.services.IAccountTransactionService;
import com.fdmgroup.application.util.Clock;
import com.fdmgroup.exceptions.ResourceNotFoundException;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

import java.time.LocalDate;

import org.junit.Before;
import org.junit.Test;

public class AccountsControllerTest {

    private MockMvc mvc;
	
	@InjectMocks
	private AccountsController controller;
	@Mock
	private IAccountOverviewService accOverviewService;
	@Mock
	private Clock clock;
	@Mock
	private IAccountTransactionService accTrasactionService;
	private final LocalDate mockDate = LocalDate.of(2018, 11, 8);
	private final String accountsJson = "[{\"account_id\":1}]";
	private final String transactionJson = "[{\"transaction_id\":1}]";
	
	@Before
	public void setup() {
		MockitoAnnotations.initMocks(this);
		mvc = MockMvcBuilders.standaloneSetup(controller)
                .build();
		when(clock.getTodaysDate()).thenReturn(mockDate);
	}
	
	@Test
	public void controller_returns_ok_on_accounts_endpoint() throws Exception {
        MockHttpServletResponse response = mvc.perform(
                get("/accounts"))
                .andReturn().getResponse();
 
        assertEquals(HttpStatus.OK.value(), response.getStatus());
	}
	
	@Test
	public void controller_returns_ok_on_accounts_endpoint_with_id() throws Exception {
        MockHttpServletResponse response = mvc.perform(
                get("/accounts/2000"))
                .andReturn().getResponse();
 
        assertEquals(HttpStatus.OK.value(), response.getStatus());
	}
	
	@Test
	public void account_overview_returns_string_from_account_overview_service() {
		when(accOverviewService.getAccounts(mockDate)).thenReturn(accountsJson);
		String json = controller.getAccountsOverview();
		
		verify(accOverviewService).getAccounts(mockDate);
		assertEquals(accountsJson, json);
	}
	
	@Test
	public void account_transaction_details_returns_transaction_json_from_service() throws ResourceNotFoundException {
		when(accTrasactionService.getTransactions(123456789l)).thenReturn(transactionJson);
		String json = controller.getTransactionDetails(123456789l);
		
		verify(accTrasactionService).getTransactions(123456789l);
		assertEquals(transactionJson, json);
	}
}
