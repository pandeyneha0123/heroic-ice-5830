package com.masai.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor

public class TransactionDto {
	private double ammount;
	private String description;
	private String transectionType;
	private Integer walletId;
}
