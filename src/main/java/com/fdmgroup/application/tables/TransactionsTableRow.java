package com.fdmgroup.application.tables;

import java.sql.Timestamp;
import java.time.format.DateTimeFormatter;

import com.fdmgroup.application.entities.Account;
import com.fdmgroup.application.entities.Transaction;

public class TransactionsTableRow {
	private long accountNumber;
	private String accountName;
	private String valueDate;
	private String currency;
	private double debitAmount;
	private double creditAmount;
	private String debitCredit;
	private String transactionNarrative;

	public TransactionsTableRow(Transaction transaction) {
		if (transaction != null) {
			Account account = transaction.getAccount();
			accountNumber = account.getAccountNumber();
			accountName = account.getAccountName();
			setFormattedDate(transaction.getValueDate());
			currency = transaction.getCurrency().getSymbol();
			setDebitCreditAmounts(transaction);
			transactionNarrative = transaction.getTransactionNarrative();
		}

	}

	private void setDebitCreditAmounts(Transaction transaction) {
		if (transaction.getCredit() == 0) {
			debitAmount = transaction.getDebit();
			debitCredit = "Debit";
		} else {
			creditAmount = transaction.getCredit();
			debitCredit = "Credit";
		}

	}

	private void setFormattedDate(Timestamp date) {
		valueDate = date.toLocalDateTime().toLocalDate().atStartOfDay()
				.format(DateTimeFormatter.ofPattern("MMM. dd, yyyy"));

	}

	public long getAccountNumber() {
		return accountNumber;
	}

	public String getAccountName() {
		return accountName;
	}

	public String getValueDate() {
		return valueDate;
	}

	public String getCurrency() {
		return currency;
	}

	public double getDebitAmount() {
		return debitAmount;
	}

	public double getCreditAmount() {
		return creditAmount;
	}

	public String getDebitCredit() {
		return debitCredit;
	}

	public String getTransactionNarrative() {
		return transactionNarrative;
	}

}
