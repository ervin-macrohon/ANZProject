package com.fdmgroup.application.entities;

import org.junit.Test;

import nl.jqno.equalsverifier.EqualsVerifier;

public class AccountTypeTest {
	@Test
	public void hashcode_equals_test() {
		EqualsVerifier.forClass(AccountType.class)
	    .withPrefabValues(User.class, new User(1), new User(2))	    
	    .withPrefabValues(Transaction.class, new Transaction(1), new Transaction(2))
	    .withPrefabValues(Currency.class, new Currency(1), new Currency(2))
	    .withPrefabValues(Account.class, new Account(1), new Account(2))
	    .withIgnoredFields("accounts")
	    .verify();
	}
}
