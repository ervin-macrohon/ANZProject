package com.fdmgroup.application.tables;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Set;

import com.fdmgroup.application.entities.Account;
import com.fdmgroup.application.entities.Transaction;

public class AccountsTableRow {
	private long accountNumber;
	private String accountName;
	private String accountType;
	private String balanceDate;
	private String currency;
	private double openingAccountBalance;

	public AccountsTableRow(Account account, LocalDate date) {
		if (account != null) {
			accountNumber = account.getAccountNumber();
			accountName = account.getAccountName();
			accountType = account.getAccountType().getType();
			currency = account.getCurrency().getSymbol();
			convertAndSetDate(date);
			calculateOpeningAccountBalance(account.getTransactions(), date);
		}
	}

	private void calculateOpeningAccountBalance(Set<Transaction> transactions, LocalDate date) {
		openingAccountBalance = 0;
		transactions.stream().filter(t -> {
			return t.getValueDate().toLocalDateTime().toLocalDate().isBefore(date)
					|| t.getValueDate().toLocalDateTime().toLocalDate().isEqual(date);
		}).forEach((t) -> {
			openingAccountBalance += t.getCredit();
			openingAccountBalance -= t.getDebit();
		});
	}

	private void convertAndSetDate(LocalDate date) {
		this.balanceDate = date.format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));

	}

	public long getAccountNumber() {
		return accountNumber;
	}

	public String getAccountName() {
		return accountName;
	}

	public String getAccountType() {
		return accountType;
	}

	public String getBalanceDate() {
		return balanceDate;
	}

	public String getCurrency() {
		return currency;
	}

	public double getOpeningAccountBalance() {
		return openingAccountBalance;
	}

}
