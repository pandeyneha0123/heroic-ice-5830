package com.masai.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.masai.exception.TransactionException;
import com.masai.exception.WalletException;
import com.masai.model.CurrentUserSession;
import com.masai.model.TransactionDto;
import com.masai.model.Transection;
import com.masai.model.Wallet;
import com.masai.repository.CustomerRepository;
import com.masai.repository.SessionRepository;
import com.masai.repository.TransactionDao;
import com.masai.repository.WalletRepository;

@Service
public class TransactionServiceImpl implements TransactionService {

	@Autowired
	private TransactionDao tra;
	@Autowired
	private WalletRepository wd;
	@Autowired
	private SessionRepository sessionRepository;
	@Autowired
	private CustomerRepository customerRepository;

	@Override
	public TransactionDto addTranaction(TransactionDto tran, String key) throws TransactionException {

		if (tran == null) {
			throw new TransactionException("transaction detail incorrect");
		}

		Wallet existingWallet = wd.findById(tran.getWalletId()).get();
		if (tran.getWalletId() != existingWallet.getWalletId()) {
			throw new TransactionException("Invalid Wallet id");
		}
		if (existingWallet.getAmount() < tran.getAmmount()) {
			throw new TransactionException("Insufficient balance");
		}
		existingWallet.setAmount(existingWallet.getAmount() - tran.getAmmount());

		Transection t1 = new Transection();
		t1.setAmmount(tran.getAmmount());
		t1.setDescription(tran.getDescription());
		t1.setTransectionType(tran.getTransectionType());
		t1.setWalletId(existingWallet);
		existingWallet.getTransections().add(t1);
		wd.save(existingWallet);
		return tran;
	}

	@Override
	public Transection transectionByPhone(TransactionDto tran) throws TransactionException {

		Wallet existingWallet = wd.findById(tran.getWalletId()).get();

		Transection t1 = new Transection();
		t1.setAmmount(tran.getAmmount());
		t1.setDescription(tran.getDescription());
		t1.setTransectionType(tran.getTransectionType());

		t1.setWalletId(existingWallet);
		existingWallet.getTransections().add(t1);

		t1 = tra.save(t1);
		existingWallet = wd.save(existingWallet);

		return t1;
	}

	@Override
	public List<Transection> viewTransactionByWalletId(Integer id, String key) throws TransactionException {

		// verify user is loged in or not
		CurrentUserSession userLogedIn = sessionRepository.findByUuid(key);

		if (userLogedIn == null) {
			throw new WalletException("User must login first !");
		}

		Optional<Wallet> opt = wd.findById(id);

		if (opt.isEmpty()) {
			throw new TransactionException("No wallet found with this id...");
		}

		if (opt.get().getTransections().isEmpty()) {
			throw new TransactionException("No transaction done by this wallet id");
		}
		return opt.get().getTransections();
	}

	@Override
	public List<Transection> viewTransactionByDate(String D1, String D2, String key) throws TransactionException {
		// verify user is loged in or not
		CurrentUserSession userLogedIn = sessionRepository.findByUuid(key);

		if (userLogedIn == null) {
			throw new WalletException("User must login first !");
		}

		LocalDate l1 = LocalDate.parse(D1);
		LocalDate l2 = LocalDate.parse(D2);
		List<Transection> list = new ArrayList<>();
		Optional<Wallet> opt = wd
				.findById(customerRepository.findById(userLogedIn.getUserId()).get().getWallet().getWalletId());

		if (opt.isEmpty()) {
			throw new TransactionException("No wallet found with this id...");
		}

		for (Transection tran : opt.get().getTransections()) {
			if (tran.getTransactionDate().isAfter(l1) && tran.getTransactionDate().isBefore(l2)) {
				list.add(tran);
			}
		}

		if (list.size() == 0) {
			throw new TransactionException("No transaction done between these date");
		}
		return list;
	}

	@Override
	public List<Transection> viewTransactionByType(String type, String key) throws TransactionException {
		// verify user is loged in or not
		CurrentUserSession userLogedIn = sessionRepository.findByUuid(key);

		if (userLogedIn == null) {
			throw new WalletException("User must login first !");
		}
		List<Transection> list = new ArrayList<>();
		Optional<Wallet> opt = wd
				.findById(customerRepository.findById(userLogedIn.getUserId()).get().getWallet().getWalletId());

		if (opt.isEmpty()) {
			throw new TransactionException("No wallet found with this id...");
		}

		for (Transection tran : opt.get().getTransections()) {
			if (tran.getTransectionType().equalsIgnoreCase(type)) {
				list.add(tran);
			}
		}

		if (list.size() == 0) {
			throw new TransactionException("No transaction found by this type " + type);
		}
		return list;
	}

}
