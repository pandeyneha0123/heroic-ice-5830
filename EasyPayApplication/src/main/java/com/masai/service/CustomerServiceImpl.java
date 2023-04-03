package com.masai.service;

import java.util.List;
import java.util.Optional;

import javax.security.auth.login.AccountNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.masai.exception.CustomerException;
import com.masai.exception.CustomerNotFoundException;
import com.masai.model.BankAccount;
import com.masai.model.CurrentUserSession;
import com.masai.model.Customer;
import com.masai.repository.CustomerRepository;
import com.masai.repository.SessionRepository;

@Service
public class CustomerServiceImpl implements CustomerService {
	
		@Autowired
		private CustomerRepository cDao;

		@Autowired
		private SessionRepository csDao;

		
		@Override
		public Customer createCustomer(Customer customer) throws CustomerException {
		
			Customer existingCustomer = cDao.findByEmail(customer.getEmail());
			
			if (existingCustomer == null) {
				return cDao.save(customer);
			} else {
				throw new CustomerException("Customer already registered");
			}

		}
		
		@Override
		public Customer updateCustomer(Customer customer, String key) throws CustomerException {
			
			CurrentUserSession logedInUser = csDao.findByUuid(key);
			
			if(logedInUser == null) {
				throw new CustomerException("Please provide valid key to update the customer");
			}
			
			if(logedInUser.getUserId() == customer.getCId()) {
				return cDao.save(customer);
			}
			else {
				throw new CustomerException("Invalid customer details, please login first");
			}
		}
		

		@Override
		public Customer viewCustomerDetails(Integer customerId, String key) throws CustomerException {
			
			
			
			Optional<CurrentUserSession> cSession = csDao.findById(customerId);
			
			if (cSession.isPresent()) {
				
				Optional<Customer> opt = cDao.findById(customerId);
			
				if(opt.isPresent()) {
					return opt.get();
				}else {
					throw new CustomerException("Something went wrong please try again later!");
				}
				
			} else {
				throw new CustomerException("User must be logged in !");
			}
		}
		
//	-----------------------------------Neha--------------------------------------------------->	

		 @Override
		    public Customer addAccount(BankAccount account, String key) throws CustomerException   {
			 CurrentUserSession logedInUser = csDao.findByUuid(key);
				
				if(logedInUser == null) {
					throw new CustomerException("Please provide valid key to update the customer");
				}
		        // Authenticate customer using provided  key
		        Customer customer = cDao.findById(logedInUser.getUserId()).get();
		        if (customer == null) {
		            throw new CustomerException("Invalid key....");
		        }
		        
		        // Add new account to customer's bank-account list
		        customer.getWallet().getBanks().add(account);
		        
		        // Save updated customer object to repository
		        customer = cDao.save(customer);

		        return customer;
		    }

		 @Override
		    public Customer deleteAccount(Integer accountId, String key) throws CustomerException   {
		        // Authenticate customer using provided security key
			 CurrentUserSession logedInUser = csDao.findByUuid(key);
				
				if(logedInUser == null) {
					throw new CustomerException("Please provide valid key to update the customer");
				}
		        // Authenticate customer using provided  key
		        Customer customer = cDao.findById(logedInUser.getUserId()).get();
		        if (customer == null) {
		            throw new CustomerException("Invalid security key....");
		        }
		        
		        // Find accounts with provided account ID
		        List<BankAccount> bankAccounts = customer.getWallet().getBanks();
		        
		        if(bankAccounts == null ||  bankAccounts.isEmpty()) {
		            throw new CustomerException("Account not found for customer....");
		        }
		        
		        BankAccount deletableAccount = null;
		        for(BankAccount acc: bankAccounts) {
		        	if(acc.getBankAccountId() == accountId) {
		        		deletableAccount = acc;
		        	}
		        }
		        // Throw exception if account not found
		        if(deletableAccount == null) {
		        	throw new CustomerException("Account not found for customer....");
		        }
		        
		        customer.getWallet().getBanks().remove(deletableAccount);
		        
		        return  cDao.save(customer);
		    }
		

		 @Override
		    public BankAccount viewAccount(String accountNumber, String key) throws AccountNotFoundException  {
		        // Authenticate customer using provided security key
			 CurrentUserSession logedInUser = csDao.findByUuid(key);
				
				if(logedInUser == null) {
					throw new AccountNotFoundException("Please provide valid key to update the customer");
				}
		        // Authenticate customer using provided  key
		        Customer customer = cDao.findById(logedInUser.getUserId()).get();
		        if (customer == null) {
		            throw new AccountNotFoundException("Invalid security key");
		        }
		        
		        // Find accounts with provided account ID
		        List<BankAccount> bankAccounts = customer.getWallet().getBanks();
		        
		        if(bankAccounts == null ||  bankAccounts.isEmpty()) {
		            throw new AccountNotFoundException("Account not found for customer....");
		        }
		        
		        BankAccount bankAccount = null;
		        for(BankAccount acc: bankAccounts) {
		        	if(acc.getAccountNumber().equals(accountNumber)) {
		        		bankAccount = acc;
		        	}
		        }
		        if(bankAccount == null) {
		            throw new AccountNotFoundException("Account not found for customer....");
		        }
		       return  bankAccount;
		        
		       
		    }

		 @Override
		    public List<BankAccount> viewAllAccount(Integer customerId, String key) throws  CustomerNotFoundException  {
		        // Authenticate customer using provided security key
		    	 CurrentUserSession logedInUser = csDao.findByUuid(key);
					
					if(logedInUser == null) {
						throw new CustomerNotFoundException("Please provide valid key to update the customer");
					}
			        // Authenticate customer using provided  key
			        Customer customer = cDao.findById(logedInUser.getUserId()).get();
		        if (customer == null) {
		            throw new CustomerNotFoundException("Invalid security key");
		        }
		        
		        // Find customer with provided customer ID
		        if (customer.getWallet()==null) {
		            throw new CustomerNotFoundException("Customer has no wallets...");
		        }
		        
		        // Return customer's account list
		        return customer.getWallet().getBanks();
		    }

		@Override
		public Double showBalance(String email, String Key) throws CustomerException {
			CurrentUserSession logedInUser = csDao.findByUuid(Key);
			
			if(logedInUser == null) {
				throw new CustomerException("Please provide valid key to update the customer");
			}
	        // Authenticate customer using provided  key
	        Customer customer = cDao.findById(logedInUser.getUserId()).get();
	        if (customer == null) {
	            throw new CustomerException("Customer not found");
	        }
	        
	        // Calculate total balance of all customer accounts
	        Double totalBalance = customer.getWallet().getAmount();
	       
	        
	        return totalBalance;
		}
		
		  


}
