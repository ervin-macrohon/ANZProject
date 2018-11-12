package com.fdmgroup.application.controllers;

import javax.annotation.Resource;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fdmgroup.application.services.IAccountOverviewService;
import com.fdmgroup.application.services.IAccountTransactionService;
import com.fdmgroup.application.util.Clock;
import com.fdmgroup.exceptions.ResourceNotFoundException;

@RestController
public class AccountsController {
	@Resource
	private IAccountOverviewService accOverviewService;
	@Resource
	private Clock clock;
	@Resource
	private IAccountTransactionService accTrasactionsService;

	@RequestMapping("/accounts")
	public String getAccountsOverview() {
		return accOverviewService.getAccounts(clock.getTodaysDate());
	}

	@RequestMapping("/accounts/{id}")
	public String getTransactionDetails(@PathVariable("id") long id) throws ResourceNotFoundException {
		return accTrasactionsService.getTransactions(id);

	}

}
