package com.masai.model;


import java.math.BigDecimal;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

//BankAccount(id Gv , AccountNumber String , ifc String , Branch String , Ballence Double , BankName String , 
//Wallet wallet,Customerid int)

@Entity
//@Data
//@AllArgsConstructor
//@NoArgsConstructor
public class BankAccount {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "account_Id")
    private Integer BankAccountId;
    private String AccountNumber;
    private String ifsc;
    private String Branch;
    private Double ballence;
    private String bankName;
    
    @JsonIgnore
    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "customer_Id")
    private Customer customer;

	public Integer getBankAccountId() {
		return BankAccountId;
	}

	public void setBankAccountId(Integer bankAccountId) {
		BankAccountId = bankAccountId;
	}

	public String getAccountNumber() {
		return AccountNumber;
	}

	public void setAccountNumber(String accountNumber) {
		AccountNumber = accountNumber;
	}

	public String getIfsc() {
		return ifsc;
	}

	public void setIfsc(String ifsc) {
		this.ifsc = ifsc;
	}

	public String getBranch() {
		return Branch;
	}

	public void setBranch(String branch) {
		Branch = branch;
	}

	public void getBallence(Double ballence) {
		this.ballence = ballence;
	}

	public void setBallence(Double ballence) {
		this.ballence = ballence;
	}

	public String getBankName() {
		return bankName;
	}

	public void setBankName(String bankName) {
		this.bankName = bankName;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	
	
}
