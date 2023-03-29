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
//Wallet(id Gv,Amount double, LastDate LocalDate,List<BankAccont> wallet)

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Wallet {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer wid;
	private Double amount;
	private LocalDate lastUpdate;
	private List<BankAccount> banks;
	
}
