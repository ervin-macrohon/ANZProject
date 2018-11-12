package com.fdmgroup.application.util;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.time.LocalDate;

import org.junit.Test;

public class ClockTest {
	private final Clock clock = new Clock();
	
	@Test
	public void date_returned_is_not_null_and_is_todays_date() {
		LocalDate todaysDate = clock.getTodaysDate();
		
		assertNotNull(todaysDate);
		assertEquals(LocalDate.now(), todaysDate);
	}
}
