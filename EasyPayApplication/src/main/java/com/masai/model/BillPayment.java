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
//5.BillPayment(BillPay id,billType String ,amount Double,PaymentDate LocalDate,wallet wallet)
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class BillPayment {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int billPayId;
	private String billType;
	private Double amount;
	private LocalDate paymentDate;
	private Wallet wallet;

}
