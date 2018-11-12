package com.fdmgroup.application.factories;

import java.time.LocalDate;

import org.springframework.stereotype.Component;

import com.fdmgroup.application.entities.Account;
import com.fdmgroup.application.tables.AccountsTableRow;

@Component
public class AccountsTableRowFactory {

	public AccountsTableRow createTableRow(Account account, LocalDate date) {
		return new AccountsTableRow(account, date);
	}

}
