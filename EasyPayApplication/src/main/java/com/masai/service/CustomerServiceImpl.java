package com.masai.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.masai.exception.CustomerException;
import com.masai.model.Customer;
import com.masai.model.Wallet;

import com.masai.repository.CustomerDao;
import com.masai.repository.CustomerRepository;
import com.masai.repository.CustomerSessionDao;

@Service
public class CustomerServiceImpl implements CustomerService {
//	------------------------------------------neha-------------------------------------------->
	

		@Autowired
		private CustomerDao cDao;

		@Autowired
		private CustomerSessionDao csDao;

		@Override
		public Customer createCustomer(Customer customer) {
			Customer existingCustomer = cDao.findByMobileNumber(customer.getMobileNumber());
			if (existingCustomer == null) {
				return cDao.save(customer);
			} else {
				throw new CustomerException("Customer already registered");
			}

		}

		@Override
		public String customerLogin(CustomerLoginDTO customerDto) {
			Customer customer = cDao.findByMobileNumberAndPassword(customerDto.getMobileNumber(),
					customerDto.getPassword());

			if (customer != null) {
				CustomerSession cs = new CustomerSession();
				cs.setCustomerId(customer.getCustomerId());
				cs.setTimeStamp(LocalDateTime.now());
				cs.setUniqueId(RandomString.make(8));

				CustomerSession cSession = csDao.save(cs);
				return cSession.toString();

			} else {
				throw new CustomerException("Wrong credentials");
			}

		}

		@Override
		public String customerLogout(String customerId) {
			CustomerSession cSession = csDao.findByUniqueId(customerId);
			if (cSession != null) {
				csDao.delete(cSession);
				return "Logged out !";
			} else {
				throw new CustomerException("User not logged in with this number!");
			}

		}

		@Override
		public CustomerSession checkCustomerSession(String customerId) {
			CustomerSession cSession = csDao.findBycustomerId(customerId);
			if (cSession != null) {

				return cSession;
			} else {
				return null;
			}

		}

		@Override
		public Customer viewCustomerDetails(String customerId) {
			CustomerSession cSession = csDao.findBycustomerId(customerId);
			if (cSession != null) {
				Optional<Customer> opt = cDao.findById(cSession.getId());
				Customer customer = opt.get();
				return customer;
			} else {
				throw new CustomerException("User must be logged in!");
			}
		}

	
//	--------------------------------------------------------------------------------------------
}
