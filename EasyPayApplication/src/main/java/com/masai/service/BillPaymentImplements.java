package com.masai.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.masai.exception.BenificiaryException;
import com.masai.exception.BillPaymentException;
import com.masai.exception.LoginException;
import com.masai.model.BillPayment;
import com.masai.model.CurrentUserSession;
import com.masai.repository.BillPaymentRepository;
import com.masai.repository.SessionRepository;

@Service
public class BillPaymentImplements implements BillPaymentDao {
	
	@Autowired
	private BillPaymentRepository repo;
	
	@Autowired
	private SessionRepository repo2;
	
	@Override
	public BillPayment addBillPayment(BillPayment Payment,String key) throws BillPaymentException {
		
		CurrentUserSession useHereOrNot = repo2.findByUuid(key);
		if(useHereOrNot==null) {
			throw new LoginException("please Enter a valid key");
		}
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
	public BillPayment ViewBillPayment(Integer id,String key) throws BillPaymentException {
		CurrentUserSession useHereOrNot = repo2.findByUuid(key);
		if(useHereOrNot==null) {
			throw new LoginException("please Enter a valid key");
		}
		Optional<BillPayment> checkISorNot = repo.findById(id);
		if(checkISorNot.isEmpty()) {
			throw new BenificiaryException("Enter correct id for Benificiary....");
		}
		else {
			return checkISorNot.get();
		}
	}

}
