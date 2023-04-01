package com.masai.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.masai.exception.CustomerException;
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

		

}
