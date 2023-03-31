package com.masai.model;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

//1.customer(id Gv,Moble string,password String, emial email,Wallet wallet (foren ket),)

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Customer {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer cId;
	private String name;
	private String password;
	private String email;

	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "wallet_Id")
	private Wallet wallet;

	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "customer_Id")
	private List<Address> address;

	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "account_Id")
	private BankAccount account;
}
