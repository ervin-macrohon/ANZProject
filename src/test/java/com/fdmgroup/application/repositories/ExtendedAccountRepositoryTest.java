package com.fdmgroup.application.repositories;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertSame;
import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.fdmgroup.application.entities.Account;

public class ExtendedAccountRepositoryTest {
	@InjectMocks
	private ExtendedAccountRepository exAccRepo;
	@Mock
	private AccountRepository repo;
	private List<Account> dbAccounts;
	
	@Before
	public void setup() {
		MockitoAnnotations.initMocks(this);
		dbAccounts = new ArrayList<>();
		dbAccounts.add(stubAccount(123456789l));
		dbAccounts.add(stubAccount(123456788l));
		dbAccounts.add(stubAccount(123456787l));
	}
	
	private Account stubAccount(long accountNumber) {
		Account mock = mock(Account.class);
		when(mock.getAccountNumber()).thenReturn(accountNumber);
		return mock;
	}

	@Test
	public void returns_null_when_no_match_found() {
		when(repo.findAll()).thenReturn(dbAccounts);
		
		Account account = exAccRepo.findByAccountNumber(999999999l);
		
		assertNull(account);
	}
	
	@Test
	public void returns_only_account_with_matching_account_number() {
		when(repo.findAll()).thenReturn(dbAccounts);
		
		Account account = exAccRepo.findByAccountNumber(123456789l);
		
		assertSame(dbAccounts.get(0), account);
	}
}
