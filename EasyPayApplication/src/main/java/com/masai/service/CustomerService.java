package com.masai.service;



import com.masai.model.Customer;

public interface CustomerService {
	

//	----------------------------Neha--------------------------------------------->
	
  public Customer RegisterCustomer(Customer customer) throws CustomerException;

	
	
	public Customer createCustomer(Customer customer);

	public String customerLogin(CustomerLoginDTO customerDto);

	public String customerLogout(String customerId);

	public CustomerSession checkCustomerSession(String customerId);

	public Customer viewCustomerDetails(String customerId);

	------------------------------------------------------------------------------>


	
}
