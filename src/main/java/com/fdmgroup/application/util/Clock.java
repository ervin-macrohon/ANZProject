package com.fdmgroup.application.util;

import java.time.LocalDate;

import org.springframework.stereotype.Component;

@Component
public class Clock {

	public LocalDate getTodaysDate() {
		return LocalDate.now();
	}

}
