package com.masai.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.masai.model.BillPayment;
import com.masai.service.BillPaymentDao;

@RestController
public class BillPaymentContoller {

	@Autowired
	private BillPaymentDao dao;
	
	@PostMapping("/addBillPayment")
	public ResponseEntity<BillPayment> addBillPayment(@RequestBody BillPayment billpayment){
		BillPayment addBillPayment = dao.addBillPayment(billpayment);
		return new ResponseEntity<>(addBillPayment,HttpStatus.CREATED);
	}
	@PostMapping("/ViewBillPayment/{id}")
	public ResponseEntity<BillPayment> ViewBillPayment(@PathVariable Integer id){
		BillPayment viewBillPayment = dao.ViewBillPayment(id);
		return new ResponseEntity<>(viewBillPayment,HttpStatus.CREATED);
	}
	
}
