package com.masai.model;


import org.hibernate.validator.constraints.Length;

import com.fasterxml.jackson.annotation.JsonIgnore;

import io.micrometer.common.lang.NonNull;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;



@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class BankAccount {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "account_Id")
    private Integer BankAccountId;
	@NotEmpty
	@NonNull
	@Length(min = 10,max = 20,message = "Enter valid AccountNumber")
	@Pattern(regexp = "[0-9]+", message = "number shoud be only Integers")
    private String AccountNumber;
	@NonNull
	@NotEmpty
	@Pattern(regexp = "^[A-Z]{4}0[A-Z0-9]{6}$",message = "shoud be valid ifsc code .. ")
    private String ifsc;
	@NonNull
	@NotEmpty
    private String Branch;
	@NonNull
    private Double ballence;
	@NonNull
	@NotEmpty
    private String bankName;
    
    @JsonIgnore
    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "customer_Id")
    private Customer customer;

}
