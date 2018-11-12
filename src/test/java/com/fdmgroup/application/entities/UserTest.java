package com.fdmgroup.application.entities;

import org.junit.Test;

import nl.jqno.equalsverifier.EqualsVerifier;

public class UserTest {
	@Test
	public void hashcode_equals_test() {
		EqualsVerifier.forClass(User.class)
	    .withPrefabValues(Account.class, new Account(1), new Account(2))
	    .withIgnoredFields("accounts")
	    .verify();
	}
}
