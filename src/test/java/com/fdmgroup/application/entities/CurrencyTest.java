package com.fdmgroup.application.entities;

import org.junit.Test;

import nl.jqno.equalsverifier.EqualsVerifier;

public class CurrencyTest {
	@Test
	public void hashcode_equals_test() {
		EqualsVerifier.forClass(Currency.class)
	    .withPrefabValues(Transaction.class, new Transaction(1), new Transaction(2))
	    .withPrefabValues(Account.class, new Account(1), new Account(2))
	    .withIgnoredFields("transactions")
	    .withIgnoredFields("accounts")
	    .verify();
	}

}
