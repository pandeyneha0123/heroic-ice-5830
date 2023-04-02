package com.masai.service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.masai.exception.TransactionException;
import com.masai.exception.WalletException;
import com.masai.model.AddTowallet;
import com.masai.model.CurrentUserSession;
import com.masai.model.Customer;
import com.masai.model.PhoneToPhoneTransectionDto;
import com.masai.model.TransactionDto;
import com.masai.model.Transection;
import com.masai.model.Wallet;
import com.masai.model.WalletDto;
import com.masai.repository.CustomerRepository;
import com.masai.repository.SessionRepository;
import com.masai.repository.TransactionDao;
import com.masai.repository.WalletRepository;

@Service
public class WalletServicesImpl implements WalletServices {

	@Autowired
	private WalletRepository walletRepository;

	@Autowired
	private SessionRepository sessionRepository;

	@Autowired
	private CustomerRepository customerRepository;

	@Autowired
	private TransactionService transectionService;

	@Autowired
	private TransactionDao transectionRepository;
	
	@Override
	public Wallet createNewWallet(WalletDto walletDto, String key) throws WalletException {

		// verify user is loged in or not
		CurrentUserSession userLogedIn = sessionRepository.findByUuid(key);

		if (userLogedIn == null) {
			throw new WalletException("User must login first !");
		}

		// getting the cutomer details
		Optional<Customer> optional = customerRepository.findById(userLogedIn.getUserId());

		if (optional.isPresent()) {

			Customer customer = optional.get();

			// check if cutomer alrady have wallet
			if (customer.getWallet() == null) {

				// here means customer has no wallet
				Wallet newWallet = new Wallet();

				newWallet.setCustomerName(walletDto.getCustomerName());
				newWallet.setAmount(walletDto.getAmount());
				newWallet.setPhone(customer.getPhone());
				newWallet.setLastUpdate(LocalDate.now());

				// Save the wallet in db
				Wallet savedWallet = walletRepository.save(newWallet);

				// associate customer with new wallet
				customer.setWallet(savedWallet);

				// return cutomer after merging to database
				customerRepository.save(customer);
				return savedWallet;

			} else {
				throw new WalletException("Cutomer alraedy have a wallet!");
			}

		} else {
			throw new WalletException("Something went wrong please try after some time!");
		}

	}

	@Override
	public WalletDto showBallence(String phone, String key) throws WalletException {

		// verify user is loged in or not
		CurrentUserSession userLogedIn = sessionRepository.findByUuid(key);

		if (userLogedIn == null) {
			throw new WalletException("User must login first !");
		}

		// getting the cutomer details
		Optional<Customer> optional = customerRepository.findById(userLogedIn.getUserId());

		if (optional.isEmpty()) {
			throw new WalletException("Something went wrong please try after some time!");
		}

		Wallet userWallet = optional.get().getWallet();
		// check user haing wallet or not
		if (userWallet == null) {
			throw new WalletException("User having no wallet, please create a wallet First!");
		}

//		//Cross check with registered phone number and passing phone number
//		if(userWallet.getPhone() != phone) {
//			throw new WalletException("User phone nuber must be same!");
//		}

		return new WalletDto(userWallet.getCustomerName(), userWallet.getAmount());

	}

	@Override
	public Transection fundTrnasfer(PhoneToPhoneTransectionDto transferForm, String key) throws WalletException {

		// verify user is loged in or not
		CurrentUserSession userLogedIn = sessionRepository.findByUuid(key);

		if (userLogedIn == null) {
			throw new WalletException("User must login first !");
		}

		// getting the cutomer details
		Optional<Customer> sorceCustomer = customerRepository.findById(userLogedIn.getUserId());

		Optional<Wallet> souceUser = walletRepository.findByPhone(transferForm.getSourcePhone());

		Optional<Wallet> targetUser = walletRepository.findByPhone(transferForm.getTargetMobileNumber());

		if (souceUser.isEmpty()) {
			throw new WalletException("No user found with this number : " + transferForm.getSourcePhone());
		}
		if (targetUser.isEmpty()) {
			throw new WalletException("No user found with this number : " + transferForm.getTargetMobileNumber());
		}

		Wallet sourceWallet = souceUser.get();
		Wallet targetWallet = targetUser.get();

		if (sourceWallet.getAmount() < transferForm.getAmmount()) {
			throw new WalletException("Insufficent balance...!");
		}

		// transection------------>
		sourceWallet.setAmount(sourceWallet.getAmount() - transferForm.getAmmount());

		targetWallet.setAmount(targetWallet.getAmount() + transferForm.getAmmount());
		// update ballence-------->
		walletRepository.save(sourceWallet);
		walletRepository.save(targetWallet);

		// transection succesfull----->

		// transection recipet
		Transection t1 = null;
		Transection t2 = null;
		try {
			t1 = transectionService.transectionByPhone(new TransactionDto(transferForm.getAmmount(),
					transferForm.getDescription(), "Debit", sourceWallet.getWalletId()));
			transectionService.transectionByPhone(new TransactionDto(transferForm.getAmmount(),
					transferForm.getDescription(), "Credit", targetWallet.getWalletId()));
		} catch (TransactionException e) {

		}

		return t1;
	}

	@Override
	public Transection depositAmmount(AddTowallet addForm, String key) throws WalletException {

		// verify user is loged in or not
		CurrentUserSession userLogedIn = sessionRepository.findByUuid(key);

		if (userLogedIn == null) {
			throw new WalletException("User must login first !");
		}
		
		Optional<Wallet> targetWallet = walletRepository.findByPhone(addForm.getPhone());
		
		if(targetWallet.isEmpty()) {
			throw new WalletException("User has no active wallet...");
		}
		
		 Wallet wallet = targetWallet.get();
		 wallet.setAmount(wallet.getAmount() + addForm.getAmmount());
		 
		 Transection trans1 = transectionRepository.save(new Transection(0, "Credit", LocalDate.now(),  addForm.getAmmount(),  addForm.getDescrpition(), wallet));
		 
		 wallet.getTransections().add(trans1);
		 
		 walletRepository.save(wallet);

		return trans1;
	}
	
	@Override
	public List<Wallet> getAllWallets(String key) throws WalletException {

		// verify user is loged in or not
		CurrentUserSession userLogedIn = sessionRepository.findByUuid(key);

		if (userLogedIn == null) {
			throw new WalletException("User must login first !");
		}
		
		if(!userLogedIn.getRole().equalsIgnoreCase("Admin")) {
			throw new WalletException("**Restricted service Only Admins Allowd !");
		}
		
		List<Wallet> targetWallets = walletRepository.findAll();
		
		if(targetWallets.isEmpty()) {
			throw new WalletException("No wallets  found...");
		}
			 

		return targetWallets;
	}

}
