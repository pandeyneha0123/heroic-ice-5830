package com.masai.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.util.List;
import java.math.BigDecimal;

import org.springframework.web.bind.annotation.*;

import com.masai.exception.CustomerException;
import com.masai.model.BankAccount;
import com.masai.model.Customer;
import com.masai.service.CustomerService;

@RestController

public class BankController {
	
	@Autowired
    private CustomerService customerService;

    

    @PostMapping("/customers/{customerId}")
    public Customer addAccount(@RequestBody BankAccount account, @PathVariable("customerId") String customerId, @RequestParam("key") String key) throws CustomerException {
        return customerService.addAccount(account, key);
    }

    @DeleteMapping("/customers/{customerId}/accounts/{accountId}")
    public Customer deleteAccount(@PathVariable("accountId") Integer accountId, @PathVariable("customerId") String customerId, @RequestParam("key") String key) throws CustomerException {
        return customerService.deleteAccount(accountId, key);
    }

    @GetMapping("/accounts/{accountNumber}")
    public BankAccount viewAccount(@PathVariable("accountNumber") String accountNumber, @RequestParam("key") String key) throws CustomerException {
        return customerService.viewAccount(accountNumber, key);
    }

    @GetMapping("/customers/{customerId}/accounts")
    public List<BankAccount> viewAllAccount(@PathVariable("customerId") Integer customerId, @RequestParam("key") String key) throws CustomerException {
        return customerService.viewAllAccount(customerId, key);
    }

    @GetMapping("/customers/{email}/balance")
    public BigDecimal showBalance(@PathVariable("email") String email) throws CustomerException {
        return customerService.showBalance(email);
    }

   
}
