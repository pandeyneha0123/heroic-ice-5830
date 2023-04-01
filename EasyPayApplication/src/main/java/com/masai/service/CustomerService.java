package com.masai.service;

import java.math.BigDecimal;
import java.util.List;

import javax.security.auth.login.AccountNotFoundException;

import com.masai.exception.CustomerException;
import com.masai.exception.CustomerNotFoundException;
import com.masai.model.BankAccount;
import com.masai.model.Customer;

public interface CustomerService {
	

	public Customer createCustomer(Customer customer) throws CustomerException;
	


	public Customer updateCustomer(Customer customer, String key) throws CustomerException;
	

	public Customer viewCustomerDetails(Integer customerId, String key) throws CustomerException;



	public Customer addAccount(BankAccount account, String key)throws CustomerException;;


	public Customer deleteAccount(Integer accountId, String key)throws CustomerException;;

	public BankAccount viewAccount(String accountNumber, String key) throws AccountNotFoundException;

	public List<BankAccount> viewAllAccount(Integer customerId, String key)throws CustomerNotFoundException;;

	public Double showBalance(String email,String Key)throws CustomerException;;



	

	
}
