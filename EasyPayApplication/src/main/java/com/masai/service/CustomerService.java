package com.masai.service;

import java.math.BigDecimal;
import java.util.List;

import com.masai.exception.CustomerException;
import com.masai.model.BankAccount;
import com.masai.model.Customer;

public interface CustomerService {
	

	public Customer createCustomer(Customer customer) throws CustomerException;
	


	public Customer updateCustomer(Customer customer, String key) throws CustomerException;
	

	public Customer viewCustomerDetails(Integer customerId, String key) throws CustomerException;



	public Customer addAccount(BankAccount account, String key);


	public Customer deleteAccount(Integer accountId, String key);

	public BankAccount viewAccount(String accountNumber, String key);

	public List<BankAccount> viewAllAccount(Integer customerId, String key);

	public BigDecimal showBalance(String email);



	

	
}
