package com.masai.model;

import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
//BenificialDetil (BeniId Gv,name String ,MobleNumer String address address(hash-a relatioship))
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Benificial {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer benificialId;
	private String  name;
	private String mobileNumber;
	@Embedded
	private Address address;
}
