package com.masai.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.masai.exception.BenificiaryException;
import com.masai.exception.BillPaymentException;
import com.masai.model.BillPayment;
import com.masai.repository.BillPaymentRepository;

@Service
public class BillPaymentImplements implements BillPaymentDao {
	
	@Autowired
	private BillPaymentRepository repo;
	@Override
	public BillPayment addBillPayment(BillPayment Payment) throws BillPaymentException {
		Optional<BillPayment> checkISorNot = repo.findById(Payment.getBillPayId());
		if(!checkISorNot.isEmpty()) {
			throw new BenificiaryException("Benificiary is already their ...");
		}
		else {
			BillPayment saveBenificiary = repo.save(Payment);
			return saveBenificiary;
		}
	}

	@Override
	public BillPayment ViewBillPayment(Integer id) throws BillPaymentException {
		Optional<BillPayment> checkISorNot = repo.findById(id);
		if(checkISorNot.isEmpty()) {
			throw new BenificiaryException("Enter correct id for Benificiary....");
		}
		else {
			return checkISorNot.get();
		}
	}

}
