package com.masai.model;

import java.util.List;

import org.hibernate.validator.constraints.Length;

import com.fasterxml.jackson.annotation.JsonIgnore;

import io.micrometer.common.lang.NonNull;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
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
	@NonNull
	@NotEmpty
	@Length(min = 3,max = 15,message = "name length shoud be min 3 char and max of 15 char")
	private String name;
	@Pattern(regexp = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&-+=()])(?=\\\\S+$).{8, 20}$",message = "password shoud be weel defiend...")
	private String password;
	@NonNull
	@Column(unique = true)
	@Email(message = "email shoud be valid...")
	private String email;
	@NonNull
	@Column(unique = true)
	@Pattern(regexp = "[89]{1}[0-9]", message = "number shoud be 10-digit and number starting with either 8 or 9")
	private String phone;
	
	@JsonIgnore
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "wallet_Id")
	private Wallet wallet;
	
	@JsonIgnore
	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "customer_Id")
	private List<Address> address;
	
	@JsonIgnore
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "account_Id")
	private BankAccount account;

}
