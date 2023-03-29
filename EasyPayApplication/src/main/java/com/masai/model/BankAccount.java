package com.masai.model;

import java.time.LocalDate;
import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

//BankAccount(id Gv , AccountNumber String , ifc String , Branch String , Ballence Double , BankName String , 
//Wallet wallet,Customerid int)

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class BankAccount {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer BankAccountId;
	private String AccountNumber;
	private String ifsc;
	private String Branch;
	private Double ballence;
	private String bankName;
	private Wallet wallet;
	private Customer customerId;
	
}
