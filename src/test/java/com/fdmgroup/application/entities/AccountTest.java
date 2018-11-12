package com.fdmgroup.application.entities;

import org.junit.Test;

import nl.jqno.equalsverifier.EqualsVerifier;

public class AccountTest {
	@Test
	public void hashcode_equals_test() {
		EqualsVerifier.forClass(Account.class)
	    .withPrefabValues(User.class, new User(1), new User(2))
	    .withPrefabValues(Transaction.class, new Transaction(1), new Transaction(2))
	    .withPrefabValues(Currency.class, new Currency(1), new Currency(2))
	    .withPrefabValues(AccountType.class, new AccountType(1), new AccountType(2))
	    .withIgnoredFields("transactions")
	    .withIgnoredFields("currency")
	    .withIgnoredFields("accountType")
	    .withIgnoredFields("user")
	    .verify();
	}
}
