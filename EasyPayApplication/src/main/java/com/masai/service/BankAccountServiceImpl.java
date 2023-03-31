package com.masai.service;

import java.math.BigDecimal;
import java.util.Optional;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.masai.exception.CustomerException;
import com.masai.model.BankAccount;
import com.masai.model.CurrentUserSession;
import com.masai.model.Customer;
import com.masai.model.Wallet;
import com.masai.repository.AccountDao;
import com.masai.repository.CustomerDao;
import com.masai.repository.SessionRepository;
import com.masai.repository.WalletDao;

@Service
public class BankAccountServiceImpl implements BankAccountService {

	@Autowired
	private CustomerService csDao;

	@Autowired
	private CustomerDao cDao;

	@Autowired
	private AccountDao bDao;

	@Autowired
	private WalletDao wDao;
	
	@Autowired
	SessionRepository session;
	

	@Override
	public Customer addAccount(BankAccount Account, String key) throws CustomerException {


		CurrentUserSession responseSession = session.findByUuid(key);

		if (responseSession != null) {
			Optional<Customer> opt = cDao.findById(responseSession.getUserId());
			
			Customer customer = opt.get();

			List<BankAccount> banks = customer.getWallet().getBanks();

			boolean flag = false;
			for (BankAccount bank : banks) {
				if (bank.getAccountNumber().equals(Account.getAccountNumber())) {
					flag = true;
				}
			}

			// return desired bank account
			if (!flag) {
				// add bank account
				customer.getWallet().getBanks().add(Account);
				cDao.save(customer);
				return customer;
			} else {
				throw new CustomerException("Bank Account alread exist");
			}

		} else {
			throw new CustomerException("Customer not logged in");
		}

	}

	@Override
	public Customer deleteAccount(Integer accountId, String key) throws CustomerException{
		
		CurrentUserSession responseSession = session.findByUuid(key);

		if (responseSession != null) {
			
			Optional<BankAccount> opt = bDao.findById(accountId);
			
			if (opt.isPresent()) {
				bDao.delete(opt.get());
				Optional<Customer> optc = cDao.findById(responseSession.getUserId());
				Customer customer = optc.get();
				return customer;
			} else {
				throw new CustomerException("Wrong bank id....");
			}

		} else {
			throw new CustomerException("Customer not logged in....");
		}

	}

	@Override
	public BankAccount ViewAccount(String accountNo, String key) throws CustomerException{
		
		CurrentUserSession responseSession = session.findByUuid(key);
		BankAccount bankAccount = null;

		if (responseSession != null) {
			Optional<Customer> opt = cDao.findById(responseSession.getUserId());
			Customer customer = opt.get();

			List<BankAccount> banks = customer.getWallet().getBanks();

			for (BankAccount bank : banks) {
				if (bank.getAccountNumber().equals(accountNo)) {
					bankAccount = bank;
				}
			}

			// return desired bank account
			if (bankAccount != null) {
				return bankAccount;
			} else {
				throw new CustomerException("No bank account available with this account number");
			}

		} else {
			throw new CustomerException("Customer not logged in");
		}
	}

	@Override
	public List<BankAccount> ViewAllAccount(Integer wid, String key) throws CustomerException{
		
		CurrentUserSession responseSession = session.findByUuid(key);
		
		if (responseSession != null) {
			Optional<Wallet> opt = wDao.findById(wid);
			if (opt.isPresent()) {
				List<BankAccount>  banks = opt.get().getBanks();
				return banks;
			} else {
				throw new CustomerException("Wronge wallet id");
			}
		} else {
			throw new CustomerException("Customer not logged in");
		}

	}

	@Override
	public BigDecimal showBalance(String email) throws CustomerException {
	    
		// Retrieve the balance of the customer with the given email from the database
	    BigDecimal balance = session.getBalanceByEmail(email);
	    
	 // If the balance is null or negative, throw an exception
	    if (balance == null || balance.compareTo(BigDecimal.ZERO) < 0) {
	        throw new CustomerException("Invalid balance");
	    }
	    
	    // Return the balance
	    return balance;
	}


	
}
