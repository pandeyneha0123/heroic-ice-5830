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
import com.masai.service.CustomerService;

@RestController

public class BankController {
	
	@Autowired
    private CustomerService customerService;

    

    @PostMapping("/customers/{customerId}")
    public ResponseEntity<Customer>  addAccount(@RequestBody BankAccount account, @PathVariable("customerId") String customerId, @RequestParam("key") String key) throws CustomerException {
    	Customer addAccount = customerService.addAccount(account, key);
    	return new ResponseEntity<>(addAccount,HttpStatus.CREATED);
    }

    @DeleteMapping("/customers/{customerId}/accounts/{accountId}")
    public ResponseEntity<Customer> deleteAccount(@RequestBody BankAccount account,@PathVariable("accountId") Integer accountId, @PathVariable("customerId") String customerId, @RequestParam("key") String key) throws CustomerException {
    	Customer deleteAccount=customerService.deleteAccount(accountId, key);
    	return new ResponseEntity<>(deleteAccount,HttpStatus.CREATED);
    }

    

    @GetMapping("/{customerId}/accounts")
    public ResponseEntity<List<BankAccount>> viewAllAccounts(@RequestBody BankAccount account,@PathVariable Integer customerId,
                                                              @RequestParam String key) {
        try {
            List<BankAccount> accounts = customerService.viewAllAccount(customerId, key);
            return ResponseEntity.ok(accounts);
        } catch (CustomerNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    @GetMapping("/{email}/balance")
    public ResponseEntity<Double> showBalance(@RequestBody BankAccount account,@PathVariable String email,
                                               @RequestParam String key) {
        try {
            Double balance = customerService.showBalance(email, key);
            return ResponseEntity.ok(balance);
        } catch (CustomerException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
    }
}
