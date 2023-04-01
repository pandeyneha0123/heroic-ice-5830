package com.masai.service;

import java.time.LocalDate;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.masai.exception.WalletException;
import com.masai.model.CurrentUserSession;
import com.masai.model.Customer;
import com.masai.model.Transection;
import com.masai.model.Wallet;
import com.masai.model.WalletDto;
import com.masai.repository.CustomerRepository;
import com.masai.repository.SessionRepository;
import com.masai.repository.WalletRepository;

@Service
public class WalletServicesImpl implements WalletServices {
	
	@Autowired
	private WalletRepository walletRepository;
	
	@Autowired
	private SessionRepository sessionRepository;
	
	@Autowired
	private CustomerRepository customerRepository;
	
	@Override
	public Customer createNewWallet(WalletDto walletDto, String key)
			throws WalletException {
			
			//verify user is loged in or not
			CurrentUserSession userLogedIn = sessionRepository.findByUuid(key);
			
			if(userLogedIn == null) {
				throw new WalletException("User must login first !");
			}
			
			// getting the cutomer details
			Optional<Customer> optional = customerRepository.findById(userLogedIn.getUserId());
			
			
			if(optional.isPresent()) {
				
				Customer customer = optional.get();
				
				//check if cutomer alrady have wallet
				if(customer.getWallet() == null) {
					
					// here means customer has no wallet
					Wallet newWallet = new Wallet();
					
					newWallet.setCustomerName(walletDto.getCustomerName());
					newWallet.setAmount(walletDto.getAmount());
					newWallet.setPhone(customer.getPhone());
					newWallet.setLastUpdate(LocalDate.now());
					
					//Save the wallet in db
					Wallet savedWallet = walletRepository.save(newWallet);
					
					//associate customer with new wallet
					customer.setWallet(savedWallet);
					
					//return cutomer after merging to database
					return customerRepository.save(customer);
					
				}else {
					throw new WalletException("Cutomer alraedy have a wallet!");
				}
				
			}else {
				throw new WalletException("Something went wrong please try after some time!");
			}
			
	}

	@Override
	public WalletDto showBallence(String phone, String key) throws WalletException {
		
		//verify user is loged in or not
		CurrentUserSession userLogedIn = sessionRepository.findByUuid(key);
		
		if(userLogedIn == null) {
			throw new WalletException("User must login first !");
		}
		
		// getting the cutomer details
		Optional<Customer> optional = customerRepository.findById(userLogedIn.getUserId());
		
		if(optional.isEmpty()) {
			throw new WalletException("Something went wrong please try after some time!");
		}
		
		Wallet userWallet = optional.get().getWallet();
		//check user haing wallet or not
		if(userWallet == null) {
			throw new WalletException("User having no wallet, please create a wallet First!");
		}
		
		//Cross check with registered phone number and passing phone number
		if(userWallet.getPhone() != phone) {
			throw new WalletException("User phone nuber must be same!");
		}
		
		
		return new WalletDto(userWallet.getCustomerName(),userWallet.getAmount());
		
	}

	@Override
	public Transection fundTrnasfer(String sourceMobileNumber, String targetMobileNumber, Double ammount,
			String key) throws WalletException {
		
		return null;
	}

	@Override
	public Transection depositAmmount(String email, Double ammount, String key) throws WalletException {
		
		return null;
	}


}
