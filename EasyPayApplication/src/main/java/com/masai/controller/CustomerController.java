package com.masai.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.masai.model.Customer;
import com.masai.service.CustomerService;

import jakarta.validation.Valid;

@RestController
public class CustomerController {
	@Autowired
	private CustomerService custService;

	@PostMapping("/customer")
	public ResponseEntity<Customer> createCustomerHandler(@Valid @RequestBody Customer customer) {
		Customer newCustomer = custService.createCustomer(customer);
		return new ResponseEntity<Customer>(newCustomer, HttpStatus.ACCEPTED);
	}

	

	@GetMapping("/customer/{customerId}")
	public ResponseEntity<Customer> viewCustomerDetailsHandler(@PathVariable("customerId") String customerId) {
		Customer newCustomer = custService.viewCustomerDetails(customerId);
		return new ResponseEntity<Customer>(newCustomer, HttpStatus.ACCEPTED);

}
 }
