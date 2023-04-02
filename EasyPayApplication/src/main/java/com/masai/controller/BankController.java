package com.masai.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;

import java.util.List;

import javax.security.auth.login.AccountNotFoundException;

import java.math.BigDecimal;

import org.springframework.web.bind.annotation.*;

import com.masai.exception.CustomerException;
import com.masai.exception.CustomerNotFoundException;
import com.masai.model.BankAccount;
import com.masai.model.Customer;
import com.masai.service.BankAccountService;
import com.masai.service.CustomerService;

@RestController

public class BankController {
	

	@Autowired
    private BankAccountService bankService;

    @PostMapping("/customers/{customerId}")
    public ResponseEntity<Customer>  addAccount(@RequestBody BankAccount account, @PathVariable("customerId") String customerId, @RequestParam("key") String key) throws CustomerException {
    	Customer addAccount = bankService.addAccount(account, key);
    	return new ResponseEntity<>(addAccount,HttpStatus.CREATED);
    }

    @DeleteMapping("/customers/{customerId}/accounts/{accountId}")
    public Customer deleteAccount(@PathVariable("accountId") Integer accountId, @PathVariable("customerId") String customerId, @RequestParam("key") String key) throws CustomerException {
        return bankService.deleteAccount(accountId, key);
    }

    @GetMapping("/accounts/{accountNumber}")
    public BankAccount viewAccount(@PathVariable("accountNumber") String accountNumber, @RequestParam("key") String key) throws CustomerException, AccountNotFoundException {
        return bankService.viewAccount(accountNumber, key);
    }

    @GetMapping("/customers/{customerId}/accounts")
    public List<BankAccount> viewAllAccount(@PathVariable("customerId") Integer customerId, @RequestParam("key") String key) throws CustomerException, CustomerNotFoundException {
        return bankService.ViewAllAccount(customerId, key);
    }

    @GetMapping("/customers/{email}/balance")
    public Double showBalance(@PathVariable("email") String email,@RequestParam("key") String key) throws CustomerException {
        return bankService.showBalance(email,key);
    }

   
}
