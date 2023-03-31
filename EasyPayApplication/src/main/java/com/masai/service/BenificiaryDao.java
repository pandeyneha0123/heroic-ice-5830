package com.masai.service;

import java.util.List;

import com.masai.exception.BenificiaryException;
import com.masai.model.Benificiary;

public interface BenificiaryDao {
	public Benificiary addBenificiary(Benificiary benificiary,String key) throws BenificiaryException;
	public Benificiary deleteBenificiary(Integer BenificiaryId,String key) throws BenificiaryException;
	public Benificiary ViewBenificiary(Integer BenificiaryId,String key) throws BenificiaryException;
	public List<Benificiary> ViewAllBenificiary(String key) throws BenificiaryException;

}
