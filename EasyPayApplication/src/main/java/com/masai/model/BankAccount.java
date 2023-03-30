package com.masai.model;


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
@Data
@AllArgsConstructor
@NoArgsConstructor
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

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "customer_Id")
    private Customer customer;
	
}
