package com.masai.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.masai.exception.CustomerException;
import com.masai.model.Customer;
import com.masai.service.CustomerService;


@RestController
public class CustomerController {
	
	@Autowired
	private CustomerService cService;
	
	
	
	@PostMapping("/customers")
	public ResponseEntity<Customer> addNewCustomerHandler(@RequestBody Customer customer) throws CustomerException {
		
		Customer savedCustomer = cService.createCustomer(customer);
		
		return new ResponseEntity<>(savedCustomer, HttpStatus.CREATED);
		
	}
	
	@PatchMapping("/customers/")
	public ResponseEntity<Customer> updateCustomerHandler(@RequestBody Customer customer, @RequestParam String key) throws CustomerException {
		
		Customer savedCustomer = cService.updateCustomer(customer, key);
		
		return new ResponseEntity<>(savedCustomer, HttpStatus.ACCEPTED);
		
	}
	
	@GetMapping("/customers/{Id}")
	public ResponseEntity<Customer> showCustomerHandler(@PathVariable(value = "Id") Integer id, @RequestParam String key) throws CustomerException {
		
		Customer savedCustomer = cService.viewCustomerDetails(id, key);
		
		return new ResponseEntity<>(savedCustomer, HttpStatus.CREATED);
		
	}
}

