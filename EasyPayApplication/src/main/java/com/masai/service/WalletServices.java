package com.masai.service;

import java.math.BigDecimal;
import java.util.List;

import com.masai.exception.WalletException;
import com.masai.model.AddTowallet;
import com.masai.model.Customer;
import com.masai.model.PhoneToPhoneTransectionDto;
import com.masai.model.Transection;
import com.masai.model.Wallet;
import com.masai.model.WalletDto;


public interface WalletServices {

	public Wallet createNewWallet(WalletDto walletDto, String key) throws WalletException;
	public WalletDto showBallence(String phone, String key) throws WalletException;
	public Transection fundTrnasfer(PhoneToPhoneTransectionDto transferForm, String key) throws WalletException;
	public Transection depositAmmount(AddTowallet add2Wallet, String key) throws WalletException;
	public List<Wallet>  getAllWallets(String key) throws WalletException;
	
}
