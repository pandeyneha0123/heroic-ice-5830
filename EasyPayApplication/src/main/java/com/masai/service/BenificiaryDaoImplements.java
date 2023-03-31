package com.masai.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.masai.exception.BenificiaryException;
import com.masai.exception.LoginException;
import com.masai.model.Benificiary;
import com.masai.model.CurrentUserSession;
import com.masai.repository.BenificiaryRepository;
import com.masai.repository.SessionRepository;

@Service
public class BenificiaryDaoImplements implements BenificiaryDao {

	@Autowired
	private BenificiaryRepository repo;
	@Autowired
	private SessionRepository repo2;
	

	@Override
	public Benificiary addBenificiary(Benificiary benificiary,String key) throws BenificiaryException {
		CurrentUserSession useHereOrNot = repo2.findByUuid(key);
		if(useHereOrNot==null) {
			throw new LoginException("please Enter a valid key");
		}
		Optional<Benificiary> checkISorNot = repo.findById(benificiary.getBenificialId());
		if(!checkISorNot.isEmpty()) {
			throw new BenificiaryException("Benificiary is already their ...");
		}
		else {
			Benificiary saveBenificiary = repo.save(benificiary);
			return saveBenificiary;
		}
	}

	@Override
	public Benificiary deleteBenificiary(Integer BenificiaryId,String key) throws BenificiaryException {
		CurrentUserSession useHereOrNot = repo2.findByUuid(key);
		if(useHereOrNot==null) {
			throw new LoginException("please Enter a valid key");
		}
		Optional<Benificiary> checkISorNot = repo.findById(BenificiaryId);
		if(checkISorNot.isEmpty()) {
			throw new BenificiaryException("Enter the correct Benificiary Id");
		}
		else {
			 Benificiary benificiary = checkISorNot.get();
			 repo.delete(benificiary);
			 return benificiary;
		}
	
	}

	@Override
	public Benificiary ViewBenificiary(Integer BenificiaryId,String key) throws BenificiaryException {
		CurrentUserSession useHereOrNot = repo2.findByUuid(key);
		if(useHereOrNot==null) {
			throw new LoginException("please Enter a valid key");
		}
		Optional<Benificiary> checkISorNot = repo.findById(BenificiaryId);
		if(checkISorNot.isEmpty()) {
			throw new BenificiaryException("Enter the correct Benificiary Id");
		}
		else {
			 Benificiary benificiary = checkISorNot.get();
			 return benificiary;
		}
	}

	@Override
	public List<Benificiary> ViewAllBenificiary(String key) throws BenificiaryException {
		CurrentUserSession useHereOrNot = repo2.findByUuid(key);
		if(useHereOrNot==null) {
			throw new LoginException("please Enter a valid key");
		}
		 List<Benificiary> AllBenificiary = repo.findAll();
		if(AllBenificiary.isEmpty()) {
			throw new BenificiaryException("No Any Benificiary Detail here....");
		}
		else {
			return AllBenificiary;
		}
		
	}

}
