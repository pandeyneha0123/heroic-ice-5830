package com.masai.model;

import java.time.LocalDate;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

//Wallet(id Gv,Amount double, LastDate LocalDate,List<BankAccont> wallet)

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Wallet {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer walletId;
	private String customerName;
    private Double amount;
    private LocalDate lastUpdate;
    
    @JsonIgnore
    @OneToMany
    @JoinColumn(name = "walletId")
    private List<BankAccount> banks;
    
    @JsonIgnore
    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL, mappedBy = "walletId")
    List<BillPayment> bills;
    
    @JsonIgnore
    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL, mappedBy = "walletId")
    List<Transection> transections;
    
    @JsonIgnore
    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "walletId")
    private List<Benificiary> benificiarylist;

	public Integer getWalletId() {
		return walletId;
	}

	public void setWalletId(Integer walletId) {
		this.walletId = walletId;
	}

	public Double getAmount() {
		return amount;
	}

	public void setAmount(Double amount) {
		this.amount = amount;
	}

	public LocalDate getLastUpdate() {
		return lastUpdate;
	}

	public void setLastUpdate(LocalDate lastUpdate) {
		this.lastUpdate = lastUpdate;
	}

	public List<BankAccount> getBanks() {
		return banks;
	}

	public void setBanks(List<BankAccount> banks) {
		this.banks = banks;
	}

	public List<BillPayment> getBills() {
		return bills;
	}

	public void setBills(List<BillPayment> bills) {
		this.bills = bills;
	}

	public List<Benificiary> getBenificiarylist() {
		return benificiarylist;
	}

	public void setBenificiarylist(List<Benificiary> benificiarylist) {
		this.benificiarylist = benificiarylist;
	}

}
