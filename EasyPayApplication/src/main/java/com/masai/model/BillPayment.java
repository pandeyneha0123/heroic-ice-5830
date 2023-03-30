package com.masai.model;

import java.time.LocalDate;

import org.springframework.stereotype.Component;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
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
    private Integer billPayId;
    private String billType;
    private Double amount;
    private LocalDate paymentDate;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "wallet_Id")
    private Wallet walletId;

}
