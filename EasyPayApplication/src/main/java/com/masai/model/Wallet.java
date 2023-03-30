package com.masai.model;

import java.time.LocalDate;
import java.util.List;
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

//Wallet(id Gv,Amount double, LastDate LocalDate,List<BankAccont> wallet)

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Wallet {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer walletId;
    private Double amount;
    private LocalDate lastUpdate;

    @OneToMany
    @JoinColumn(name = "walletId")
    private List<BankAccount> banks;

    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL, mappedBy = "walletId")
//    @JoinColumn(name = "bill_Id")#*#*#*#*#*
    List<BillPayment> bills;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "walletId")
    private List<Benificiary> benificiarylist;

}
