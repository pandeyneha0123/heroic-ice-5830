package com.masai.service;

import java.util.List;

import com.masai.model.Customer;

public interface BankAccount {
	
//	<-----------------------------Neha---------------------------------->
	
	public Customer addAccount(BankAccount Account, String customerId);

	public Customer deleteAccount(Integer BankAccountId, String customerId);

	public BankAccount ViewAccount(String AccountNumber, String customerId);

	public List<BankAccount> ViewAllAccount(Integer wid, String customerId);

	public Customer showBalance(String mobileNumber);
	
//	-------------------------------------------------------------------------------

}
