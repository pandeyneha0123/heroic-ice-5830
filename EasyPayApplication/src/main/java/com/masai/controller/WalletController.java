package com.masai.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.masai.exception.WalletException;
import com.masai.model.Customer;
import com.masai.model.PhoneToPhoneTransectionDto;
import com.masai.model.Transection;
import com.masai.model.WalletDto;
import com.masai.service.WalletServices;

@RestController
@RequestMapping("/ePay")
public class WalletController {
	
	@Autowired
	private WalletServices walletServices;
	
	@PostMapping("/wallets")
	public ResponseEntity<Customer> createNewWalletHandler(@RequestBody WalletDto walletDto, @RequestParam String key) throws WalletException {
		
		Customer resposeCustomer =	walletServices.createNewWallet(walletDto, key);
		
		return new ResponseEntity<>(resposeCustomer, HttpStatus.CREATED);
	}
	
	@GetMapping("/customers/wallets")
	public ResponseEntity<WalletDto> showBallenceHandler(@RequestParam String phone, String key) throws WalletException {
		
		WalletDto respnoseWalletDto = walletServices.showBallence(phone, key);
		
		return new ResponseEntity<WalletDto>(respnoseWalletDto,HttpStatus.OK);
	}
	
	@PostMapping("/wallets/{id}")
	public ResponseEntity<Transection> fundTransferHandler(@RequestBody PhoneToPhoneTransectionDto p2pDto, @RequestParam String key) throws WalletException {
		
		Transection resposeTransection =	walletServices.fundTrnasfer(p2pDto, key);
		
		return new ResponseEntity<>(resposeTransection, HttpStatus.CREATED);
	}
}
