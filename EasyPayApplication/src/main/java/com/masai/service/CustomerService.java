package com.masai.service;



import com.masai.exception.CustomerException;
import com.masai.model.Customer;

public interface CustomerService {

	public Customer createCustomer(Customer customer) throws CustomerException;
	
	public Customer updateCustomer(Customer customer, String key) throws CustomerException;

	public Customer viewCustomerDetails(Integer customerId, String key) throws CustomerException;
	
}
