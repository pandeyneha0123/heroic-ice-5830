package com.masai.service;

import java.util.List;

import com.masai.exception.CustomerException;
import com.masai.model.BankAccount;
import com.masai.model.Customer;

public interface BankAccountService {
	
	
	public Customer addAccount(BankAccount Account, String key) throws CustomerException;

	public Customer deleteAccount(Integer BankAccountId, String key) throws CustomerException;

	public BankAccount ViewAccount(String AccountNumber, String key) throws CustomerException;

	public List<BankAccount> ViewAllAccount(Integer wId, String key) throws CustomerException;

	public Double showBalance(String email, String key) throws CustomerException;

}
