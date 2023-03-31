package com.masai.service;

import java.math.BigDecimal;

import com.masai.exception.WalletException;
import com.masai.model.Customer;
import com.masai.model.Transection;
import com.masai.model.Wallet;


public interface WalletServices {
	public Customer createNewWallet(String name, String mbileNumber, BigDecimal ammount, String key) throws WalletException;
	public BigDecimal showBallence(String email, String key) throws WalletException;
	public Transection fundTrnasfer(String sourceMobileNumber, String targetMobileNumber, BigDecimal ammount, String key) throws WalletException;
	public Transection depositAmmount(String email, BigDecimal ammount, String key) throws WalletException;
	
}
