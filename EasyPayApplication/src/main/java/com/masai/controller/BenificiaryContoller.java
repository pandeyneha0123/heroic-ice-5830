package com.masai.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.masai.model.Benificiary;
import com.masai.service.BenificiaryDao;

@RestController
public class BenificiaryContoller {
	@Autowired
	private BenificiaryDao dao;
	
	@PostMapping("/addBenificiary")
	public ResponseEntity<Benificiary>  addBenificiary(@RequestBody Benificiary benificial) {
		Benificiary addBenificiary = dao.addBenificiary(benificial);
		return  new ResponseEntity<>(addBenificiary,HttpStatus.CREATED);
	}
	
	@GetMapping("/ViewBenificiary/{id}")
	public ResponseEntity<Benificiary> ViewBenificiary(@PathVariable Integer id) {
		Benificiary ViewBenificiaryByid = dao.ViewBenificiary(id);
		return new ResponseEntity<>(ViewBenificiaryByid,HttpStatus.OK) ;
	}
	
	@DeleteMapping("/deleteBenificiary/{id}")
	public ResponseEntity<Benificiary> deleteBenificiary(@PathVariable Integer id) {
		Benificiary deleteBenificiaryByid = dao.deleteBenificiary(id);
		return new ResponseEntity<>(deleteBenificiaryByid,HttpStatus.OK) ;
	}
	
	@GetMapping("/ViewAllBenificiary")
	public ResponseEntity<List<Benificiary>> ViewAllBenificiary() {
		 List<Benificiary> viewAllBenificiary = dao.ViewAllBenificiary();
		return new ResponseEntity<>(viewAllBenificiary,HttpStatus.OK);
	}
	

}