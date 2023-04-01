package com.masai.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.masai.model.BillPayment;
import com.masai.model.Wallet;
import com.masai.service.BillPaymentDao;

@RestController
public class BillPaymentContoller {

	@Autowired
	private BillPaymentDao dao;
	
	@PostMapping("/addBillPayment")
	public ResponseEntity<BillPayment> addBillPayment(@RequestBody BillPayment billpayment,@RequestParam("key") String key,@RequestBody Wallet w){
		BillPayment addBillPayment = dao.addBillPayment(billpayment,key,w);
		return new ResponseEntity<>(addBillPayment,HttpStatus.CREATED);
	}
	@PostMapping("/ViewBillPayment/{id}")
	public ResponseEntity<BillPayment> ViewBillPayment(@PathVariable Integer id,@RequestParam("key") String key){
		BillPayment viewBillPayment = dao.ViewBillPayment(id,key);
		return new ResponseEntity<>(viewBillPayment,HttpStatus.CREATED);
	}
	
	
}
//1. for getting json documentation: /v3/api-docs/
//2. for getting ui documentation: /swagger-ui.html