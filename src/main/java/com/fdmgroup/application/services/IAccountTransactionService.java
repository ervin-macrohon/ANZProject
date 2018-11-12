package com.fdmgroup.application.services;

import com.fdmgroup.exceptions.ResourceNotFoundException;

public interface IAccountTransactionService {

	String getTransactions(long accountNumber) throws ResourceNotFoundException;

}
