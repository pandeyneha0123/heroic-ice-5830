package com.masai.model;

import java.time.LocalDate;

import org.springframework.stereotype.Component;

import com.fasterxml.jackson.annotation.JsonIgnore;

import io.micrometer.common.lang.NonNull;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
//5.BillPayment(BillPay id,billType String ,amount Double,PaymentDate LocalDate,wallet wallet)

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class BillPayment {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer billPayId;
	@NotNull
	@NotEmpty
    private String billType;
	@DecimalMin(value = "1",message = "amount must be greater than 1 ")
    private Double amount;
	@NotNull
    private LocalDate paymentDate;
	
    @JsonIgnore
    @OneToOne(fetch = FetchType.EAGER,cascade = CascadeType.ALL)
    @JoinColumn(name = "wallet_Id")
    private Wallet walletId;
    
}
