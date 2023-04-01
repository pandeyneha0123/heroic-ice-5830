package com.masai.service;

import java.util.List;

import com.masai.exception.TransactionException;
import com.masai.model.TransactionDto;
import com.masai.model.Transection;

import jakarta.transaction.Transaction;


public interface TransactionService {
	public TransactionDto addTranaction(TransactionDto tran, String key) throws TransactionException;
	public List<Transection> viewTransactionByWalletId(Integer id, String key) throws TransactionException;
	public List<Transection> viewTransactionByDate(String D1, String D2, String key) throws TransactionException;
	public List<Transection> viewTransactionByType(String type, String key) throws TransactionException;
	public Transection transectionByPhone(TransactionDto tran) throws TransactionException;
	
}
