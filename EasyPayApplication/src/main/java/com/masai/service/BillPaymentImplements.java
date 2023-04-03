package com.masai.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.masai.exception.BenificiaryException;
import com.masai.exception.BillPaymentException;
import com.masai.exception.LoginException;
import com.masai.model.Benificiary;
import com.masai.model.BillPayment;
import com.masai.model.CurrentUserSession;
import com.masai.model.Customer;
import com.masai.model.Wallet;
import com.masai.repository.BillPaymentRepository;
import com.masai.repository.CustomerRepository;
import com.masai.repository.SessionRepository;
import com.masai.repository.WalletDao;

@Service
public class BillPaymentImplements implements BillPaymentDao {
	
	@Autowired
	private BillPaymentRepository repo;
	
	@Autowired
	private SessionRepository repo2;
	@Autowired
	private CustomerRepository repo3;

	@Autowired
	private WalletDao repo4;
	@Override
	public BillPayment addBillPayment(BillPayment Payment,String key) throws BillPaymentException {
		
		CurrentUserSession useHereOrNot = repo2.findByUuid(key);
		if(useHereOrNot==null) {
			throw new LoginException("please Enter a valid key");
		}
		else {
			Integer userId = useHereOrNot.getUserId();
			Customer currestuser = repo3.findById(userId).get();
			Wallet wallet = currestuser.getWallet();
			if(wallet==null) {
				
				throw new BillPaymentException("Wallet is not their");
				
				
//				Wallet w1=new Wallet();
//				w1.setAmount(w.getAmount());
//				w1.setLastUpdate(w.getLastUpdate());
//				repo4.save(w1);
//				currestuser.setWallet(w1);
//				repo3.save(currestuser);
			}
			else {
				BillPayment save = repo.save(Payment);
				wallet.getBills().add(save);
				Wallet wall = repo4.save(wallet);
				List<BillPayment> bills = wall.getBills();
				return save;
			}
			
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
			throw new BillPaymentException("Enter correct id for Benificiary....");
		}
		else {
			return checkISorNot.get();
		}
	}

}
