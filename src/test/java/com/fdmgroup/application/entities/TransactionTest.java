package com.fdmgroup.application.entities;

import org.junit.Test;

import nl.jqno.equalsverifier.EqualsVerifier;

public class TransactionTest {
	@Test
	public void hashcode_equals_test() {
		EqualsVerifier.forClass(Transaction.class)
	    .withPrefabValues(Currency.class, new Currency(1), new Currency(2))
	    .withPrefabValues(Account.class, new Account(1), new Account(2))
	    .withIgnoredFields("currency")
	    .withIgnoredFields("account")
	    .verify();
	}
}
