package com.fdmgroup.application.entities;

import java.util.Set;

import javax.persistence.*;

@Entity
@Table(name = "Accounts")
public class Account {
	@Id
	@GeneratedValue
	private long id;

	@Column(nullable = false)
	private long accountNumber;

	@Column(nullable = false)
	private String accountName;

	@ManyToOne
	@JoinColumn(nullable = false)
	private User user;

	@OneToMany(mappedBy = "account", fetch = FetchType.EAGER)
	private Set<Transaction> transactions;

	@ManyToOne
	@JoinColumn(nullable = false)
	private Currency currency;

	@ManyToOne
	@JoinColumn(nullable = false)
	private AccountType accountType;

	public Account() {
	}

	public Account(long id) {
		this.id = id;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public long getAccountNumber() {
		return accountNumber;
	}

	public void setAccountNumber(long accountNumber) {
		this.accountNumber = accountNumber;
	}

	public String getAccountName() {
		return accountName;
	}

	public void setAccountName(String accountName) {
		this.accountName = accountName;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Set<Transaction> getTransactions() {
		return transactions;
	}

	public void setTransactions(Set<Transaction> transactions) {
		this.transactions = transactions;
	}

	public Currency getCurrency() {
		return currency;
	}

	public void setCurrency(Currency currency) {
		this.currency = currency;
	}

	public AccountType getAccountType() {
		return accountType;
	}

	public void setAccountType(AccountType accountType) {
		this.accountType = accountType;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((accountName == null) ? 0 : accountName.hashCode());
		result = prime * result + (int) (accountNumber ^ (accountNumber >>> 32));
		result = prime * result + (int) (id ^ (id >>> 32));
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof Account))
			return false;
		Account other = (Account) obj;
		if (accountName == null) {
			if (other.accountName != null)
				return false;
		} else if (!accountName.equals(other.accountName))
			return false;
		if (accountNumber != other.accountNumber)
			return false;
		if (id != other.id)
			return false;
		return true;
	}
}
