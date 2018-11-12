package com.fdmgroup.application.services;

import java.time.LocalDate;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fdmgroup.application.entities.Account;
import com.fdmgroup.application.factories.AccountsTableFactory;
import com.fdmgroup.application.repositories.AccountRepository;

@Service
public class AccountOverviewService implements IAccountOverviewService{
	@Resource
	private AccountRepository accRepo;
	
	@Resource
	private ObjectMapper mapper;

	@Resource
	private AccountsTableFactory accTableFactory;

	@Override
	public String getAccounts(LocalDate time) {
		try {
			List<Account> accounts = (List<Account>) accRepo.findAll();
			if (accounts.isEmpty()) {
				return "[]";
			}else {
				return mapper.writeValueAsString(accTableFactory.createTable(accounts, time));
			}
		} catch (JsonProcessingException e) {
			e.printStackTrace();
			return null;
		}
	}

}
