package com.fdmgroup.application.factories;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.fdmgroup.application.entities.Transaction;
import com.fdmgroup.application.tables.TransactionsTableRow;

@Component
public class TransactionsTableFactory {
	@Resource
	private TransactionsTableRowFactory rowFactory;

	public List<TransactionsTableRow> createTable(List<Transaction> list) {
		List<TransactionsTableRow> table  = new ArrayList<>();
		list.stream().forEach(t->{
			table.add(rowFactory.createTableRow(t));
		});
		return table;
	}

}
