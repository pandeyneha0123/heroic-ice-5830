package com.masai.service;

import java.util.List;

import com.masai.exception.BenificiaryException;
import com.masai.model.Benificiary;

public interface BenificiaryDao {
	public Benificiary addBenificiary(Benificiary benificiary) throws BenificiaryException;
	public Benificiary deleteBenificiary(Integer BenificiaryId) throws BenificiaryException;
	public Benificiary ViewBenificiary(Integer BenificiaryId) throws BenificiaryException;
	public List<Benificiary> ViewAllBenificiary() throws BenificiaryException;

}
