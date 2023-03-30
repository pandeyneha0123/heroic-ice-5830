package com.masai.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.masai.exception.TransactionException;
import com.masai.model.TransactionDto;
import com.masai.model.Transection;
import com.masai.service.TransactionService;

@RestController
public class TransectionController {
	
	@Autowired
	private TransactionService tas;
	
	@PostMapping("/transaction")
	public ResponseEntity<TransactionDto> addTransaction(@RequestBody TransactionDto tdto) throws TransactionException{
		TransactionDto td1 = tas.addTranaction(tdto);
		return new ResponseEntity<TransactionDto>(td1, HttpStatus.ACCEPTED);
	}
	
	@GetMapping("/transaction/{id}")
	public ResponseEntity<List<Transection>> viewTransactionByWalletId(@PathVariable("id")Integer walletId) throws TransactionException{
		List<Transection> l1 = tas.viewTransactionByWalletId(walletId);
		return new ResponseEntity<>(l1, HttpStatus.OK);
	}
	
	@GetMapping("/transaction/{d1}/{d2}")
	public ResponseEntity<List<Transection>> viewTransactionByDate(@PathVariable("d1")String d1, @PathVariable("d2")String d2) throws TransactionException{
		List<Transection> l1 = tas.viewTransactionByDate(d1, d2);
		return new ResponseEntity<>(l1, HttpStatus.OK);
	}
	@GetMapping("/transactions/{type}")
	public ResponseEntity<List<Transection>> viewTransactionByType(@PathVariable("type")String type) throws TransactionException{
		List<Transection> l1 = tas.viewTransactionByType(type);
		return new ResponseEntity<>(l1, HttpStatus.OK);
	}


}
