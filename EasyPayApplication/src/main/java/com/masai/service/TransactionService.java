package com.masai.service;

import java.util.List;

import com.masai.exception.TransactionException;
import com.masai.model.TransactionDto;
import com.masai.model.Transection;


public interface TransactionService {
	public TransactionDto addTranaction(TransactionDto tran) throws TransactionException;
	public List<Transection> viewTransactionByWalletId(Integer id) throws TransactionException;
	public List<Transection> viewTransactionByDate(String D1, String D2) throws TransactionException;
	public List<Transection> viewTransactionByType(String type) throws TransactionException;
	
}
