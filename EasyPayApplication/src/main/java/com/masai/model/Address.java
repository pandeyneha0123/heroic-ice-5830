package com.masai.model;

import jakarta.persistence.Embeddable;

@Embeddable
public class Address {
	private String city;
	private String state;
	private String pincode;
}
