package com.masai.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class PhoneToPhoneTransectionDto {
	
	private String sourcePhone;
	private String targetMobileNumber; 
	private Double ammount;
}
