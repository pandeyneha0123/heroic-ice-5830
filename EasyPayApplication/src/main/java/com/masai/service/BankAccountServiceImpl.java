package com.masai.service;

import java.util.Optional;

import org.hibernate.mapping.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.masai.exception.CustomerException;
import com.masai.model.BankAccount;
import com.masai.model.Customer;
import com.masai.model.Wallet;
import com.masai.repository.AccountDao;
import com.masai.repository.CustomerDao;
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

	@Override
	public Customer addAccount(BankAccount Account, String CustomerId) {


		CustomerSession session = csDao.checkCustomerSession(CustomerId);

		if (session != null) {
			Optional<Customer> opt = cDao.findById(session.getId());
			Customer customer = opt.get();

			List<BankAccount> banks = customer.getWallet().getBanks();

			boolean flag = false;
			for (BankAccount bank : banks) {
				if (bank.getAccountNo().equals(Account.getAccountNo())) {
					flag = true;
				}
			}

			// return desired bank account
			if (!flag) {
				// associate wallet
				Account.setWallet(customer.getWallet());
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
	public Customer deleteAccount(Integer accountId, String customerId) {
		CustomerSession session = csDao.checkCustomerSession(customerId);

		if (session != null) {
			Optional<BankAccount> opt = bDao.findById(accountId);
			if (opt.isPresent()) {
				bDao.delete(opt.get());
				Optional<Customer> optc = cDao.findById(session.getId());
				Customer customer = optc.get();
				return customer;
			} else {
				throw new CustomerException("Wrong bank id");
			}

		} else {
			throw new CustomerException("Customer not logged in");
		}

	}

	@Override
	public BankAccount ViewAccount(String accountNo, String customerId) {
		CustomerSession session = csDao.checkCustomerSession(customerId);
		BankAccount bankAccount = null;

		if (session != null) {
			Optional<Customer> opt = cDao.findById(session.getId());
			Customer customer = opt.get();

			List<BankAccount> banks = customer.getWallet().getBanks();

			for (BankAccount bank : banks) {
				if (bank.getAccountNo().equals(accountNo)) {
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
	public List<BankAccount> ViewAllAccount(Integer wid, String customerId) {
		CustomerCurrentSession session = csDao.checkCustomerSession(customerIdId);
		if (session != null) {
			Optional<Wallet> opt = wDao.findById(wid);
			if (opt.isPresent()) {
				List banks = opt.get().getBanks();
				return banks;
			} else {
				throw new CustomerException("Wronge wallet id");
			}
		} else {
			throw new CustomerException("Customer not logged in");
		}

	}

	@Override
	public Customer showBalance(String mobileNo) {
		Customer customer = cDao.findByMobileNumber(mobileNo);
		if (customer != null) {
			
			return customer;
		} else {
			throw new CustomerException("No customer found with mobile number " + mobileNo);
		}
	}
}
