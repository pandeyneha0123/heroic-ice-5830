package com.masai.service;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.masai.exception.TransactionException;
import com.masai.model.TransactionDto;
import com.masai.model.Transection;
import com.masai.model.Wallet;
import com.masai.repository.TransactionDao;
import com.masai.repository.WalletDao;

@Service
public class TransactionServiceImpl implements TransactionService {
	
	@Autowired
	private TransactionDao tra; 
	@Autowired
	private WalletDao wd;

	@Override
	public TransactionDto addTranaction(TransactionDto tran) throws TransactionException {
		if(tran == null) {
			throw new TransactionException("transaction detail incorrect");
		}
		Wallet existingWallet = wd.findById(tran.getWalletId()).get();
		if(tran.getWalletId() != existingWallet.getWalletId()) {
			throw new TransactionException("Invalid Wallet id");
		}
		if(existingWallet.getAmount() < tran.getAmmount()) {
			throw new TransactionException("Insufficient balance");
		}
		existingWallet.setAmount(existingWallet.getAmount() - tran.getAmmount());
		
		Transection t1 = new Transection();
		t1.setAmmount(tran.getAmmount());
		t1.setDescription(tran.getDescription());
		t1.setTransectionType(tran.getTransectionType());
		t1.setWallet(existingWallet);
		existingWallet.getTransections().add(t1);
		wd.save(existingWallet);
		return tran;
	}

	@Override
	public List<Transection> viewTransactionByWalletId(Integer id) throws TransactionException {
		List<Transection> l1 = tra.findByWalletId(id);
		if(l1.size() == 0) {
			throw new TransactionException("No transaction done by this wallet id");
		}
		return l1;
	}

	@Override
	public List<Transection> viewTransactionByDate(String D1, String D2) throws TransactionException {
		LocalDate l1 = LocalDate.parse(D1);
		LocalDate l2 = LocalDate.parse(D2);
		List<Transection> list = tra.findTransactionByDate(l1, l2);
		if(list.size() == 0) {
			throw new TransactionException("No transaction done between these date");
		}
		return list;
	}

	@Override
	public List<Transection> viewTransactionByType(String type) throws TransactionException {
		List<Transection> list = tra.findByTransectionType(type);
		if(list.size() == 0) {
			throw new TransactionException("No transaction done....");
		}
		return list;
	}

}
