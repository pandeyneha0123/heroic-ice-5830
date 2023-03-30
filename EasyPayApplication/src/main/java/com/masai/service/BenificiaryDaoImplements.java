package com.masai.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.masai.exception.BenificiaryException;
import com.masai.model.Benificiary;
import com.masai.repository.BenificiaryRepository;

@Service
public class BenificiaryDaoImplements implements BenificiaryDao {

	@Autowired

	private BenificiaryRepository repo;

	@Override
	public Benificiary addBenificiary(Benificiary benificiary) throws BenificiaryException {
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
	public Benificiary deleteBenificiary(Integer BenificiaryId) throws BenificiaryException {
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
	public Benificiary ViewBenificiary(Integer BenificiaryId) throws BenificiaryException {
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
	public List<Benificiary> ViewAllBenificiary() throws BenificiaryException {
		 List<Benificiary> AllBenificiary = repo.findAll();
		if(AllBenificiary.isEmpty()) {
			throw new BenificiaryException("No Any Benificiary Detail here....");
		}
		else {
			return AllBenificiary;
		}
		
	}

}
