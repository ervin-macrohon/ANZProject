package com.fdmgroup.application.factories;

import org.springframework.stereotype.Component;

import com.fdmgroup.application.entities.Transaction;
import com.fdmgroup.application.tables.TransactionsTableRow;

@Component
public class TransactionsTableRowFactory {

	public TransactionsTableRow createTableRow(Transaction transaction) {
		return new TransactionsTableRow(transaction);
		
	}

}
