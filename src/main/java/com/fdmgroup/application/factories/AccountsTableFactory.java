package com.fdmgroup.application.factories;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.fdmgroup.application.entities.Account;
import com.fdmgroup.application.tables.AccountsTableRow;

@Component
public class AccountsTableFactory {
	@Resource
	private AccountsTableRowFactory rowFactory;

	public List<AccountsTableRow> createTable(List<Account> list, LocalDate date) {
		List<AccountsTableRow> table = new ArrayList<>();
		if (list != null)
			list.stream().forEach(a -> table.add(rowFactory.createTableRow(a, date)));
		
		return table;
	}

}
