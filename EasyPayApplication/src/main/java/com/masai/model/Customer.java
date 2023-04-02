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
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

//1.customer(id Gv,Moble string,password String, emial email,Wallet wallet (foren ket),)

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@Entity
public class Customer {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer cId;
	@NonNull
	@NotEmpty
	@Length(min = 3,max = 24,message = "name length shoud be min 3 char and max of 24 char")
	private String name;
	@Pattern(regexp = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@$!%*?&])([a-zA-Z0-9@$!%*?&]{8,})$",message = "password shoud be weel defiend...")
	private String password;
	@NonNull
	@Column(unique = true)
	@Email(message = "email shoud be valid...")
	private String email;
	@NonNull
	@Column(unique = true)
	@Pattern(regexp = "^[6-9]\\d{9}$", message = "number shoud be 10-digit and number starting with either 6/7/8/9")
	private String phone;
	private String role;
	
	
	
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
