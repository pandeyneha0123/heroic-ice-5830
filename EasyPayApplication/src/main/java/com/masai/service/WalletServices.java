package com.masai.service;

import java.math.BigDecimal;

import com.masai.exception.WalletException;
import com.masai.model.Customer;
import com.masai.model.Transection;
import com.masai.model.Wallet;
import com.masai.model.WalletDto;


public interface WalletServices {
	public Customer createNewWallet(WalletDto walletDto, String key) throws WalletException;
	public WalletDto showBallence(String phone, String key) throws WalletException;
	public Transection fundTrnasfer(String sourceMobileNumber, String targetMobileNumber, Double ammount, String key) throws WalletException;
	public Transection depositAmmount(String phone, Double ammount, String key) throws WalletException;
	
}
